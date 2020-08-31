package com.socialNetworkAnalyzer;

public class SingletonSession {

    private static SingletonSession instance;
    private String username;
    private String password;
    //no outer class can initialize this class's object
    private SingletonSession() {}

    public static SingletonSession Instance()
    {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null)
        {
            instance = new SingletonSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void cleanUserSession() {
        username = "";// or null
    }

/*public class UserSession {

    private static UserSession instance;

    private String userName;

    private UserSession(){}

    private static UserSession instance(){
        if (instance==null){
            instance = new UserSession();
        }
        return instance;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }*/

/*    public void cleanUserSession() {
        userName = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName +
                '}';
    }*/
    /*private UserSession(String userName) {
        this.userName = userName;
    }


    private static UserSession getInstace(String userName) {
        if(instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }


    public void cleanUserSession() {
        userName = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName +
                '}';
    }*/
}

