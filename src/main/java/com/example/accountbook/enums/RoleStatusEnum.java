package com.example.accountbook.enums;

public enum RoleStatusEnum {
    MEMBER(0),
    ADMIN(1),
    CREATOR(2);

    private final int code;
    private RoleStatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
