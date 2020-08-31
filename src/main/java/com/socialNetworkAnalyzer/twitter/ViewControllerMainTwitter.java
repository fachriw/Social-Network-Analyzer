package com.socialNetworkAnalyzer.twitter;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewControllerMainTwitter {

	private PresenterMainTwitter presenterMainTwitter;

	private VBox pane;

	private TextField textField_relations_usernameInput;

	private Button button_relations_displayRelations;

	private Label label_relations_status;

	private Label bio;

	private Label amountFollowers;

	private Label amountFollowed;

	private Label amountTweets;
	private Label amountMentioned;

	private Label amountLiked;
	private Label amountRetweeted;

	private Label twitterLimits;
	private HBox hbox_tabPaneButtonList;

	private TweetDisplay userTweetDisplay;
	private TweetDisplay mentionsTweetDisplay;

	private UserDisplay followersUserDisplay;

	private TabPane tabPane_stats;
	private TabPane tabPane_tweets;
	private TabPane tabPane_mentions;
	private TabPane tabPane_followers;
	// private TabPane tabPane_relations;

	private boolean displayingSelf = true;

	@FXML
	private AnchorPane anchorPane;

	public ViewControllerMainTwitter(PresenterMainTwitter presenterMainTwitter) {
		this.presenterMainTwitter = presenterMainTwitter;
	}

	public void setPresenterMainTwitter(PresenterMainTwitter presenterMainTwitter) {
		this.presenterMainTwitter = presenterMainTwitter;
	}

	public void initView() throws IOException {
		pane = new VBox();

		Insets insets = new Insets(10);
		bio = new Label();
		amountFollowers = new Label();
		amountFollowed = new Label();
		amountTweets = new Label();
		amountMentioned = new Label();
		amountLiked = new Label();
		amountRetweeted = new Label();
		pane.setPadding(insets);
		twitterLimits = new Label();
		pane.getChildren().add(twitterLimits);

		hbox_tabPaneButtonList = new HBox();
		pane.getChildren().add(hbox_tabPaneButtonList);

		tabPane_stats = new TabPane(hbox_tabPaneButtonList, pane, "Stats");

		tabPane_stats.tabPaneContent.add(bio);
		tabPane_stats.tabPaneContent.add(amountFollowers);
		tabPane_stats.tabPaneContent.add(amountFollowed);
		tabPane_stats.tabPaneContent.add(amountTweets);
		tabPane_stats.tabPaneContent.add(amountMentioned);
		tabPane_stats.tabPaneContent.add(amountLiked);
		tabPane_stats.tabPaneContent.add(amountRetweeted);

		tabPane_tweets = new TabPane(hbox_tabPaneButtonList, pane, "Tweets");

		userTweetDisplay = new TweetDisplay("Tweets by you");
		userTweetDisplay.comboBox_timeFrameSelector.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				String selectedOrderType = (String) userTweetDisplay.comboBox_orderTypeSelector.valueProperty().get();

				presenterMainTwitter.requestUserTweets(selectedOrderType, t1, 100, displayingSelf);

			}
		});

		userTweetDisplay.comboBox_orderTypeSelector.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				String selectedTimeFrame = (String) userTweetDisplay.comboBox_timeFrameSelector.valueProperty().get();

				presenterMainTwitter.requestUserTweets(t1, selectedTimeFrame, 100, displayingSelf);

			}
		});

		tabPane_tweets.tabPaneContent.add(userTweetDisplay);

		tabPane_mentions = new TabPane(hbox_tabPaneButtonList, pane, "Mentions");

		mentionsTweetDisplay = new TweetDisplay("Mentions of you");
		mentionsTweetDisplay.enableUsernameColumn();
		mentionsTweetDisplay.comboBox_timeFrameSelector.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				String selectedOrderType = (String) mentionsTweetDisplay.comboBox_orderTypeSelector.valueProperty()
						.get();

				presenterMainTwitter.requestMentionsTweets(selectedOrderType, t1, 100, displayingSelf);

			}
		});

		mentionsTweetDisplay.comboBox_orderTypeSelector.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue ov, String t, String t1) {
				String selectedTimeFrame = (String) mentionsTweetDisplay.comboBox_timeFrameSelector.valueProperty()
						.get();

				presenterMainTwitter.requestMentionsTweets(t1, selectedTimeFrame, 100, displayingSelf);
			}
		});

		tabPane_mentions.tabPaneContent.add(mentionsTweetDisplay);

		tabPane_followers = new TabPane(hbox_tabPaneButtonList, pane, "Followers");

		followersUserDisplay = new UserDisplay("Followers of you");
		tabPane_followers.tabPaneContent.add(followersUserDisplay);

		// tabPane_relations = new TabPane(hbox_tabPaneButtonList, pane, "Relations");

		EventHandler<ActionEvent> button_displayInfo_eventHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				displayingSelf = false;
				String username = textField_relations_usernameInput.getText();
				presenterMainTwitter.requestInfo(username);

			}
		};
		label_relations_status = new Label();
		textField_relations_usernameInput = new TextField();

		button_relations_displayRelations = new Button("Display relations");
		button_relations_displayRelations.setOnAction(button_displayInfo_eventHandler);

		// tabPane_relations.tabPaneContent.add(new Label("Twitter username:"));
		// tabPane_relations.tabPaneContent.add(textField_relations_usernameInput);
		// tabPane_relations.tabPaneContent.add(button_relations_displayRelations);
		// tabPane_relations.tabPaneContent.add(label_relations_status);
	}

	public void resetAccountDisplay() {
		this.bio.setText("");
		this.amountFollowed.setText("");
		this.amountFollowers.setText("");
		this.label_relations_status.setText("");
		this.userTweetDisplay.clear();
		this.mentionsTweetDisplay.clear();
		this.followersUserDisplay.clear();

	}

	public Pane getUI() {
		return pane;
	}

	public void showErrorMessage(String message) {
		label_relations_status.setText(message);
	}

	public void showUserTweetDisplayMessage(String message) {
		this.userTweetDisplay.showMessage(message);
	}

	public void showMentionsTweetDisplayMessage(String message) {
		this.mentionsTweetDisplay.showMessage(message);
	}

	public void showFollowersUserDisplayMessage(String message) {
		this.followersUserDisplay.showMessage(message);
	}

	public void showAccountDisplay() {
		System.out.println("now showing account display");

		Stage window = new Stage();
		Scene scene = new Scene(pane);
		window.setScene(scene);
		window.setTitle("Login");
		window.setMaxHeight(1080);
		window.setMaxWidth(1920);
		window.setWidth(500);
		window.setHeight(500);
		window.setMinHeight(500);
		window.setMinWidth(500);

		window.show();
		presenterMainTwitter.requestInfo();
		presenterMainTwitter.requestUserTweets(TwitterInformation.orderTypeStrings[0],
				TwitterInformation.timeFrameStrings[0], 100, displayingSelf);
		presenterMainTwitter.requestMentionsTweets(TwitterInformation.orderTypeStrings[0],
				TwitterInformation.timeFrameStrings[0], 100, displayingSelf);

	}

	public void showPinInputWindow() {
		Stage window = new Stage();

		VBox rootBox = new VBox();
		Label label = new Label(
				"A Link should have been opened in your browser.\n Please log into Twitter and type the presented PIN number in the following Text field:\n");
		TextField pinEntryField = new TextField("Pin number from twitter");
		Button okButton = new Button("OK");

		EventHandler<ActionEvent> okButtonevent = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				presenterMainTwitter.requestAuthentication(pinEntryField.getText());
				window.close();

			}
		};

		okButton.setOnAction(okButtonevent);

		rootBox.getChildren().add(label);
		rootBox.getChildren().add(pinEntryField);
		rootBox.getChildren().add(okButton);

		Scene scene = new Scene(rootBox);
		window.setScene(scene);
		window.setTitle("Enter PIN");
		window.setMaxHeight(600);
		window.setMaxWidth(600);
		window.setWidth(500);
		window.setHeight(500);
		window.setMinHeight(500);
		window.setMinWidth(500);
		window.show();

	}

	public void showAuthenticationDisplay(String authenticationURL) {
		System.out.println("now showing authentication display");
		System.out.println("Open the following URL and grant access to your account:");
		if (Desktop.isDesktopSupported()) {
			new Thread(() -> {
				try {
					Desktop.getDesktop().browse(new URI(authenticationURL));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}).start();
		}
		showPinInputWindow();

		System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");

	}

	public void showAmountFollowed(int amount) {
		amountFollowed.setText("Amount of followed accounts: " + amount);
	}

	public void showAmountFollowers(int amount) {
		amountFollowers.setText("Amount of followers: " + amount);

	}

	public void showAmountTweets(int amount) {
		amountTweets.setText(amount + " tweets");

	}

	public void showAmountMentioned(int amount) {
		amountMentioned.setText("Mentioned " + amount + " times");
	}

	public void showAmountLiked(int amount) {
		amountLiked.setText(amount + " received likes");

	}

	public void showAmountRetweeted(int amount) {
		amountRetweeted.setText(amount + " received retweets");
	}

	public void showBio(String text) {
		bio.setText("Bio: " + text);
	}

	public void showMostFavoritedUserTweets(ArrayList<TweetEntry> mostFavoritedTweets) {
		this.userTweetDisplay.setTweetEntries(mostFavoritedTweets);

	}

	public void showMostRetweetedUserTweets(ArrayList<TweetEntry> mostRetweetedTweets) {
		this.userTweetDisplay.setTweetEntries(mostRetweetedTweets);

	}

	public void showMostFavoritedMentionsTweets(ArrayList<TweetEntry> mostFavoritedTweets) {
		this.mentionsTweetDisplay.setTweetEntries(mostFavoritedTweets);

	}

	public void showMostRetweetedMentionsTweets(ArrayList<TweetEntry> mostRetweetedTweets) {
		this.mentionsTweetDisplay.setTweetEntries(mostRetweetedTweets);

	}

	public void showFollowers(ArrayList<UserEntry> followers) {
		this.followersUserDisplay.setUserEntries(followers);
	}

	public void showFatalError() {
		// TODO Auto-generated method stub

	}

	public void showAuthenticationFailedMessage() {
		System.out.println("authentication failed, try again");
	}

}
