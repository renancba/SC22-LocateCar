package org.locadora.model.vehicle;

import java.math.BigDecimal;

public class Motorcycle extends Vehicle {
    public Motorcycle() {
    }

    public Motorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        super(vehicleManufacturer, vehicleModel, registrationPlate);
        super.rentalFee = new BigDecimal(100);
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
