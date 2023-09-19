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
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPropertyController implements Initializable {
    @FXML
    private TextField propertyIDTextField;
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
    private TextField sizeTxtField;
    @FXML
    private Spinner<Integer> bedroomSpinner;
    @FXML
    private Spinner<Integer> bathroomSpinner;
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
    private Button saveButton;
    @FXML
    private Button editButton;
    @FXML
    private Button upload;
    @FXML
    private Label showID;
    @FXML
    private AnchorPane uploadScreen;
    @FXML
    private ImageView imageHouse;


    private String Ptype;
    private String Pstatus;

    String imgFile;
    String tempImg;

    private int sizeFeatures;
    private int sizeFacilities;
    private Details detail;

    ArrayList<Details> propertyJson = new ArrayList<>();
    ArrayList<String> propertyFeatures = new ArrayList<String>();
    ArrayList<String> propertyFacilities = new ArrayList<String>();

    private User userdata = new User();

    //private Details currentProperty = new Details();

    // get property details from json and put all the records into an arrayList called propertyJson
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

    public void getPropertyFacilities(){
        if(propertyFacilities.size()!=0){
            for(int i =0; i<propertyFacilities.size();i++){
                propertyFacilities.remove(i);
            }
        }
        if(security.isSelected()){
            if(propertyFacilities.contains(security.getText())) {
            }
            else{
                propertyFacilities.add(security.getText());
            }
        }
        if(parking.isSelected()){
            if(propertyFacilities.contains(parking.getText())) {
            }
            else{
                propertyFacilities.add(parking.getText());
            }
        }
        if(pool.isSelected()){
            if(propertyFacilities.contains(pool.getText())) {
            }
            else{
                propertyFacilities.add(pool.getText());
            }
        }
        if(bbq.isSelected()){
            if(propertyFacilities.contains(bbq.getText())) {
            }
            else{
                propertyFacilities.add(bbq.getText());
            }
        }
        if(market.isSelected()){
            if(propertyFacilities.contains(market.getText())) {
            }
            else{
                propertyFacilities.add(market.getText());
            }
        }
        if(gym.isSelected()){
            if(propertyFacilities.contains(gym.getText())) {
            }
            else{
                propertyFacilities.add(gym.getText());
            }
        }
        if(jogging.isSelected()){
            if(propertyFacilities.contains(jogging.getText())) {
            }
            else{
                propertyFacilities.add(jogging.getText());
            }
        }
        if(playground.isSelected()){
            if(propertyFacilities.contains(playground.getText())) {
            }
            else{
                propertyFacilities.add(playground.getText());
            }
        }
        if(basketball.isSelected()){
            if(propertyFacilities.contains(basketball.getText())) {
            }
            else{
                propertyFacilities.add(basketball.getText());
            }
        }
        if(badminton.isSelected()){
            if(propertyFacilities.contains(badminton.getText())) {
            }
            else{
                propertyFacilities.add(badminton.getText());
            }
        }
    }

    public void getPropertyFeatures(){
        if(propertyFeatures.size()!=0){
            for(int i =0; i<propertyFeatures.size();i++){
                propertyFeatures.remove(i);
            }
        }
        if(aircon.isSelected()){
            if(propertyFeatures.contains(aircon.getText())) {

            }
            else{
                propertyFeatures.add(aircon.getText());
            }
        }
        if(heater.isSelected()){
            if(propertyFeatures.contains(heater.getText())) {

            }
            else{
                propertyFeatures.add(heater.getText());
            }
        }
        if(fridge.isSelected()){
            if(propertyFeatures.contains(fridge.getText())) {

            }
            else{
                propertyFeatures.add(fridge.getText());
            }
        }
        if(tv.isSelected()){
            if(propertyFeatures.contains(tv.getText())) {

            }
            else{
                propertyFeatures.add(tv.getText());
            }
        }
        if(wifi.isSelected()){
            if(propertyFeatures.contains(wifi.getText())) {

            }
            else{
                propertyFeatures.add(wifi.getText());
            }
        }
        if(balcony.isSelected()){
            if(propertyFeatures.contains(balcony.getText())) {

            }
            else{
                propertyFeatures.add(balcony.getText());
            }
        }
        if(kitchenCabinet.isSelected()){
            if(propertyFeatures.contains(kitchenCabinet.getText())) {

            }
            else{
                propertyFeatures.add(kitchenCabinet.getText());
            }
        }
    }


    // clear the content of the text field and selection of the radio button to default state
    public void clearSelection(){

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
    }

    // set the status of the form either can edit or cannot edit
    public void setFormStatus(boolean status){
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

    // get propertyType in the editing mode
    public void getPropertyType(ActionEvent actionEvent) {
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

    // get status in the editing mode
    public void getStatus(ActionEvent actionEvent) {
        if(active.isSelected()){
            Pstatus ="Active";
        }
        else{
            Pstatus = "Inactive";
        }
    }

    // get status and propertyType in the editing mode
    public void getStatusAndPropertyType(){
        if(active.isSelected()){
            Pstatus ="Active";
        }
        else{
            Pstatus = "Inactive";
        }

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

    // search propertyID in the json and display the property details for the particular property ID
    public void searchPropertyID(Details detail) throws IOException, ParseException {
        clearSelection();
        tempImg = detail.getPropertyIMG();
        /*
        if(propertyJson.size()!=0){
            for(int i =0; i<propertyJson.size();i++){
                propertyJson.remove(i);
            }
        }
        getArrayPropertyJson();
        for (int i = 0; i < propertyJson.size(); i++){
            if (propertyIDTextField.getText().equals(detail.getPropertyID())) { */
                if(detail.getPropertyType().equals("condominium")){
                    condominium.setSelected(true);
                }
                else if(detail.getPropertyType().equals("singleStorey")){
                    singleStorey.setSelected(true);
                }
                else if(detail.getPropertyType().equals("doubleStorey")){
                    doubleStorey.setSelected(true);
                }
                else if(detail.getPropertyType().equals("townHouse")){
                    townhouse.setSelected(true);
                }
                else if(detail.getPropertyType().equals("bungalow")){
                    bungalow.setSelected(true);
                }
                sizeTxtField.setText(detail.getSize());
                long number = detail.getBedroom();
                int spinnerNumber = (int) number;
                bedroomSpinner.increment(spinnerNumber-1);
                number = detail.getBathroom();
                spinnerNumber = (int) number;
                bathroomSpinner.increment(spinnerNumber-1);
                priceTxtField.setText(detail.getRentalPrice());
                rateTxtField.setText(detail.getRentalRate());
                if(detail.getStatus().equals("Active")){
                    active.setSelected(true);
                }
                else {
                    inactive.setSelected(true);
                }
                addressTxtField.setText(detail.getAddress());
                nameTxtField.setText(detail.getName());
                contactTxtField.setText(detail.getContact());
                for(int j =0; j<detail.getSizeFeatures();j++) {
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
                }

                imageHouse.setImage(new Image(getClass().getResourceAsStream((String) detail.getPropertyIMG())));
                upload.setDisable(true);
                setFormStatus(true);
                editButton.setDisable(false);
                saveButton.setDisable(true);
            //}
        //}

    }

    public void editPropertyDetails(ActionEvent actionEvent) {
        upload.setDisable(false);
        editButton.setDisable(true);
        setFormStatus(false);
        saveButton.setDisable(false);
    }

    // save the edited property details to json
    public void saveToJson(ActionEvent actionEvent) throws IOException, ParseException {
        JSONArray featuresList = new JSONArray(); // array to get selection of features and save it into json
        JSONArray facilitiesList = new JSONArray(); // array to get selection of facilities and save it into json
        upload.setDisable(true);
        setFormStatus(true);
        saveButton.setDisable(true);
        if (propertyFeatures.size() != 0) {
            for (int i = 0; i < propertyFeatures.size(); i++) {
                propertyFeatures.remove(i);
            }
        }
        if (propertyFacilities.size() != 0) {
            for (int i = 0; i < propertyFacilities.size(); i++) {
                propertyFacilities.remove(i);
            }
        }
        getPropertyFeatures();
        getPropertyFacilities();
        getStatusAndPropertyType();
        if (propertyJson.size() != 0) {
            for (int i = 0; i < propertyJson.size(); i++) {
                propertyJson.remove(i);
            }
        }
        if (featuresList.size() != 0) {
            for (int i = 0; i < featuresList.size(); i++) {
                featuresList.remove(i);
            }
        }
        if (facilitiesList.size() != 0) {
            for (int i = 0; i < facilitiesList.size(); i++) {
                facilitiesList.remove(i);
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
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
        } catch (IOException e) {
            System.out.println("IOException Occurred!");
        } catch (ParseException e) {
            System.out.println("ParseException Occurred!");
        } catch (Exception e) {
            System.out.println("Error Occurred!");
        }

        for (int i = 0; i < obj.size(); i++) {
            // detect if there's any changes
            if (detail.getPropertyID().equals(propertyJson.get(i).getPropertyID())) {
                obj.remove(i);
                propertyDetails.put("propertyID", detail.getPropertyID());
                propertyDetails.put("propertyType", Ptype);
                propertyDetails.put("size", sizeTxtField.getText());
                propertyDetails.put("bedroom", bedroomSpinner.getValue());
                propertyDetails.put("bathroom", bathroomSpinner.getValue());
                propertyDetails.put("rentalPrice", priceTxtField.getText());
                propertyDetails.put("rentalRate", rateTxtField.getText());
                propertyDetails.put("status", Pstatus);
                propertyDetails.put("address", addressTxtField.getText());
                propertyDetails.put("name", nameTxtField.getText());
                propertyDetails.put("contactNumber", contactTxtField.getText());
                propertyDetails.put("post", userdata.getName());

                if (imgFile == null){
                    imgFile = tempImg;
                }

                propertyDetails.put("imageSrc", imgFile);
                for (int j = 0; j < propertyFeatures.size(); j++) {
                    featuresList.add(propertyFeatures.get(j));
                }
                propertyDetails.put("propertyFeatures", featuresList);

                for (int k = 0; k < propertyFacilities.size(); k++) {
                    facilitiesList.add(propertyFacilities.get(k));
                }
                propertyDetails.put("propertyFacilities", facilitiesList);
                obj.add(propertyDetails);

                Long bedroom = new Long(bedroomSpinner.getValue());
                Long bathroom = new Long(bathroomSpinner.getValue());
                detail = new Details(detail.getPropertyID(), Ptype, sizeTxtField.getText(), bedroom, bathroom,
                        priceTxtField.getText(), rateTxtField.getText(), Pstatus, addressTxtField.getText(), nameTxtField.getText(), contactTxtField.getText(),
                        featuresList,facilitiesList, featuresList.size(), facilitiesList.size(), imgFile,userdata.getName());
            }
        }

        try {
            FileWriter file = new FileWriter("C:\\Users\\Dell\\Downloads\\Property\\src\\main\\java\\com\\example\\property\\Properties.json", false);
            file.write(obj.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cancelEdit (ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("DisplayProperty.fxml"));
        Parent root = (Parent) loader.load();

        DisplayDetailController displaycontroller = (DisplayDetailController) loader.getController();

        displaycontroller.setUser(userdata);
        displaycontroller.syncPropertyDetail(detail);


        Scene sceneView = new Scene(root);
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        stage.setScene(sceneView);
        stage.show();

    }


    @FXML
    public void getImgSrc(ActionEvent event) {
        File initialFile = new File(System.getProperty("resources"), "./com/example/property");
        if (!initialFile.exists()) {
            initialFile.mkdirs();
        }

        FileChooser imgChooser = new FileChooser();

        imgChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );

        Stage stage2 = (Stage) uploadScreen.getScene().getWindow();
        imgChooser.setInitialDirectory(initialFile);

        File img = imgChooser.showOpenDialog(stage2);

        if (img != null) {
            imgFile = img.getName();

            imageHouse.setImage(new Image(getClass().getResourceAsStream(imgFile)));
        }
        else {
            imageHouse.setImage(new Image(getClass().getResourceAsStream("noimage.png")));
        }
    }


    public void setUser(User userdata) {
        this.userdata = userdata;
    }


    public void setProperty(Details detail) {
        this.detail = detail;

        showID.setText(detail.getPropertyID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> bedroomNumber = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        bedroomNumber.setValue(1);
        bedroomSpinner.setValueFactory(bedroomNumber);
        SpinnerValueFactory<Integer> bathroomNumber = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        bathroomNumber.setValue(1);
        bathroomSpinner.setValueFactory(bathroomNumber);
    }
}
