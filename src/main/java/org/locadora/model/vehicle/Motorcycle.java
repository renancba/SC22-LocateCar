package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {

    private String cylinderCapacity;

    public Motorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String cylinderCapacity) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        this.cylinderCapacity = cylinderCapacity;
        super.rentalFee = rentalFee;
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
        return super.rentalFee;
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
