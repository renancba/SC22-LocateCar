package org.locadora.model.vehicle;

import java.math.BigDecimal;

public abstract class Vehicle {
    protected String vehicleManufacturer;
    protected String vehicleModel;
    protected String registrationPlate;
    protected Boolean isAvaible;
    protected BigDecimal rentalFee;

    public Vehicle(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        this.vehicleManufacturer = vehicleManufacturer;
        this.vehicleModel = vehicleModel;
        this.registrationPlate = registrationPlate;
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
}
