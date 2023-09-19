package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GridController {

    @FXML
    private Label gridAddress;

    @FXML
    private Label gridStatus;

    @FXML
    private Label gridRentRate;

    @FXML
    private Label gridName;

    @FXML
    private ImageView gridPic;

    @FXML
    private Label gridPrice;

    private Details detail;

    @FXML
    private Button gridDetail;

    private User userdata = new User();
    String gridID;

    private String Ptype;
    private String Pstatus;
    private int sizeFeatures;
    private int sizeFacilities;
    ArrayList<Details> propertyJson = new ArrayList<>();
    ArrayList<String> propertyFeatures = new ArrayList<String>();
    ArrayList<String> propertyFacilities = new ArrayList<String>();


    public void setData(Details detail) {
        Image image1 = new Image(getClass().getResourceAsStream(detail.getPropertyIMG()));
        gridPic.setImage(image1);

        gridID = detail.getPropertyID();
        gridName.setText(detail.getName());
        gridStatus.setText(detail.getStatus());
        gridRentRate.setText(detail.getRentalRate());
        gridAddress.setText(detail.getAddress());
        gridPrice.setText(detail.getRentalPrice());

    }


    public void displayProperty(ActionEvent e) throws IOException, ParseException {

        if(propertyJson.size()!=0){
            for(int i =0; i< propertyJson.size(); i++){
                propertyJson.remove(i);
            }
        }

        /*-------------------------------------------------------------------------------------------------------------------*/
        JSONParser parser = new JSONParser();
        JSONArray obj = (JSONArray) parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json"));
        for (Object o : obj) {
            JSONObject details = (JSONObject) o;
            String propertyID = (String) details.get("propertyID");
            String propertyType = (String) details.get("propertyType");
            String size = (String) details.get("size");
            Long bedroom = (Long) details.get("bedroom");
            Long bathroom = (Long) details.get("bathroom");
            String rentalPrice = (String) details.get("rentalPrice");
            String rentalRate = (String) details.get("rentalRate");
            String status = (String) details.get("status");
            String address = (String) details.get("address");
            String name = (String) details.get("name");
            String contact = (String) details.get("contactNumber");
            ArrayList<String> features = (ArrayList<String>) details.get("propertyFeatures");
            ArrayList<String> facilities = (ArrayList<String>) details.get("propertyFacilities");
            sizeFeatures = features.size();
            sizeFacilities = facilities.size();
            String imageSrc = (String) details.get("imageSrc");
            String post = (String) details.get("post");

            propertyJson.add(new Details(propertyID, propertyType, size, bedroom, bathroom, rentalPrice, rentalRate, status,
                    address, name, contact, features, facilities, sizeFeatures, sizeFacilities, imageSrc, post));
        }
        /*-------------------------------------------------------------------------------------------------------------------*/

        if ((userdata.getUserType()).equals("Tenant")) {
            for (int i = 0; i < propertyJson.size(); i++) {
                if ((gridID.equals(propertyJson.get(i).getPropertyID())) && (propertyJson.get(i).getStatus().equals("Active"))) {
                    detail = propertyJson.get(i);
                }
            }

        } else if ((userdata.getUserType()).equals("Agent") || (userdata.getUserType()).equals("Owner")){
            for (int i = 0; i < propertyJson.size(); i++) {
                if ((((gridID).equals(propertyJson.get(i).getPropertyID())) && (propertyJson.get(i).getStatus().equals("Active"))) ||
                    (((gridID).equals(propertyJson.get(i).getPropertyID())) && (propertyJson.get(i).getPost().equals(userdata.getUsername())))) {
                    detail = propertyJson.get(i);
                }
            }

        } else if ((userdata.getUserType()).equals("Admin")){
            for (int i = 0; i < propertyJson.size(); i++) {
                if ((gridID).equals(propertyJson.get(i).getPropertyID())){
                    detail = propertyJson.get(i);
                }
            }
        }

        /*-------------------------------------------------------------------------------------------------------------------*/

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DisplayProperty.fxml"));
        Parent root = (Parent) loader.load();

        DisplayDetailController controller = (DisplayDetailController) loader.getController();
        controller.setUser(userdata);
        controller.syncPropertyDetail(detail);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void setProperty(Details detail){
        this.detail = detail;
    }

    public void setUser(User userdata){
        this.userdata = userdata;
    }
}
