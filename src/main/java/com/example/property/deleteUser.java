package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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


public class deleteUser {
    public deleteUser(){}
    @FXML
    private Button userDeleteButton;
    @FXML
    private ComboBox<String> eventsSelector;
    @FXML
    private TextField userDetailShown;
    @FXML
    private Button adminMenuButton;
    @FXML
    private Button getUserDetail;
    @FXML
    private Text icTag;
    @FXML
    private Text nameTag;
    @FXML
    private Text genderTag;
    @FXML
    private Text contactTag;
    @FXML
    private Text emailTag;
    @FXML
    private Text passwordTag;
    @FXML
    private Text userName;
    @FXML
    private Text userfullName;
    @FXML
    private Text userGender;
    @FXML
    private Text userEmail;
    @FXML
    private Text userContact;
    @FXML
    private Text userType;
    @FXML
    private Text userPassword;


    private User userdata = new User();

    @FXML
    private void initialize() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));

        for (Object o : obj) {

            JSONObject person = (JSONObject) o;

            String username = (String) person.get("Username");
            eventsSelector.getItems().add(username);

        }
    }

    public void deleteUserConfirm(ActionEvent event) throws IOException, ParseException {
        deleteUsers();
    }

    private void deleteUsers() throws IOException, ParseException {
        String selector=eventsSelector.getValue();
        JSONParser parser = new JSONParser();
        int userDeleteIndex=0;
        int pinUserDelete = 0;

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));

        for (Object o : obj) {

            JSONObject accountDelete = (JSONObject) o;
            String username = (String) accountDelete.get("Username");
            userDeleteIndex+=1;

            if (selector.equals(username)) {
                pinUserDelete=userDeleteIndex;
            }
        }
        try{
            obj.remove(pinUserDelete-1);

            FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (Exception e) {

        }

    }

    public void returnList(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("List.fxml"));
        Parent root = (Parent)loader.load();

        ListController listcontroller = (ListController)loader.getController();

        listcontroller.setUserdata(userdata);
        listcontroller.setupListView();

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void refreshUserDetail(ActionEvent event) throws IOException, ParseException {
        String selector = eventsSelector.getValue();
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));
        for (Object o : obj) {

            JSONObject accountDelete = (JSONObject) o;
            String username = (String) accountDelete.get("Username");
            if (selector.equals(username)) {

                userName.setText((String) accountDelete.get("Username"));
                userfullName.setText((String) accountDelete.get("Name"));
                userGender.setText((String) accountDelete.get("Gender"));
                userEmail.setText((String) accountDelete.get("Email"));
                userContact.setText((String) accountDelete.get("Contact Number"));
                userType.setText((String) accountDelete.get("UserType"));
                userPassword.setText((String) accountDelete.get("password"));
            }
        }
    }

    public void setUser(User userdata){
        this.userdata = userdata;
    }
}



