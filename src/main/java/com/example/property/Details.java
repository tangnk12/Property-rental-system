package com.example.property;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Details {
    private String propertyID;
    private String propertyType;
    private String size;
    private Long bedroom;
    private Long bathroom;
    private String rentalPrice;
    private String rentalRate;
    private String status;
    private String address;
    private String name;
    private String contact;
    private ArrayList<String> features;
    private ArrayList<String> facilities;
    private int sizeFeatures;
    private int sizeFacilities;
    private String propertyIMG;
    private String post;

    public Details() {};

    public Details(String propertyID, String propertyType, String size, Long bedroom,Long bathroom,
                   String rentalPrice, String rentalRate,String status,String address,String name,String contact,
                   ArrayList<String> features,ArrayList<String> facilities,int sizeFeatures, int sizeFacilities, String propertyIMG, String post) {
        this.propertyID = propertyID;
        this.propertyType = propertyType;
        this.size = size;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.rentalPrice = rentalPrice;
        this.rentalRate = rentalRate;
        this.status = status;
        this.address = address;
        this.name = name;
        this.contact = contact;
        this.features = features;
        this.facilities = facilities;
        this.sizeFeatures = sizeFeatures;
        this.sizeFacilities = sizeFacilities;
        this.propertyIMG = propertyIMG;
        this.post = post;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public String getSize() {
        return size;
    }

    public Long getBedroom() {
        return bedroom;
    }

    public Long getBathroom() {
        return bathroom;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public String getRentalRate() {
        return rentalRate;
    }

    public String getStatus(){
        return  status;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getFeatures(int n) {
        return features.get(n);
    }

    public String getFacilities(int n) {
        return facilities.get(n);
    }

    public String getPropertyIMG() {
        return propertyIMG;
    }

    public ArrayList<String> getFacilityList(){ return facilities; }

    public ArrayList<String> getFeaturesList(){ return features; }

    public int getSizeFeatures() {
        return sizeFeatures;
    }

    public int getSizeFacilities() {
        return sizeFacilities;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setRentalRate(String rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setPropertyIMG(String propertyIMG) {
        this.propertyIMG = propertyIMG;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}