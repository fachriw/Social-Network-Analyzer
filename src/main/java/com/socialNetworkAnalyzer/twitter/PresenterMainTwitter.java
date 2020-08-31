package com.socialNetworkAnalyzer.twitter;

import twitter4j.TwitterException;

import java.util.ArrayList;

public class PresenterMainTwitter {

    private ViewControllerMainTwitter viewControllerMainTwitter;
    private ModelTwitter modelTwitter;

    public void setViewControllerMainTwitter(ViewControllerMainTwitter viewControllerMainTwitter) {
        this.viewControllerMainTwitter = viewControllerMainTwitter;
    }

    public void setModelTwitter(ModelTwitter modelTwitter) {
        this.modelTwitter = modelTwitter;
    }

    public void setViewControllerLoginTwitter(ViewControllerMainTwitter viewControllerLoginTwitter) {
        this.viewControllerMainTwitter = viewControllerMainTwitter;
    }


    public void init() {
        try {
            this.modelTwitter.useExistingTwitterAuthentication();

            this.viewControllerMainTwitter.showAccountDisplay();
        } catch (AuthenticationException e) {
            try {
                this.viewControllerMainTwitter.showAuthenticationDisplay(this.modelTwitter.getTwitterAuthenticationURL());
            } catch (TwitterException e1) {

                e1.printStackTrace();
                viewControllerMainTwitter.showFatalError();
            }

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void requestAuthentication(String pin) {
        try {
            this.modelTwitter.useTwitterAuthenticationPin(pin);
            this.viewControllerMainTwitter.showAccountDisplay();

        } catch (TwitterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            this.viewControllerMainTwitter.showAuthenticationFailedMessage();
            try {
                this.viewControllerMainTwitter.showAuthenticationDisplay(this.modelTwitter.getTwitterAuthenticationURL());
            } catch (TwitterException e1) {

                e1.printStackTrace();
                viewControllerMainTwitter.showFatalError();
            }

        }

    }

    public void requestUserTweets(String orderType, String timeFrame, int amount, boolean fromSelf) {
        TwitterInformation ti;
        try {
            ti = modelTwitter.getTwitterInfo(fromSelf);
            ArrayList<TweetEntry> tweetTableEntries = ti.getTweetEntries(TwitterInformation.sourceType.USERTIMELINE,
                    orderType, timeFrame, amount);
            viewControllerMainTwitter.showMostFavoritedUserTweets(tweetTableEntries);

        } catch (TwitterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            viewControllerMainTwitter.showUserTweetDisplayMessage("No new tweets since " + timeFrame);
        }

    }

    public void requestMentionsTweets(String orderType, String timeFrame, int amount, boolean fromSelf) {
        TwitterInformation ti;
        try {
            ti = modelTwitter.getTwitterInfo(fromSelf);
            ArrayList<TweetEntry> tweetTableEntries = ti.getTweetEntries(TwitterInformation.sourceType.MENTIONSTIMELINE,
                    orderType, timeFrame, amount);
            viewControllerMainTwitter.showMostFavoritedMentionsTweets(tweetTableEntries);
        } catch (TwitterException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            viewControllerMainTwitter.showMentionsTweetDisplayMessage("No new mentions since " + timeFrame);
        }

    }

    public void requestInfo() {
        try {
            viewControllerMainTwitter.resetAccountDisplay();
            TwitterInformation ti = modelTwitter.getTwitterInfo(true);
            this.viewControllerMainTwitter.showAmountFollowed(ti.getAmountFollowed());
            this.viewControllerMainTwitter.showAmountFollowers(ti.getAmountFollowers());
            this.viewControllerMainTwitter.showBio(ti.getBio());
            this.viewControllerMainTwitter.showAmountTweets(ti.getAmountUserTweets());
            this.viewControllerMainTwitter.showAmountMentioned(ti.getAmountMentionsTweets());
            this.viewControllerMainTwitter.showAmountLiked(ti.getAmountLiked());
            this.viewControllerMainTwitter.showAmountRetweeted(ti.getAmountRetweeted());
            this.viewControllerMainTwitter.showFollowers(ti.getFollowersUserEntries());

        } catch (TwitterException e) {
            viewControllerMainTwitter.showErrorMessage(e.getMessage());
        }

    }

    public void requestInfo(String username) {
        // TODO Auto-generated method stub
        try {
            viewControllerMainTwitter.resetAccountDisplay();
            TwitterInformation ti = modelTwitter.getTwitterInfo(username);
            this.viewControllerMainTwitter.showAmountFollowed(ti.getAmountFollowed());
            this.viewControllerMainTwitter.showAmountFollowers(ti.getAmountFollowers());
            this.viewControllerMainTwitter.showBio(ti.getBio());
            this.viewControllerMainTwitter.showAmountTweets(ti.getAmountUserTweets());
            this.viewControllerMainTwitter.showAmountMentioned(ti.getAmountMentionsTweets());
            this.viewControllerMainTwitter.showAmountLiked(ti.getAmountLiked());
            this.viewControllerMainTwitter.showAmountRetweeted(ti.getAmountRetweeted());

        } catch (TwitterException e) {
            viewControllerMainTwitter.showErrorMessage(e.getMessage());
        }

    }
}
