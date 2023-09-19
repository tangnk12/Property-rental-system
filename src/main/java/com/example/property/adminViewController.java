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

public class adminViewController {

    @FXML
    private Button addNewProp;

    @FXML
    private Button checkRequest;

    @FXML
    private Button deleteUser;

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

    private User userdata = new User();

    public void setUser(User userdata) {
        this.userdata = userdata;

        username.setText(userdata.getName());
        type.setText(userdata.getUserType());
    }

    public void addNewPropClicked (ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddProperty.fxml"));

        Parent root = (Parent)loader.load();

        AddPropertyController addPropertyController = (AddPropertyController)loader.getController();
        addPropertyController.setUser(userdata);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void checkRequestClicked (ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("giveRequest.fxml"));
        Parent root = (Parent)loader.load();
        giveRequest controller = (giveRequest)loader.getController();

        controller.setUser(userdata);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();


    }

    public void deleteUserClicked (ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("deleteUser.fxml"));
        Parent root = (Parent)loader.load();
        deleteUser controller = (deleteUser)loader.getController();

        controller.setUser(userdata);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();
    }

    public void createAdminClicked (ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("createAdmin.fxml"));
        Parent root = (Parent)loader.load();
        createAdmin controller = (createAdmin)loader.getController();

        controller.setUser(userdata);

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
