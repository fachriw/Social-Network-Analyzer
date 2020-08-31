package com.socialNetworkAnalyzer.login;

import com.socialNetworkAnalyzer.ModelInsta;
import com.socialNetworkAnalyzer.SingletonSession;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public class PresenterLogin {

    private ViewControllerLogin viewControllerLogin;
    private ModelInsta modelInsta;


    public PresenterLogin(){}

    public void setViewControllerLogin(ViewControllerLogin viewControllerLogin) {
        this.viewControllerLogin = viewControllerLogin;
    }

    public void setModel(ModelInsta modelInsta) {
        this.modelInsta = modelInsta;
    }

    public Instagram4j login(String username, String password){
        Instagram4j instagram4j;
        modelInsta = new ModelInsta();
        instagram4j = modelInsta.login(username, password);
        return instagram4j;
    }

    public void storeUser(String username, String password){
        SingletonSession.Instance().setUsername(username);
        SingletonSession.Instance().setPassword(password);
    }

    public InstagramUser getProfile(Instagram4j instagram4j){
        InstagramUser instagramUser;
        modelInsta = new ModelInsta();
        instagramUser = modelInsta.getConnectedProfile(instagram4j);
        return instagramUser;
    }

    public static void updateProfile(InstagramUser user){
        ModelInsta.updateProfile(user);
    }

    public static void setInstagram4jInstance(Instagram4j instagram4j) {
        ModelInsta.setInstagram4jInstance(instagram4j);
    }
}
