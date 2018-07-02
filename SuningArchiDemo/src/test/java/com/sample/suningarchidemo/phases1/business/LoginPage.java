package com.sample.suningarchidemo.phases1.business;

import com.sample.suningarchidemo.common.Printer;
import com.sample.suningarchidemo.phases1.network.LoginNetService;
import com.sample.suningarchidemo.phases1.network.LoginNetServiceImpl;
import com.sample.suningarchidemo.phases1.network.OnNetCallbackListener;
import com.sample.suningarchidemo.phases1.storage.LoginStorageService;
import com.sample.suningarchidemo.phases1.storage.LoginStorageServiceImpl;

public class LoginPage {

    private LoginNetService loginNetService;
    private LoginStorageService loginStorageService;

    public LoginPage() {
        Printer.print("初始化登录页面", true);

        loginNetService = new LoginNetServiceImpl();
        loginStorageService = new LoginStorageServiceImpl();

        requestAuthCode();
        login();
    }

    /**
     * 请求验证码
     */
    public void requestAuthCode() {
        loginNetService.getAuthcode("18512345678", 1, new OnNetCallbackListener() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    /**
     * 登录
     */
    public void login() {
        loginNetService.login("18512345678", "8888", new OnNetCallbackListener() {
            @Override
            public void onSuccess(String result) {
                Printer.print("登录成功", true);
                loginStorageService.saveToken(result);
                Printer.print("跳转到主页面");
            }

            @Override
            public void onFail(String error) {

            }
        });
    }
}