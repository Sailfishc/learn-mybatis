package com.sailfish.dto;

import com.sailfish.model.SysRole;

/**
 * 对role类的扩展
 * @author sailfish
 * @create 2017-08-05-下午7:59
 */
public class SysRoleExtend  extends SysRole{

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "SysRoleExtend{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
