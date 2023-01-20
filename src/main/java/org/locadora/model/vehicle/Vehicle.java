package org.locadora.model.vehicle;

import org.json.JSONObject;

import java.math.BigDecimal;

public abstract class Vehicle {
    protected String vehicleManufacturer;
    protected String vehicleModel;
    protected String registrationPlate;
    protected Boolean isAvaible;
    protected BigDecimal rentalFee;

    public Vehicle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee) {
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModel = vehicleModel;
        this.registrationPlate = registrationPlate;
        this.rentalFee = rentalFee;
        this.isAvaible = true;
    }

    public Vehicle() {
        this.rentalFee = getRentalFee();
    }

    public String getVehicleManufacturer() {
        return vehicleManufacturer;
    }

    public void setVehicleManufacturer(String vehicleManufacturer) {
        this.vehicleManufacturer = vehicleManufacturer;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public Boolean getAvaible() {
        return isAvaible;
    }

    public void setAvaible(Boolean avaible) {
        isAvaible = avaible;
    }

    public abstract BigDecimal getRentalFee();

    public abstract JSONObject toJSONObject();

    public abstract void completeInfo();

    public abstract void shortInfo();
    public abstract void setParticularity(String s);

    @Override
    public String toString() {
        return "FABRICANTE: " + vehicleManufacturer + "MODELO: " + vehicleModel + "PLACA: " + registrationPlate +
               "TAXA DE ALUGUEL: " + rentalFee;
    }
}
