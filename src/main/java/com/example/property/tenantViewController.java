package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class tenantViewController {

    @FXML
    private Button editProfile;

    @FXML
    private Button logOut;

    @FXML
    private ImageView logo;

    @FXML
    private Label username;

    @FXML
    private Label type;

    private User userdata;

    public void setUser(User userdata) {
        this.userdata = userdata;

        username.setText(userdata.getName());
        type.setText(userdata.getUserType());
    }

    public void editProfileClicked (ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editPersonal.fxml"));

        Parent root = (Parent)loader.load();

        editPersonal editPersonalController = (editPersonal)loader.getController();
        editPersonalController.setUser(userdata);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void logoutClicked (ActionEvent e) throws IOException {
        Main m = new Main();
        m.changeScene("main.fxml");
    }

}
