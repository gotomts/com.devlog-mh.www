package com.devlogmh.www.domain.admin.service.post;

import com.devlogmh.www.domain.admin.service.common.AbsUtilService;
import com.devlogmh.www.domain.admin.util.Contains;
import com.devlogmh.www.domain.model.post.PostControlDto;
import com.devlogmh.www.domain.model.post.PostDto;
import com.devlogmh.www.domain.model.post.PostListForm;
import com.devlogmh.www.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 投稿記事管理 ゴミ箱へ追加
 */
@Service
@Transactional
public class PostTrashAddService extends AbsUtilService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostControlDto postControlDto;

    private PostListForm postListForm;

    private BindingResult bindingResult;

    private ModelAndView mav;

    private RedirectAttributes redirectAttributes;

    /**
     * 初期処理
     */
    @Override
    public void customInit() {
        // コントローラーから渡された値を取得
        this.postListForm = postControlDto.getPostListForm();
        this.bindingResult = postControlDto.getBindingResult();
        this.redirectAttributes = postControlDto.getRedirectAttributes();
    }

    /**
     * 主処理
     */
    @Override
    public void mainProcess() {

        // エラーだった場合
        if (this.bindingResult.hasErrors()) {

            // リダイレクト時のエラーメッセージを詰める
            String errorMsg = "ゴミ箱へ移動する投稿記事を選択してください。";
            this.redirectAttributes.addFlashAttribute("errorMsg", errorMsg);

            return;

        }

        // formからdtoへ詰め替え
        PostDto postDto = new PostDto();
        postDto.setDelflg(this.postListForm.getDelflg());
        postDto.setCheckId(this.postListForm.getCheckId());

        // チェックしたレコードをゴミ箱へ移動
        this.trashAdd(postDto);

    }

    /**
     * ゴミ箱へ追加
     * @param postDto
     */
    public void trashAdd(PostDto postDto) {

        // Deleteフラグ
        postDto.setDelflg(Contains.DelFlg.DELETE.getValue());
        postMapper.trashMove(postDto);

    }

}
