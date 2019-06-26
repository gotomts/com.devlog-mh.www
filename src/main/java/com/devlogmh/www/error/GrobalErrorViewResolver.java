package com.devlogmh.www.error;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.devlogmh.www.domain.admin.util.RoutesContains.*;

@Component
@RequestMapping("admin")
public class GrobalErrorViewResolver implements ErrorViewResolver {

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
                                         HttpStatus status, Map<String, Object> model) {

        ModelAndView mav = new ModelAndView();

        // HTTP 404 error
        if (status == HttpStatus.NOT_FOUND) {

            mav.addObject("message", "HTTP 404 エラー メッセージ");
            mav.setViewName(ERROR_404);

            // HTTP 4xx error
        } else if (status.is4xxClientError()) {

            mav.setViewName(ERROR_4XX);

            // HTTP 5xx error
        } else if (status.is5xxServerError()) {

            mav.setViewName(ERROR_5XX);

        } else {

            mav.setViewName(ERROR);
        }

        return mav;
    }

}
