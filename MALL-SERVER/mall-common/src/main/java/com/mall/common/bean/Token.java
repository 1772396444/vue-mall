package com.mall.common.bean;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Data
@Accessors(chain = true)
public class Token {

    private String id;
    private String userId;
    private String staffId;
    private String roleId;
    private String client;

    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String STAFF_ID = "staffId";
    public static final String ROLE_ID = "roleId";
    public static final String IS_CLIENT = "client";

}
