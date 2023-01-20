package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {

    private String cylinderCapacity;
    public Motorcycle() {
    }

    public Motorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        super(vehicleManufacturer, vehicleModel, registrationPlate);
        super.rentalFee = new BigDecimal(100);
    }

    public JSONObject toJSONObject() {
        JSONObject vehicleObject = new JSONObject();
        vehicleObject.put("vehicleManufacturer", vehicleManufacturer);
        vehicleObject.put("vehicleModel", vehicleModel);
        vehicleObject.put("registrationPlate", registrationPlate);
        vehicleObject.put("isAvaible", isAvaible);
        vehicleObject.put("rentalFee", rentalFee);
        return vehicleObject;
    }

    @Override
    public BigDecimal getRentalFee() {
        return super.rentalFee = new BigDecimal(100);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
