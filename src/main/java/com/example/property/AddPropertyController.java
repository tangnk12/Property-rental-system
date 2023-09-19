package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AddPropertyController implements Initializable {

    @FXML
    private RadioButton condominium;
    @FXML
    private RadioButton singleStorey;
    @FXML
    private RadioButton doubleStorey;
    @FXML
    private RadioButton townhouse;
    @FXML
    private RadioButton bungalow;
    @FXML
    private Button generateButton;
    @FXML
    private Label propertyIDLabel;
    @FXML
    private TextField sizeTxtField;
    @FXML
    private Spinner<Integer> bedroomSpinner;
    @FXML
    private Spinner<Integer> bathroomSpinner;
    //int initialValue = 1;
    @FXML
    private TextField priceTxtField;
    @FXML
    private TextField rateTxtField;
    @FXML
    private TextField addressTxtField;
    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField contactTxtField;
    @FXML
    private CheckBox aircon;
    @FXML
    private CheckBox heater;
    @FXML
    private CheckBox fridge;
    @FXML
    private CheckBox tv;
    @FXML
    private CheckBox wifi;
    @FXML
    private CheckBox balcony;
    @FXML
    private CheckBox kitchenCabinet;
    @FXML
    private CheckBox security;
    @FXML
    private CheckBox parking;
    @FXML
    private CheckBox pool;
    @FXML
    private CheckBox bbq;
    @FXML
    private CheckBox market;
    @FXML
    private CheckBox gym;
    @FXML
    private CheckBox jogging;
    @FXML
    private CheckBox playground;
    @FXML
    private CheckBox basketball;
    @FXML
    private CheckBox badminton;
    @FXML
    private RadioButton active;
    @FXML
    private RadioButton inactive;
    @FXML
    private Button upload;
    @FXML
    private ImageView imageHouse;
    @FXML
    private AnchorPane addScreen;


    private User userdata = new User();

    String imgFile;
    String propertyID;
    String Ptype;
    String Pstatus;

    ArrayList<String> propertyFeatures = new ArrayList<String>();
    ArrayList<String> propertyFacilities = new ArrayList<String>();

    // get the number for how many features is selected for respective property
    private int sizeFeatures;
    // get the number for how many facilities is selected for respective property
    private int sizeFacilities;

    //an arrayList to store all the properties
    ArrayList<Details> propertyJson = new ArrayList<>();


    // get property details from json and put all the records into an arrayList called propertyJson
    public void getArrayPropertyJson() throws IOException, org.json.simple.parser.ParseException {

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



    // set the status of the form either can edit or cannot edit
    public void setFormStatus(boolean status){
        upload.setDisable(status);
        condominium.setDisable(status);
        singleStorey.setDisable(status);
        doubleStorey.setDisable(status);
        townhouse.setDisable(status);
        bungalow.setDisable(status);
        sizeTxtField.setDisable(status);
        bedroomSpinner.setDisable(status);
        bathroomSpinner.setDisable(status);
        priceTxtField.setDisable(status);
        rateTxtField.setDisable(status);
        active.setDisable(status);
        inactive.setDisable(status);
        addressTxtField.setDisable(status);
        nameTxtField.setDisable(status);
        contactTxtField.setDisable(status);
        aircon.setDisable(status);
        heater.setDisable(status);
        fridge.setDisable(status);
        tv.setDisable(status);
        wifi.setDisable(status);
        balcony.setDisable(status);
        kitchenCabinet.setDisable(status);
        security.setDisable(status);
        parking.setDisable(status);
        pool.setDisable(status);
        bbq.setDisable(status);
        market.setDisable(status);
        gym.setDisable(status);
        jogging.setDisable(status);
        playground.setDisable(status);
        basketball.setDisable(status);
        badminton.setDisable(status);

    }

    // to get which facilities have been selected and put it in arrayList called property facilities
    public void getPropertyFacilities(){
        if(security.isSelected()){
            propertyFacilities.add(security.getText());
        }
        if(parking.isSelected()){
            propertyFacilities.add(parking.getText());
        }
        if(pool.isSelected()){
            propertyFacilities.add(pool.getText());
        }
        if(bbq.isSelected()){
            propertyFacilities.add(bbq.getText());
        }
        if(market.isSelected()){
            propertyFacilities.add(market.getText());
        }
        if(gym.isSelected()){
            propertyFacilities.add(gym.getText());
        }
        if(jogging.isSelected()){
            propertyFacilities.add(jogging.getText());
        }
        if(playground.isSelected()){
            propertyFacilities.add(playground.getText());
        }
        if(basketball.isSelected()){
            propertyFacilities.add(basketball.getText());
        }
        if(badminton.isSelected()){
            propertyFacilities.add(badminton.getText());
        }
    }

    // to get which features have been selected and put it in arrayList called property features
    public void getPropertyFeatures(){
        if(aircon.isSelected()){
            propertyFeatures.add(aircon.getText());
        }
        if(heater.isSelected()){
            propertyFeatures.add(heater.getText());
        }
        if(fridge.isSelected()){
            propertyFeatures.add(fridge.getText());
        }
        if(tv.isSelected()){
            propertyFeatures.add(tv.getText());
        }
        if(wifi.isSelected()){
            propertyFeatures.add(wifi.getText());
        }
        if(balcony.isSelected()){
            propertyFeatures.add(balcony.getText());
        }
        if(kitchenCabinet.isSelected()){
            propertyFeatures.add(kitchenCabinet.getText());
        }
    }

    // get property type
    public void getPropertyType(ActionEvent event){

        if(condominium.isSelected()){
            Ptype = "condominium";
        }
        else if(singleStorey.isSelected()){
            Ptype = "singleStorey";
        }
        else if(doubleStorey.isSelected()){
            Ptype = "doubleStorey";
        }
        else if(townhouse.isSelected()){
            Ptype = "townHouse";
        }
        else if(bungalow.isSelected()){
            Ptype = "bungalow";
        }
    }

    // to acquire propertyID
    public void getPropertyID(ActionEvent event) throws IOException, org.json.simple.parser.ParseException {

        getArrayPropertyJson();
        generateButton.setDisable(true);
        propertyID= getRandAlphaNumString(6);
        boolean checkID = true;

        do {
            if (propertyJson.size() != 0){
                for (Details details : propertyJson) {
                    if (details.getPropertyID().equals(propertyID)) {
                        propertyID = getRandAlphaNumString(6);
                        break;
                    } else {
                        checkID = false;
                    }
                }
            } else { checkID = false; }
        }while(checkID);
        propertyIDLabel.setText(propertyID);
    }

    //get the property status whether active or inactive
    public void getStatus(ActionEvent event){
        if(active.isSelected()){
            Pstatus = "Active";
        }
        else if(inactive.isSelected()){
            Pstatus = "Inactive";
        }
    }

    //randomize the property id
    public static String getRandAlphaNumString(int n){

        String randAlphaNumString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                +"0123456789";
        StringBuilder sb = new StringBuilder(n);

        for(int i=0; i<n;i++){
            int index = (int)(randAlphaNumString.length()*Math.random());
            sb.append(randAlphaNumString.charAt(index));
        }
        return sb.toString();
    }


    public void submitProperty(ActionEvent event) throws ParseException {
        getPropertyFeatures(); //get feature
        getPropertyFacilities(); //get facility
        submitProperty(); // save into property and write into json

    }

    public void submitProperty() throws ParseException {
        FileWriter file = null;
        try {
            JSONParser parser = new JSONParser();

            Object size = parser.parse(new FileReader("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json"));
            JSONArray propertyList = (JSONArray)size;
            JSONObject propertyDetails = new JSONObject();
            JSONArray featuresList = new JSONArray(); // array to get selection of features and save it into json
            JSONArray facilitiesList = new JSONArray(); // array to get selection of facilities and save it into json

            propertyDetails.put("propertyID", propertyID);
            propertyDetails.put("propertyType",Ptype);
            propertyDetails.put("size", sizeTxtField.getText());
            propertyDetails.put("bedroom",bedroomSpinner.getValue());
            propertyDetails.put("bathroom",bathroomSpinner.getValue());
            propertyDetails.put("rentalPrice", priceTxtField.getText());
            propertyDetails.put("rentalRate", rateTxtField.getText());
            propertyDetails.put("status",Pstatus);
            propertyDetails.put("address", addressTxtField.getText());
            propertyDetails.put("name", nameTxtField.getText());
            propertyDetails.put("contactNumber", contactTxtField.getText());

            for(int i =0; i< propertyFeatures.size(); i++) {
                featuresList.add(propertyFeatures.get(i));
            }
            propertyDetails.put("propertyFeatures",featuresList);

            for(int i =0; i< propertyFacilities.size(); i++) {
                facilitiesList.add(propertyFacilities.get(i));
            }
            propertyDetails.put("propertyFacilities",facilitiesList);
            propertyDetails.put("imageSrc", imgFile);
            propertyDetails.put("post", userdata.getName());

            propertyList.add(propertyDetails);
            file=new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json");
            file.write(propertyList.toJSONString()); //rewriting back to json

        }catch (IOException | org.json.simple.parser.ParseException e){

            e.printStackTrace();

        }finally {

            try {
                file.flush();
                file.close();

            }catch (IOException e){

                e.printStackTrace();

            }
        }
    }

    // clear the content of the text field and selection of the radio button to default state
    public void clearSelection(ActionEvent event){

        condominium.setSelected(false);
        singleStorey.setSelected(false);
        doubleStorey.setSelected(false);
        townhouse.setSelected(false);
        bungalow.setSelected(false);
        condominium.setSelected(false);
        sizeTxtField.setText(null);
        priceTxtField.setText(null);
        rateTxtField.setText(null);
        active.setSelected(false);
        inactive.setSelected(false);
        addressTxtField.setText(null);
        nameTxtField.setText(null);
        contactTxtField.setText(null);
        aircon.setSelected(false);
        heater.setSelected(false);
        fridge.setSelected(false);
        tv.setSelected(false);
        wifi.setSelected(false);
        balcony.setSelected(false);
        kitchenCabinet.setSelected(false);
        security.setSelected(false);
        parking.setSelected(false);
        pool.setSelected(false);
        bbq.setSelected(false);
        market.setSelected(false);
        gym.setSelected(false);
        jogging.setSelected(false);
        playground.setSelected(false);
        basketball.setSelected(false);
        badminton.setSelected(false);
        bedroomSpinner.decrement(bedroomSpinner.getValue());
        bathroomSpinner.decrement(bathroomSpinner.getValue());

        imageHouse.setImage(new Image(getClass().getResourceAsStream("noimage.PNG")));
    }

    @FXML
    public void getImgSrc(ActionEvent event) {
        File initialFile = new File(System.getProperty("resources"), ".com/example/property");
        if (!initialFile.exists()) {
            initialFile.mkdirs();
        }

        FileChooser imgChooser = new FileChooser();

        imgChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );

        Stage stage1 = (Stage) addScreen.getScene().getWindow();
        imgChooser.setInitialDirectory(initialFile);

        File img = imgChooser.showOpenDialog(stage1);

        if (img != null) {
            imgFile = img.getName();

            imageHouse.setImage(new Image(getClass().getResourceAsStream(imgFile)));
        }
        else {
            imageHouse.setImage(new Image(getClass().getResourceAsStream("noimage.png")));
        }
    }


    public void cancelAdd(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("List.fxml"));
        Parent root = (Parent)loader.load();

        ListController listcontroller = (ListController)loader.getController();

        listcontroller.setUserdata(userdata); //passing userdata to list view
        listcontroller.setupListView();

        Scene sceneView = new Scene(root);
        Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }

    public void setUser(User userdata) {
        this.userdata = userdata;
    }

    // this one is for the spinner
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> bedroomNumber = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        bedroomNumber.setValue(1);
        bedroomSpinner.setValueFactory(bedroomNumber);
        SpinnerValueFactory<Integer> bathroomNumber = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        bathroomNumber.setValue(1);
        bathroomSpinner.setValueFactory(bathroomNumber);

        imageHouse.setImage(new Image(getClass().getResourceAsStream("noimage.PNG")));
    }

}


