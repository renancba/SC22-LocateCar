package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Car extends Vehicle {

    private String transmission;

    public Car(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String transmission) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        this.transmission = transmission;
    }

    public Car(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String transmission, boolean isAvaliable) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        super.isAvaible = isAvaliable;
        this.transmission = transmission;
    }

    public JSONObject toJSONObject() {
        JSONObject vehicleObject = new JSONObject();
        vehicleObject.put("vehicleManufacturer", vehicleManufacturer);
        vehicleObject.put("vehicleModel", vehicleModel);
        vehicleObject.put("registrationPlate", registrationPlate);
        vehicleObject.put("isAvaible", isAvaible);
        vehicleObject.put("rentalFee", rentalFee);
        vehicleObject.put("transmission", transmission);
        return vehicleObject;
    }

    public void shortInfo() {
        System.out.println("MARCA: " + this.getVehicleManufacturer());
        System.out.println("MODELO: " + this.getVehicleModel());
    }

    public void completeInfo() {
        shortInfo();
        System.out.println("PLACA: " + this.getRegistrationPlate());
        System.out.println("PORTAS: " + this.transmission);
        System.out.println("DIÁRIA: " + this.getRentalFee());
        System.out.println("DISPONIBILIDADE: " + (this.getAvaible() ? "DISPONÍVEL" : "INDISPONÍVEL"));
    }

    public void setParticularity(String transmission) {
        this.transmission = transmission;
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
