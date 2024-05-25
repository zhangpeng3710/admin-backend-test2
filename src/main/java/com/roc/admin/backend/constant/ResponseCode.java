package com.roc.admin.backend.constant;

public enum ResponseCode {
    // common code
    SUCCESS(0, "success"),
    FAIL(1, "fail"),

    // 1001～2000
    // request error code
    PARAM_ERROR(1001, "param error"),
    PARAM_IS_NULL(1002, "param is null"),
    PARAM_TYPE_ERROR(1003, "param type error"),
    PARAM_BIND_ERROR(1004, "param bind error"),
    PARAM_NOT_COMPLETE(1005, "param not complete"),
    PARAM_NOT_EXIST(1006, "param not exist"),
    PARAM_NOT_MATCH(1007, "param not match"),

    // 2001～
    // user error code
    USER_NOT_EXIST(2001, "user not exist"),
    USER_ALREADY_EXIST(2002, "user already exist"),
    USER_NOT_LOGIN(2003, "user not login"),
    USER_LOGIN_ERROR(2004, "user name or password error"),
    USER_HAS_EXISTED(2005, "user has existed"),
    USER_REGISTER_ERROR(2006, "user register error"),
    USER_UPDATE_ERROR(2007, "user update error"),
    USER_DELETE_ERROR(2008, "user delete error"),
    USER_PASSWORD_ERROR(2009, "user password error"),
    USER_AUTH_ERROR(2010, "user authentication error"),

    // blog error
    BLOG_NOT_EXIST(2101, "blog not exist"),
    BLOG_TITLE_OR_CONTENT_ERROR(2102, "blog title or content error"),
    BLOG_HAS_EXISTED(2103, "blog has existed"),
    BLOG_CREATE_ERROR(2104, "blog create error"),
    BLOG_UPDATE_ERROR(2105, "blog update error"),
    BLOG_DELETE_ERROR(2106, "blog delete error"),

    // comment error
    COMMENT_NOT_EXIST(2201, "comment not exist"),
    COMMENT_HAS_EXISTED(2202, "comment has existed"),
    COMMENT_CONTENT_EMPTY(2203, "comment content empty"),

    // tag error
    TAG_NOT_EXIST(2301, "tag not exist"),
    TAG_HAS_EXISTED(2302, "tag has existed"),
    TAG_CREATE_ERROR(2303, "tag create error"),
    TAG_UPDATE_ERROR(2304, "tag update error"),
    TAG_DELETE_ERROR(2305, "tag delete error"),

    // category error
    CATEGORY_NOT_EXIST(2401, "category not exist"),
    CATEGORY_HAS_EXISTED(2402, "category has existed");


    /**
     * 自定义状态码
     **/
    private final int code;
    /**
     * 自定义描述
     **/
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
