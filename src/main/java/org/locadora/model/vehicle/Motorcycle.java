package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {

    private String cylinderCapacity;

    public Motorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String cylinderCapacity) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        this.cylinderCapacity = cylinderCapacity;
    }

    public Motorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String cylinderCapacity, boolean isAvaliable) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        super.isAvaible = isAvaliable;
        this.cylinderCapacity = cylinderCapacity;
    }

    public JSONObject toJSONObject() {
        JSONObject vehicleObject = new JSONObject();
        vehicleObject.put("vehicleManufacturer", vehicleManufacturer);
        vehicleObject.put("vehicleModel", vehicleModel);
        vehicleObject.put("registrationPlate", registrationPlate);
        vehicleObject.put("isAvaible", isAvaible);
        vehicleObject.put("rentalFee", rentalFee);
        vehicleObject.put("cylinderCapacity", cylinderCapacity);
        return vehicleObject;
    }

    public void shortInfo() {
        System.out.println("MARCA: " + this.getVehicleManufacturer());
        System.out.println("MODELO: " + this.getVehicleModel());
    }

    public void completeInfo() {
        shortInfo();
        System.out.println("PLACA: " + this.getRegistrationPlate());
        System.out.println("PORTAS: " + this.cylinderCapacity);
        System.out.println("DIÁRIA: " + this.getRentalFee());
        System.out.println("DISPONIBILIDADE: " + (super.isAvaible ? "DISPONÍVEL" : "INDISPONÍVEL"));
    }

    public void setParticularity(String cylinderCapacity) {
        this.cylinderCapacity = cylinderCapacity;
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
