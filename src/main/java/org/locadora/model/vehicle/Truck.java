package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Truck extends Vehicle {

    String numberOfAxles;

    public Truck(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String numberOfAxles) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        this.numberOfAxles = numberOfAxles;
    }

    public Truck(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String numberOfAxles, boolean isAvaliable) {
        super(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee);
        super.isAvaible = isAvaliable;
        this.numberOfAxles = numberOfAxles;
    }

    public JSONObject toJSONObject() {
        JSONObject vehicleObject = new JSONObject();
        vehicleObject.put("vehicleManufacturer", vehicleManufacturer);
        vehicleObject.put("vehicleModel", vehicleModel);
        vehicleObject.put("registrationPlate", registrationPlate);
        vehicleObject.put("isAvaible", isAvaible);
        vehicleObject.put("rentalFee", rentalFee);
        vehicleObject.put("numberOfAxles", numberOfAxles);
        //TODO: FALTARIA O NUMBEROFAXLES AQUI?
        return vehicleObject;
    }

    public void shortInfo() {
        System.out.println(" MARCA: " + this.getVehicleManufacturer());
        System.out.println(" MODELO: " + this.getVehicleModel());
    }

    public void completeInfo() {
        shortInfo();
        System.out.println(" PLACA: " + this.getRegistrationPlate());
        System.out.println(" PORTAS: " + this.numberOfAxles);
        System.out.println(" DIÁRIA: " + this.getRentalFee());
        System.out.println(" DISPONIBILIDADE: " + (this.getAvaible() ? "DISPONÍVEL" : "INDISPONÍVEL"));
    }

    public void setParticularity(String numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
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
