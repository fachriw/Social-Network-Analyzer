package com.socialNetworkAnalyzer.fblogin;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

public class Get_User_Details {
    public User_Profile Get_Profile_info(String accesstoken) {
        User_Profile obj_User_Profile=new User_Profile();
        FacebookClient facebookClient = new DefaultFacebookClient(accesstoken, Version.VERSION_3_2);
        User user = facebookClient.fetchObject("me", User.class);
        System.out.println("User name: " + user.getName());
        obj_User_Profile.setUser_name(user.getName());
        return obj_User_Profile;
    }
}
