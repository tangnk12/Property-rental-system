package com.example.property;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

import org.json.simple.parser.ParseException;

import java.io.FileWriter;

import java.io.IOException;


public class LogIn {

    public LogIn() {}

    @FXML
    private Button button;
    @FXML
    private Label wrongLogIn;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button getRequest;

    private User userdata;


    public void getRequestAcc(ActionEvent event) throws IOException, ParseException {
        requestAcc();
    }


    public void requestAcc()throws IOException{
        Main m = new Main();
        m.changeScene("submitdetail.fxml");
    }


    public void userLogIn(ActionEvent e) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));
        JSONParser admin = new JSONParser();

        JSONArray adminObj = (JSONArray) admin.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\adminAccount.json"));

        userdata = new User();

        for (Object o : obj) {
            JSONObject person = (JSONObject) o;
            String usernameData = (String) person.get("Username");
            String passWord = (String) person.get("Password");

            if (username.getText().toString().equals(usernameData) && password.getText().toString().equals(passWord)) {

                wrongLogIn.setText("Success!");

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("List.fxml"));
                Parent root = (Parent)loader.load();
                ListController listcontroller = (ListController)loader.getController();


                userdata.setUsername((String) person.get("Username"));
                userdata.setName((String) person.get("Name"));
                userdata.setEmail((String) person.get("Email"));
                userdata.setContactPhone((String) person.get("Contact Phone"));
                userdata.setContactPhone((String) person.get("IC"));
                userdata.setUserType((String) person.get("UserType"));
                userdata.setPassword((String) person.get("Password"));

                listcontroller.setUserdata(userdata);
                listcontroller.setupListView();

                Scene sceneView = new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.setScene(sceneView);
                stage.show();


                try (FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\thislogin.json")) {
                    //We can write any JSONArray or JSONObject instance to the file

                    JSONObject user = new JSONObject();
                    user.put("Login",username.getText());
                    file.write(String.valueOf(user));
                    file.flush();

                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }

            else if (username.getText().isEmpty() && password.getText().isEmpty()) {
                wrongLogIn.setText("Please enter your data.");
            } else {
                wrongLogIn.setText("Wrong username or password!");
            }

        }
        for (Object p : adminObj) {
            JSONObject adminAccount = (JSONObject)p ;

            String ID = (String) adminAccount.get("AdminID");
            String passWord = (String) adminAccount.get("AdminPassword");

            if (username.getText().toString().equals(ID) && password.getText().toString().equals(passWord)) {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("List.fxml"));
                Parent root = (Parent)loader.load();
                ListController listcontroller = (ListController)loader.getController();

                userdata.setUsername((String) adminAccount.get("AdminID"));
                userdata.setName((String) adminAccount.get("AdminID"));
                userdata.setUserType("Admin");
                userdata.setPassword((String) adminAccount.get("AdminPassword"));

                listcontroller.setUserdata(userdata);
                listcontroller.setupListView();

                Scene sceneView = new Scene(root);
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                stage.setScene(sceneView);
                stage.show();

            }

        }

    }

    public void setUserdata(User userdata) {
        this.userdata = userdata;
    }

}