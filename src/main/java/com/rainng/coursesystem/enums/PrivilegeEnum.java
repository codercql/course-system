package com.rainng.coursesystem.enums;

/**
 * @program: course-system
 * @description: 权限角色枚举
 * @author: chenqiulu
 * @create: 2024-04-13 02:07
 **/
public enum PrivilegeEnum {
    TEACHER("teacher","老师"),
    STUDENT("student","学生"),
    ADMIN("admin","管理员");

    private String code;

    private String desc;

    PrivilegeEnum(String code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
