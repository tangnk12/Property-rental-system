package com.example.property;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import javafx.event.ActionEvent;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;


import org.json.simple.parser.ParseException;


import java.io.IOException;

public class submitdetail {

    public submitdetail() { }

    @FXML
    private TextField getName;
    @FXML
    private TextField getPhone;
    @FXML
    private TextField getEmail;
    @FXML
    private Button requestSubmit;
    @FXML
    private RadioButton getSex;
    @FXML
    private RadioButton getSex2;
    @FXML
    private RadioButton getAgent;
    @FXML
    private RadioButton getOwner;
    @FXML
    private RadioButton getTenant;

    public static FileWriter file;

    public void confirmSubmit(ActionEvent event) throws ParseException {
        getUser();
    }

    public void getUser() throws ParseException {

        JSONParser parser = new JSONParser();

        try{

            Object size = parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json"));
            JSONArray jsonArray = (JSONArray)size;
            JSONObject user = new JSONObject();

            user.put("Name", getName.getText());
            user.put("Contact Number", getPhone.getText());
            user.put("Email", getEmail.getText());

            if (getSex.isSelected()) {
                user.put("Gender", "Male");
            }
            if (getSex2.isSelected()) {
                user.put("Gender", "Female");
            }

            if (getAgent.isSelected()) {
                user.put("UserType", "Agent");
            }
            if (getOwner.isSelected()) {
                user.put("UserType", "Owner");
            }
            if (getTenant.isSelected()) {
                user.put("UserType", "Tenant");
            }

            user.put("Status", "Pending");

            jsonArray.add(user);

            file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json");
            file.write(jsonArray.toJSONString());

            }catch(IOException e){
                e.printStackTrace();
            } finally{
                try {
                    file.flush();
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void returnLogin(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("main.fxml");

    }
}
