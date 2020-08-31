package com.socialNetworkAnalyzer.twitter;

public class AuthenticationException extends Exception {
    public AuthenticationException(String errorMessage) {
        super(errorMessage);
    }
}
