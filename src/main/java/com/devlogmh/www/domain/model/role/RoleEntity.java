package com.devlogmh.www.domain.model.role;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 権限一覧クラス
 */
@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

}
