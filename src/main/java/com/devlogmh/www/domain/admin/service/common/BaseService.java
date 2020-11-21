package com.devlogmh.www.domain.admin.service.common;

import com.devlogmh.www.domain.model.util.BaseControlDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface BaseService {
    /**
     * 必ず実行する処理
     * @param dto
     */
    void delegate(BaseControlDto dto);
}
