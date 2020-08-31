package com.socialNetworkAnalyzer.login;

import com.socialNetworkAnalyzer.ModelInsta;
import com.socialNetworkAnalyzer.main.Presenter;
import com.socialNetworkAnalyzer.main.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewControllerLogin{

    @FXML
    private TextField usernametxt;

    @FXML
    private PasswordField passwordtxt;


    private PresenterLogin presenterLogin;


    public ViewControllerLogin(){}

    public void setPresenterLogin(PresenterLogin presenterLogin) {
        this.presenterLogin = presenterLogin;
    }

    @FXML
    void loginHandler(ActionEvent event) {
        String username = usernametxt.getText();
        String password = passwordtxt.getText();


        Instagram4j instagram4j = presenterLogin.login(usernametxt.getText(), passwordtxt.getText());
        if (instagram4j!=null){
            InstagramUser user = presenterLogin.getProfile(instagram4j);
            if (user != null){

                PresenterLogin.updateProfile(user);
                PresenterLogin.setInstagram4jInstance(instagram4j);

                Presenter presenter = new Presenter();

                presenter.setUsername(user.getUsername());
                presenter.setUser(user);
                Presenter.setInstagram4jInstance(instagram4j);
                Pane pane = new Pane();

                ViewController viewController= new ViewController();

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.socialNetworkAnalyzer/InstaMain.fxml"));
                    pane = (Pane)loader.load();
                    viewController = (ViewController)loader.getController();
                    viewController.setPresenter(presenter);
                } catch (Exception e) {
                    Logger.getLogger(ViewControllerLogin.class.getName()).log(Level.SEVERE, null, e);
                }


                ModelInsta modelInsta = new ModelInsta();
                ModelInsta.getInstagram4jInstance();
                presenter.setViewController(viewController);
                presenter.setModel(modelInsta);
                viewController.updateContent();

                Scene scene = new Scene(pane);


                Node node =(Node)event.getSource();
                Stage window = ((Stage)node.getScene().getWindow());
                window.setScene(scene);
                window.show();
            }
        }

    }
}
