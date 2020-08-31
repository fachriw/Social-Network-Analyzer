package com.socialNetworkAnalyzer.mainLogin;

import com.socialNetworkAnalyzer.ModelInsta;

public class PresenterMainLogin {

    private ViewControllerMainLogin viewControllerMainLogin;
    private ModelInsta modelInsta;

    public PresenterMainLogin(){}

    public void setViewControllerMainLogin(ViewControllerMainLogin viewControllerMainLogin) {
        this.viewControllerMainLogin = viewControllerMainLogin;
    }

    public void setModel(ModelInsta modelInsta) {
        this.modelInsta = modelInsta;
    }
}
