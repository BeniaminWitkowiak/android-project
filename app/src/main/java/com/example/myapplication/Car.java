package com.example.myapplication;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by ben_a_000 on 2016-05-08.
 */
public class Car implements Serializable{
    private int carID;
    private String brand;
    private String model;
    private double engineSize;
    private double averageConsumption;
    Bitmap photoNumber;

    public Car(int carID, String brand, String model, double engineSize, double averageConsumption, Bitmap photoNumber) {
        this.carID = carID;
        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.averageConsumption = averageConsumption;

        this.photoNumber = photoNumber;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public double getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(double averageConsumption) {
        this.averageConsumption = averageConsumption;
    }

    public Bitmap getPhotoNumber() {
        return photoNumber;
    }

    public void setPhotoNumber(Bitmap photoNumber) {
        this.photoNumber = photoNumber;
    }
}