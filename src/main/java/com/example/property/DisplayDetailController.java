package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayDetailController {

    @FXML
    private CheckBox aircon;

    @FXML
    private CheckBox badminton;

    @FXML
    private CheckBox balcony;

    @FXML
    private CheckBox basketball;

    @FXML
    private CheckBox bbq;

    @FXML
    private Label contact;

    @FXML
    private Button deleteProp;

    @FXML
    private Button editProp;

    @FXML
    private Button backMenuButton;

    @FXML
    private CheckBox fridge;

    @FXML
    private CheckBox gym;

    @FXML
    private CheckBox heater;

    @FXML
    private ImageView imageHouse;

    @FXML
    private CheckBox jogging;

    @FXML
    private CheckBox kitchenCabinet;

    @FXML
    private CheckBox market;

    @FXML
    private CheckBox parking;

    @FXML
    private CheckBox playground;

    @FXML
    private CheckBox pool;

    @FXML
    private TextArea propAddress;

    @FXML
    private Label propName;

    @FXML
    private Label propPrice;

    @FXML
    private Label propRentRate;

    @FXML
    private Label propSize;

    @FXML
    private Label propType;

    @FXML
    private Label propertyPoster;

    @FXML
    private Label numBed;

    @FXML
    private Label numBath;

    @FXML
    private CheckBox security;

    @FXML
    private CheckBox tv;

    @FXML
    private CheckBox wifi;

    @FXML
    private Label propStatus;

    private Details detail;

    private User userdata = new User();

    private String Ptype;
    private String Pstatus;
    private int sizeFeatures;
    private int sizeFacilities;

    ArrayList<Details> propertyJson = new ArrayList<>();
    ArrayList<String> propertyFeatures = new ArrayList<String>();
    ArrayList<String> propertyFacilities = new ArrayList<String>();


    // search propertyID in the json and display the property details for the particular property ID
    public void syncPropertyDetail(Details detail) throws IOException {

        Image image1 = new Image(getClass().getResourceAsStream(detail.getPropertyIMG()));
        imageHouse.setImage(image1);

        if(detail.getPropertyType().equals("condominium")){
            propType.setText("Condominium");
        }
        else if(detail.getPropertyType().equals("singleStorey")){
            propType.setText("Single Storey");
        }
        else if(detail.getPropertyType().equals("doubleStorey")){
            propType.setText("Double Storey");
        }
        else if(detail.getPropertyType().equals("townHouse")){
            propType.setText("townHouse");
        }
        else if(detail.getPropertyType().equals("bungalow")){
            propType.setText("Bungalow");
        }

        propStatus.setText(detail.getStatus());

        propSize.setText(detail.getSize());

        numBed.setText(detail.getBedroom().toString());

        numBath.setText(detail.getBathroom().toString());

        propPrice.setText(detail.getRentalPrice());
        propRentRate.setText(detail.getRentalRate());

        propertyPoster.setText(detail.getPost());
        contact.setText(detail.getContact());

        propAddress.setText(detail.getAddress());
        propName.setText(detail.getName());
        contact.setText(detail.getContact());
        for(int j =0; j < detail.getSizeFeatures();j++) {
            if (detail.getFeatures(j).equals(aircon.getText())){
                aircon.setSelected(true);
            }
            if (detail.getFeatures(j).equals(heater.getText())){
                heater.setSelected(true);
            }
            if (detail.getFeatures(j).equals(fridge.getText())){
                fridge.setSelected(true);
            }
            if (detail.getFeatures(j).equals(tv.getText())){
                tv.setSelected(true);
            }
            if (detail.getFeatures(j).equals(wifi.getText())){
                wifi.setSelected(true);
            }
            if (detail.getFeatures(j).equals(balcony.getText())){
                balcony.setSelected(true);
            }
            if (detail.getFeatures(j).equals(kitchenCabinet.getText())){
                kitchenCabinet.setSelected(true);
            }
        }
        for(int k =0; k<detail.getSizeFacilities();k++) {
            if (detail.getFacilities(k).equals(security.getText())){
                security.setSelected(true);
            }
            if (detail.getFacilities(k).equals(parking.getText())){
                parking.setSelected(true);
            }
            if (detail.getFacilities(k).equals(pool.getText())){
                pool.setSelected(true);
            }
            if (detail.getFacilities(k).equals(bbq.getText())){
                bbq.setSelected(true);
            }
            if (detail.getFacilities(k).equals(market.getText())){
                market.setSelected(true);
            }
            if (detail.getFacilities(k).equals(gym.getText())){
                gym.setSelected(true);
            }
            if (detail.getFacilities(k).equals(jogging.getText())){
                jogging.setSelected(true);
            }
            if (detail.getFacilities(k).equals(playground.getText())){
                playground.setSelected(true);
            }
            if (detail.getFacilities(k).equals(basketball.getText())){
                basketball.setSelected(true);
            }
            if (detail.getFacilities(k).equals(badminton.getText())) {
                badminton.setSelected(true);
            }


            if ((userdata.getUserType()).equals("Tenant")) {
                editProp.setDisable(true);
                deleteProp.setDisable(true);

            } else if ((userdata.getUserType()).equals("Agent") || (userdata.getUserType()).equals("Owner")){
                if (!(userdata.getName().equals(detail.getPost()))){
                    editProp.setDisable(true);
                    deleteProp.setDisable(true);
                }
            } else if ((userdata.getUserType()).equals("Tenant")) {
                editProp.setDisable(true);
            }
        }
        setProperty(detail);
    }

    public void editPropClicked(ActionEvent e) throws IOException, ParseException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditProperty.fxml"));
        Parent root = (Parent)loader.load();
        EditPropertyController controller = (EditPropertyController)loader.getController();

        controller.setUser(userdata);
        controller.setProperty(detail);
        controller.searchPropertyID(detail);

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();
    }

    public void deletePropClicked(ActionEvent e) throws IOException, ParseException {

        if (propertyJson.size() != 0) {
            for (int i = 0; i < propertyJson.size(); i++) {
                propertyJson.remove(i);
            }
        }

        getArrayPropertyJson();

        JSONArray obj = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONObject propertyDetails = new JSONObject();

        try {
            FileReader reader = new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json");
            obj = (JSONArray) parser.parse(reader);
            reader.close();
        } catch (FileNotFoundException e1) {
            System.out.println("File Not Found!");
        } catch (IOException e1) {
            System.out.println("IOException Occurred!");
        } catch (ParseException e1) {
            System.out.println("ParseException Occurred!");
        } catch (Exception e1) {
            System.out.println("Error Occurred!");
        }

        for (int i = 0; i < obj.size(); i++) {
            // detect if there's any changes
            if (detail.getPropertyID().equals(propertyJson.get(i).getPropertyID())) {
                obj.remove(i);
            }
        }

        try {
            FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json", false);
            file.write(obj.toJSONString());
            file.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

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

    public void backToMenu(ActionEvent e) throws IOException {
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

    public void getArrayPropertyJson() throws IOException, ParseException {
        if(propertyJson.size()!=0){
            for(int i =0; i< propertyJson.size(); i++){
                propertyJson.remove(i);
            }
        }
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
                    address, name, contact, features, facilities,sizeFeatures,sizeFacilities, imageSrc, post));
        }
    }

    public void setUser(User userdata) {
        this.userdata = userdata;
    }

    public void setProperty(Details detail) { this.detail = detail; }
}

