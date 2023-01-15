package org.locadora.model.vehicle;


import java.math.BigDecimal;

public class Car extends Vehicle {
    public Car() {
    }

    public Car(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        super(vehicleManufacturer, vehicleModel, registrationPlate);
        super.rentalFee = new BigDecimal(150);
    }

    @Override
    public BigDecimal getRentalFee() {
        return super.rentalFee = new BigDecimal(150);
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
