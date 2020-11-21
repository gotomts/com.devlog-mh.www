package com.devlogmh.www.domain.model.role;

import lombok.Getter;
import lombok.Setter;

/**
 * 権限一覧クラス
 */
public class RoleEntity {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    /**
     * デフォルトコンストラクタ
     */
    public RoleEntity() {
        super();
    }

    /**
     * コンストラクタ
     * @param id
     * @param name
     */
    public RoleEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

}
