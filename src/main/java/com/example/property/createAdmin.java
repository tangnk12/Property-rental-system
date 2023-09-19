package com.example.property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class createAdmin {
    @FXML
    private Text createAdminText;
    @FXML
    private Text adminPasswordText;
    @FXML
    private TextField getNewAdminPasswordField;
    @FXML
    private Button mainMenuButton;
    @FXML
    private Button adminCreateButton;
    public static FileWriter file;

    private User userdata = new User();

    public void mainMenuFXML(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("List.fxml"));
        Parent root = (Parent)loader.load();

        ListController listcontroller = (ListController)loader.getController();

        listcontroller.setUserdata(userdata); //passing data to list view
        listcontroller.setupListView();

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void confirmCreateAdmin(ActionEvent event) throws IOException, ParseException {
        JSONParser parser = new JSONParser();

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\adminAccount.json"));
        JSONArray jsonArray = (JSONArray)obj;
        JSONObject newAdminUser = new JSONObject();
        int adminIDNumber=0;
        for (Object o : obj) {
            adminIDNumber+=1;

        }

        try{
            String newAdminPassword=getNewAdminPasswordField.getText();
            String adminID=("A000"+adminIDNumber);
            newAdminUser.put("AdminID",adminID);

            newAdminUser.put("AdminPassword",newAdminPassword);

            jsonArray.add(newAdminUser);
            file=new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\adminAccount.json");
            file.write(jsonArray.toJSONString());
            file.flush();
            file.close();

        }
        catch (Exception e) {

        }
    }

    public void setUser(User userdata) {
        this.userdata = userdata;
    }
}






