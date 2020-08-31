package com.socialNetworkAnalyzer.main;

import com.socialNetworkAnalyzer.ModelInsta;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary;

import java.util.List;

public class ViewControllerFollowing {

    @FXML
    private ScrollPane scrollpanePane;

    private GridPane gridPane_userTable;

    private Presenter presenter;

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void updateContentFollowingdfub(){
        ModelInsta modelInsta = new ModelInsta();
        Instagram4j instagram4j = ModelInsta.getInstagram4jInstance();
        InstagramUser user = modelInsta.getConnectedProfile(instagram4j);
        List<InstagramUserSummary> following = modelInsta.followingdfub(instagram4j, user);

        int line = 0;
        int col = 0;
        gridPane_userTable = new GridPane();
        gridPane_userTable.setHgap(10);
        gridPane_userTable.setVgap(50);
        gridPane_userTable.setPadding(new Insets(2, 10, 0, 10));

        for (int i = 0; i < 4; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(25);

            gridPane_userTable.getColumnConstraints().add(cc);

        }

        scrollpanePane.setContent(gridPane_userTable);
        scrollpanePane.setFitToHeight(true);
        scrollpanePane.setFitToWidth(true);

        for(InstagramUserSummary userSummary: following){
            if (col == 4){
                col = 0;
                line++;
            }

            Label label = new Label(userSummary.getFull_name());
            VBox entryCellBox = new VBox();

            Image image = new Image(userSummary.getProfile_pic_url());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            entryCellBox.getChildren().add(imageView);
            entryCellBox.getChildren().add(label);
            entryCellBox.setSpacing(10);
            entryCellBox.setAlignment(Pos.CENTER);
            gridPane_userTable.add(entryCellBox, col++, line);
        }
    }

    public void updateContentFollowersudfb(){
        ModelInsta modelInsta = new ModelInsta();
        Instagram4j instagram4j = ModelInsta.getInstagram4jInstance();
        InstagramUser user = modelInsta.getConnectedProfile(instagram4j);
        List<InstagramUserSummary> follower = modelInsta.followerudfb(instagram4j, user);

        int line = 0;
        int col = 0;
        gridPane_userTable = new GridPane();
        gridPane_userTable.setHgap(10);
        gridPane_userTable.setVgap(50);
        gridPane_userTable.setPadding(new Insets(2, 10, 0, 10));

        for (int i = 0; i < 4; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(25);

            gridPane_userTable.getColumnConstraints().add(cc);

        }

        scrollpanePane.setContent(gridPane_userTable);
        scrollpanePane.setFitToHeight(true);
        scrollpanePane.setFitToWidth(true);

        for(InstagramUserSummary userSummary: follower){
            if (col == 4){
                col = 0;
                line++;
            }

            Label label = new Label(userSummary.getFull_name());
            VBox entryCellBox = new VBox();

            Image image = new Image(userSummary.getProfile_pic_url());

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            entryCellBox.getChildren().add(imageView);
            entryCellBox.getChildren().add(label);
            entryCellBox.setSpacing(10);
            entryCellBox.setAlignment(Pos.CENTER);
            gridPane_userTable.add(entryCellBox, col++, line);
        }
    }
}
