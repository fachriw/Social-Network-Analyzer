package com.socialNetworkAnalyzer.main;

import com.socialNetworkAnalyzer.ModelInsta;
import com.socialNetworkAnalyzer.login.ViewControllerLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewController {

    @FXML
    private Label followerslbl, followinglbl, followersgainedlbl, followerslostlbl;

    @FXML
    private GridPane gridpaneFudfb;

    @FXML
    private Button fdfubBtn;

    @FXML
    private Button fudfbBtn;

    @FXML
    private ImageView profpic;

    @FXML
    private Label usernamelbl, numofposts;

    private int fuCount, fdCount;


    private Presenter presenter;

    private int lastClicked = -1;


    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @FXML
    void followersudfb(ActionEvent event) {
        lastClicked = 2;
        Pane pane = loadFxml();

        Stage window = new Stage();
        window.setTitle("Followers You Don't Follow Back");
        window.setScene(new Scene(pane));
        window.show();
        lastClicked = -1;
    }

    @FXML
    void followingdfubHandler(ActionEvent event) {
        lastClicked = 1;
        Pane pane = loadFxml();

        Stage window = new Stage();
        window.setTitle("Following Don't Follow You Back");
        window.setScene(new Scene(pane));
        window.show();
        lastClicked = -1;
    }

    public Pane loadFxml(){
        Pane pane = new Pane();

        ViewControllerFollowing viewControllerFollowing= new ViewControllerFollowing();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.socialNetworkAnalyzer/Instafdfub.fxml"));
            pane = (Pane)loader.load();
            viewControllerFollowing = (ViewControllerFollowing) loader.getController();
            viewControllerFollowing.setPresenter(presenter);
        } catch (Exception e) {
            Logger.getLogger(ViewControllerLogin.class.getName()).log(Level.SEVERE, null, e);
        }

        ModelInsta modelInsta = new ModelInsta();
        presenter.setViewControllerFollowing(viewControllerFollowing);
        presenter.setModel(modelInsta);
        if (lastClicked == 1) {
            viewControllerFollowing.updateContentFollowingdfub();
        } else if (lastClicked == 2) {
            viewControllerFollowing.updateContentFollowersudfb();
        }
        return pane;
    }

    public void updateContent(){
        ModelInsta modelInsta = new ModelInsta();
        Instagram4j instagram4j = ModelInsta.getInstagram4jInstance();
        InstagramUser user = modelInsta.getConnectedProfile(instagram4j);
        followerslbl.setText("Followers: "+ String.valueOf(user.getFollower_count()));
        followinglbl.setText("Following: "+ String.valueOf(user.getFollowing_count()));
        numofposts.setText("Number Of Posts: "+ String.valueOf(user.getMedia_count()));
        usernamelbl.setText(user.getFull_name());
        modelInsta.createFile();
        modelInsta.readFollowersFile();
        modelInsta.followersTracking();
        followersgainedlbl.setText("Followers Gained: "+String.valueOf(modelInsta.getGainedFollowers()));
        followerslostlbl.setText("Followers Lost: "+ String.valueOf(modelInsta.getLostFollowers()));
        Image image = new Image(user.getProfile_pic_url());
        profpic.setImage(image);
        modelInsta.setFdfubCount(instagram4j, user);
        modelInsta.setFudfbCount(instagram4j, user);
        fdCount = modelInsta.getFdfubCount();
        fuCount = modelInsta.getFudfbCount();
        fdfubBtn.setText("Followers Don't Follow You Back: "+ fdCount);
        fudfbBtn.setText("Followers You Don't Follow Back: "+ fuCount);
    }
}
