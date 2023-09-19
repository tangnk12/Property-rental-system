package com.example.property;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class ListController {


    @FXML
    private ComboBox<String> priceCondition;

    @FXML
    private ComboBox<String> facilityCondition;

    @FXML
    private ComboBox<String> propProject;

    @FXML
    private ComboBox<String> propType;

    @FXML
    private ComboBox<String> rateCondition;

    @FXML
    private ComboBox<String> statusCondition;


    @FXML
    private TextField propSearchDetail;

    @FXML
    private Button searchProp;
    @FXML
    private Button clearSearch;
    @FXML
    private Button rentProp;
    @FXML
    private Button returnProp;


    @FXML
    private GridPane gridProp;

    @FXML
    private AnchorPane userGrid;

    @FXML
    private ScrollPane scrollPane;


    //an arrayList to store all the properties
    ArrayList<Details> propertyJson = new ArrayList<>();

    ArrayList<String> propertyFeatures = new ArrayList<String>();
    ArrayList<String> propertyFacilities = new ArrayList<String>();

    private User userdata = new User();

    // get the number for how many features is selected for respective property
    private int sizeFeatures;
    // get the number for how many facilities is selected for respective property
    private int sizeFacilities;


    public void setClearSearch(ActionEvent event) {

        /*
        try{*/
        gridProp.getChildren().retainAll(gridProp.getChildren().get(0));
        gridProp.getChildren().remove(gridProp.getChildren().get(0));
        /*
        } catch (ArrayIndexOutOfBoundsException e1){
        }*/

        propType.getSelectionModel().select("No Filter");
        propProject.getSelectionModel().select("No Filter");
        facilityCondition.getSelectionModel().select("No Filter");
        priceCondition.getSelectionModel().select("No Filter");
        rateCondition.getSelectionModel().select("No Filter");
        if (userdata.getUserType().equals("Tenant")){
            statusCondition.setDisable(true);
            statusCondition.getSelectionModel().select("Active");
        } else {
            statusCondition.getSelectionModel().select("No Filter");
        }

        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < propertyJson.size(); i++) {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("Grid.fxml"));
                AnchorPane box = fxmlLoader1.load();

                GridController gridController = fxmlLoader1.getController();
                gridController.setUser(userdata);
                gridController.setData(propertyJson.get(i));
                gridController.setProperty(propertyJson.get(i));


                if (column == 3) {
                    column = 0;
                    ++row;
                }
                gridProp.add(box, column++, row);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setupListView() {

        try {
            if (propertyJson.size() != 0) {
                for (int i = 0; i < propertyJson.size(); i++) {
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
                        address, name, contact, features, facilities, sizeFeatures, sizeFacilities, imageSrc, post));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        propType.getItems().clear();
        propType.getItems().addAll("Condominium", "SingleStorey", "DoubleStorey", "Townhouse", "Bungalow", "No Filter");
        propType.getSelectionModel().select("No Filter");

        propProject.getItems().clear();
        propProject.getItems().addAll("Setia Eco Glades", "Symphony Hills", "Cybersouth", "Jacaranda", "Lakefront", "No Filter");
        propProject.getSelectionModel().select("No Filter");

        facilityCondition.getItems().clear();
        facilityCondition.getItems().addAll("24-hours Security", "Parking", "Swimming Pool", "BBQ", "Mini Market", "Gymnasium", "Jogging Track", "Playground", "Basketball Court", "Badminton Court", "No Filter");
        facilityCondition.getSelectionModel().select("No Filter");

        priceCondition.getItems().clear();
        priceCondition.getItems().addAll("Lowest To Highest", "Highest To Lowest", "No Filter");
        priceCondition.getSelectionModel().select("No Filter");

        rateCondition.getItems().clear();
        rateCondition.getItems().addAll("RM 0 - RM 2", ">RM 2", "No Filter");
        rateCondition.getSelectionModel().select("No Filter");

        statusCondition.getItems().clear();
        statusCondition.getItems().addAll("Active", "Inactive", "Owned", "No Filter");

        if (userdata.getUserType().equals("Tenant")){
            statusCondition.setDisable(true);
            statusCondition.getSelectionModel().select("Active");
        } else {
            statusCondition.setDisable(false);
            statusCondition.getSelectionModel().select("No Filter");
        }


        int column = 0;
        int row = 1;

        try {
            for (int i = 0; i < propertyJson.size(); i++) {
                FXMLLoader fxmlLoader1 = new FXMLLoader();
                fxmlLoader1.setLocation(getClass().getResource("Grid.fxml"));
                AnchorPane box = fxmlLoader1.load();

                GridController gridController = fxmlLoader1.getController();
                gridController.setUser(userdata);
                gridController.setData(propertyJson.get(i));
                gridController.setProperty(propertyJson.get(i));


                if (column == 3) {
                    column = 0;
                    ++row;
                }
                gridProp.add(box, column++, row);
                GridPane.setMargin(box, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*-------------------------------------------------------------------------------------------------------------------*/

        if ((userdata.getUserType()).equals("Tenant")) {

            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(getClass().getResource("tenantGrid.fxml"));
                AnchorPane boxGrid = fxmlLoader2.load();
                tenantViewController tenantController = fxmlLoader2.getController();

                userGrid.getChildren().setAll(boxGrid);
                tenantController.setUser(userdata);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (((userdata.getUserType()).equals("Agent")) || ((userdata.getUserType()).equals("Owner"))) {

            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(getClass().getResource("sellerGrid.fxml"));
                AnchorPane boxGrid = fxmlLoader2.load();
                sellerViewController sellerController = fxmlLoader2.getController();

                userGrid.getChildren().setAll(boxGrid);
                sellerController.setUser(userdata);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if ((userdata.getUserType()).equals("Admin")) {

            try {
                FXMLLoader fxmlLoader2 = new FXMLLoader();
                fxmlLoader2.setLocation(getClass().getResource("adminGrid.fxml"));
                AnchorPane boxGrid = fxmlLoader2.load();
                adminViewController adminController = fxmlLoader2.getController();

                userGrid.getChildren().setAll(boxGrid);
                adminController.setUser(userdata);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        /*-------------------------------------------------------------------------------------------------------------------*/
    }

    public void searchPropertyClicked(ActionEvent e) {

        String type = typeFilter();
        String priceOrder = sortPrice();
        String status = statusFilter();
        String rate = orderRentRate();
        /*
        try{*/
        gridProp.getChildren().retainAll(gridProp.getChildren().get(0));
        gridProp.getChildren().remove(gridProp.getChildren().get(0));
        /*
        } catch (ArrayIndexOutOfBoundsException e1){
        }*/

        int column = 0;
        int row = 1;

        ArrayList<Details> filteredPropertyList = new ArrayList<>();
        for (Details property : propertyJson) {
            filteredPropertyList.add(property);
        }

        Details tempProperty;
        int check;

        for (int i = 0; i < propertyJson.size(); i++) {
            tempProperty = propertyJson.get(i);
            check = 0;

            if (!(type).equals("nofilter")) {
                if (!(tempProperty.getPropertyType().equals(type))) {
                    check += 1;
                }
            }

            if (!(status).equals("nofilter")) {
                if (!(status).equals("owned")) {
                    if (!(userdata.getUserType().equals("Agent") || userdata.getUserType().equals("Owner"))) {
                        if (!(tempProperty.getStatus().equals(status))) {
                            check += 1;
                        }
                    } else {
                        if ((status).equals("Inactive")) {
                            if (!(tempProperty.getPost().equals(userdata.getName())) || !(tempProperty.getStatus().equals("Inactive"))) {
                                check += 1;
                            }
                        }
                    }
                } else {
                        System.out.println(userdata.getName());
                        System.out.println(tempProperty.getPost());
                        System.out.println("-----------");
                        if (!(tempProperty.getPost().equals(userdata.getName()))) {
                            check += 1;
                        }
                    }
                }

                if (!(rate).equals("nofilter")) {
                    double tempRate = Double.parseDouble(tempProperty.getRentalRate());
                    if (rate.equals("more2")) {
                        if (tempRate <= 2) {
                            check += 1;
                        }

                    } else if (rate.equals("less2")) {
                        if (tempRate > 2) {
                            check += 1;
                        }
                    }
                }

                if (!(facilityCondition.getValue()).equals("No Filter")) {
                    if (!(tempProperty.getFacilityList().contains(facilityCondition.getValue()))) {
                        check += 1;
                    }
                }

                if (!(propProject.getValue()).equals("No Filter")) {
                    if (!(tempProperty.getName().contains(propProject.getValue()))) {
                        System.out.println(propProject.getValue());
                        System.out.println(tempProperty.getName().contains(propProject.getValue()));
                        check += 1;
                    }
                }

                if (check > 0) {
                    filteredPropertyList.remove(tempProperty);
                }
            }

            if (!(priceOrder).equals("nofilter")) {
                if (priceOrder.equals("lowToHigh")) {
                    Collections.sort(filteredPropertyList, sortPropertyAsc);
                } else if (priceOrder.equals("highToLow")) {
                    Collections.sort(filteredPropertyList, sortPropertyDes);
                }
            }


            try {
                for (int i = 0; i < filteredPropertyList.size(); i++) {

                    FXMLLoader fxmlLoader1 = new FXMLLoader();
                    fxmlLoader1.setLocation(getClass().getResource("Grid.fxml"));
                    AnchorPane box = fxmlLoader1.load();

                    GridController gridController = fxmlLoader1.getController();
                    gridController.setUser(userdata);
                    gridController.setData(filteredPropertyList.get(i));
                    gridController.setProperty(filteredPropertyList.get(i));


                    if (column == 3) {
                        column = 0;
                        ++row;
                    }
                    gridProp.add(box, column++, row);
                    GridPane.setMargin(box, new Insets(10));
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }




    public String typeFilter(){
        String condition = "nofilter";

        if (propType.getValue().equals("Condominium")){
            condition = "condominium";
        } else if (propType.getValue().equals("SingleStorey")){
            condition = "singleStorey";
        } else if (propType.getValue().equals("DoubleStorey")){
            condition = "doubleStorey";
        } else if (propType.getValue().equals("Townhouse")){
            condition = "townHouse";
        } else if (propType.getValue().equals("Bungalow")){
            condition = "bungalow";
        } else if (propType.getValue().equals("No Filter")){
            condition = "nofilter";
        }
        return condition;
    }

    public String sortPrice(){
        String condition = "nofilter";

        if (priceCondition.getValue().equals("Lowest To Highest")){
            condition = "lowToHigh";
        } else if (priceCondition.getValue().equals("Highest To Lowest")){
            condition = "highToLow";
        }
        return condition;
    }

    public String orderRentRate(){
        String condition = "nofilter";

        if (rateCondition.getValue().equals("RM 0 - RM 2")){
            condition = "less2";
        } else if (rateCondition.getValue().equals(">RM 2")){
            condition = "more2";
        }
        return condition;
    }


    public String statusFilter(){
        String condition = "nofilter";

        if (statusCondition.getValue().equals("Active")){
            condition = "Active";
        } else if (statusCondition.getValue().equals("Inactive")){
            condition = "Inactive";
        } else if (statusCondition.getValue().equals("Owned")){
            condition = "owned";
        }
        return condition;
    }

    public static Comparator<Details> sortPropertyAsc = new Comparator<Details>() {
        @Override
        public int compare (Details property1, Details property2){
            int price1 = Integer.parseInt(property1.getRentalPrice());
            int price2 = Integer.parseInt(property2.getRentalPrice());
            return price1 - price2;
        }
    };


    public static Comparator<Details> sortPropertyDes = new Comparator<Details>() {
        @Override
        public int compare (Details property1, Details property2) {
            int price1 = Integer.parseInt(property1.getRentalPrice());
            int price2 = Integer.parseInt(property2.getRentalPrice());
            return price2 - price1;
        }
    };

    public void setUserdata(User userdata) throws IOException {
        this.userdata = userdata;
    }

}