package com.socialNetworkAnalyzer.main;

import com.socialNetworkAnalyzer.ModelInsta;
import com.socialNetworkAnalyzer.login.PresenterLogin;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.List;

public class Presenter {

    private ViewController viewController;
    private ViewControllerFollowing viewControllerFollowing;
    private ModelInsta modelInsta;
    private PresenterLogin presenterLogin = new PresenterLogin();
    private String username;
    private InstagramUser user;

    public Presenter(){}

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public void setViewControllerFollowing(ViewControllerFollowing viewControllerFollowing) {
        this.viewControllerFollowing = viewControllerFollowing;
    }

    public void setModel(ModelInsta modelInsta) {
        this.modelInsta = modelInsta;
    }


    public int getFollowers(){
        int followers;
        modelInsta = new ModelInsta();
        followers= modelInsta.getFollowersCount();
        return followers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public InstagramUser getUser() {
        return user;
    }

    public void setUser(InstagramUser user) {
        this.user = user;
    }

    public static void setInstagram4jInstance(Instagram4j instagram4j) {
        ModelInsta.setInstagram4jInstance(instagram4j);
    }


    public List<InstagramUserSummary> followingdfub(Instagram4j instagram4j, InstagramUser instagramUser){
        List<InstagramUserSummary> following = modelInsta.followingdfub(instagram4j, instagramUser);
        return following;
    }

    public List<InstagramUserSummary> followerudfb(Instagram4j instagram4j, InstagramUser instagramUser){
        List<InstagramUserSummary> followers = modelInsta.followerudfb(instagram4j, instagramUser);
        return followers;
    }
}
