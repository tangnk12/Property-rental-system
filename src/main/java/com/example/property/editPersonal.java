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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class editPersonal {

    @FXML
    private Button confirmEditButton;
    @FXML
    private Text usernameText;
    @FXML
    private Button refreshDetail;
    @FXML
    private Text nameText;
    @FXML
    private Text Type;
    @FXML
    private Button backButton;

    @FXML
    private TextField Gender;
    @FXML
    private TextField Contact;
    @FXML
    private TextField Email;


    private User userdata = new User();

    public void confirmEdit(ActionEvent event) throws IOException, ParseException {
        int deleteItem=0;

        JSONParser parserDetail = new JSONParser();
        JSONArray personDetail= (JSONArray) parserDetail.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));

        for (Object o : personDetail) {

            JSONObject accountDetail = (JSONObject) o;
            deleteItem+=1;

            if(userdata.getUsername().equals(accountDetail.get("Username"))){
                String gender=Gender.getText();
                String contact=Contact.getText();
                String email=Email.getText();
                String usernameData= (String) accountDetail.get("Username");
                String userName= (String) accountDetail.get("Name");
                String Status=(String) accountDetail.get("UserType");
                String password=(String) accountDetail.get("Password");

                accountDetail.remove("Gender");
                accountDetail.put("Gender",gender);
                accountDetail.remove("Contact Phone");
                accountDetail.put("Contact Phone",contact);
                accountDetail.remove("Email");
                accountDetail.put("Email",email);
                accountDetail.put("Name",userName);
                accountDetail.put("Username",usernameData);
                accountDetail.put("UserType",Status);
                accountDetail.put("Password",password);

                try{
                    personDetail.remove(deleteItem-1);

                    personDetail.add(accountDetail);

                    FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json");
                    file.write(personDetail.toJSONString());
                    file.flush();
                    file.close();

                } catch (Exception e) {

                }
            }
        }
    }

    //reload user personal data
    public void refresh(ActionEvent event) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\thislogin.json");
        Object obj = parser.parse(reader);


        JSONParser parserDetail = new JSONParser();
        JSONArray personDetail= (JSONArray) parserDetail.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\useraccount.json"));

        for (Object o : personDetail) {
            JSONObject person = (JSONObject) obj;
            JSONObject accountDetail = (JSONObject) o;
            String name = (String) person.get("Login");

            if (name.equals(accountDetail.get("Username"))) {
                String username= (String) accountDetail.get("Username");
                usernameText.setText(username);
                nameText.setText((String) accountDetail.get("Name"));
                Type.setText((String) accountDetail.get("UserType"));
                Gender.setText((String)accountDetail.get("Gender"));
                Contact.setText((String) accountDetail.get("Contact Number"));
                Email.setText((String) accountDetail.get("Email"));

                userdata.setUsername((String) accountDetail.get("Username"));
                userdata.setName((String) accountDetail.get("Name"));
                userdata.setContactPhone((String) accountDetail.get("Contact Number"));
                userdata.setEmail((String) accountDetail.get("Email"));
                userdata.setPassword((String) accountDetail.get("Password"));
                userdata.setUserType((String) accountDetail.get("UserType"));
            }
        }
    }

    public void setUser(User userdata) {
        this.userdata = userdata;
    }

    public void backToList(ActionEvent e) throws IOException {
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


}
