package org.locadora.model.vehicle;

import org.json.JSONObject;
import org.locadora.model.Agency;

import java.math.BigDecimal;
import java.util.List;

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

    @Override
    public String toString() {
        return "FABRICANTE: " + vehicleManufacturer + "MODELO: " + vehicleModel + "PLACA: " + registrationPlate +
               "TAXA DE ALUGUEL: " + rentalFee;
    }
}
