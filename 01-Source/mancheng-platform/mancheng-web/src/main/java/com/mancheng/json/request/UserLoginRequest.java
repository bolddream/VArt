package com.mancheng.json.request;

import com.mancheng.json.base.BaseRequest;

public class UserLoginRequest extends BaseRequest {

    /**
     * 登录密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
