package com.example.property;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;


import org.json.simple.parser.ParseException;


import java.io.IOException;

public class giveRequest {

    public giveRequest(){}

    @FXML
    private TextArea requestField;
    @FXML
    private Button refresh;
    @FXML
    private Button buttonReject;
    @FXML
    private TextField getRequestID;

    @FXML
    private TextField textGiveUsername;
    @FXML
    private TextField textGivePassword;
    @FXML
    private ComboBox<String> eventsSelector;
    @FXML
    private Text configureText;
    @FXML
    private Button loginMenu;


    private User userdata;

    public static FileWriter file;
    public static FileWriter fiLe;


    public void giveRequest(ActionEvent event) throws ParseException, IOException {
        giveAccount();
    }

    @FXML
    private void initialize() throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json"));

        for (Object o : obj) {
            JSONObject person = (JSONObject) o;
            String fullname = (String) person.get("Name");
            if(person.get("Status").equals("Pending")) {
                eventsSelector.getItems().add(fullname);
            }
        }
    }


    public void giveAccount() throws ParseException, IOException {

        JSONParser parser = new JSONParser();
        String selector=eventsSelector.getValue();

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json"));

        for (Object o : obj) {

            JSONObject person = (JSONObject) o;

            if(selector.equals(person.get("Name"))){
                requestField.setWrapText(true);
                requestField.setText(person.toString());
            }

        }
    }
    public void setGetRequestID(ActionEvent event) throws ParseException, IOException {
        getRequestID();

    }
    public void rejectRequest(ActionEvent event) throws ParseException, IOException {
        deleteRequest();

    }

    public void getRequestID() throws ParseException, IOException {

        String selector=eventsSelector.getValue();
        JSONParser parser = new JSONParser();
        JSONParser parserd = new JSONParser();

        int k =0;
        int correct=0;

        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json"));
        JSONArray account = (JSONArray) parserd.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));

        for (Object o : obj) {

            JSONObject person = (JSONObject) o;
            boolean check=false;
            JSONArray jsonArray = (JSONArray)account;
            JSONObject user = new JSONObject();

            String name = (String) person.get("Name");
            correct+=1;

            if (selector.equals(name)&&"Pending".equals(person.get("Status"))) {
                int x = correct;

                person.remove("Status");
                person.put("Status", "Approved");
                for (Object p : account) {
                    JSONObject usernameCheck = (JSONObject) p;
                    String usernameChecking = (String) usernameCheck.get("Name");

                    k++;
                    if (selector.equals(usernameChecking)) {
                        check = true;
                    }

                    if (check == false) {

                        String username = textGiveUsername.getText();
                        user.put("Username", textGiveUsername.getText());

                        String fullname = (String) person.get("Name");
                        user.put("Name", (String) person.get("Name"));

                        String ContactInfo = (String) person.get("Contact Number");
                        user.put("Contact Number", (String) person.get("Contact Number"));

                        String Email = (String) person.get("Email");
                        user.put("Email", (String) person.get("Email"));

                        String Gender = (String) person.get("Gender");
                        user.put("Gender", (String) person.get("Gender"));

                        String UserType = (String) person.get("UserType");
                        user.put("UserType", (String) person.get("UserType"));

                        String password = textGivePassword.getText();
                        user.put("Password", textGivePassword.getText());

                    }
                }


                if (check == false) {
                    try {
                        obj.remove(x - 1);

                        obj.add(person);
                        jsonArray.add(user);

                        file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json");
                        fiLe = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json");
                        file.write(obj.toJSONString());
                        file.flush();
                        file.close();
                        fiLe.write(jsonArray.toJSONString());
                        fiLe.flush();
                        fiLe.close();

                    } catch (Exception e) {

                    }
                }
            }
        }
    }

    public void deleteRequest() throws ParseException, IOException {
        JSONParser parser = new JSONParser();
        String selector=eventsSelector.getValue();

        int requestDelete=0;
        int indexRequestDelete = 0;


        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json"));
        for (Object o : obj) {
            JSONObject person = (JSONObject) o;
            requestDelete+=1;
            if(selector.equals(person.get("Name"))){
            indexRequestDelete=requestDelete;
            }
        }

        try{
            obj.remove(indexRequestDelete-1);

            FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\requestdetail.json");
            file.write(obj.toJSONString());
            file.flush();
            file.close();

        } catch (Exception e) {

        }
    }

    public void returnMain(ActionEvent e) throws IOException {
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

    public void setUser(User userdata) {
        this.userdata = userdata;
    }
}




