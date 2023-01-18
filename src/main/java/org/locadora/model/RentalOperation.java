package org.locadora.model;

import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class RentalOperation {
    private Costumer costumer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal cost;
    private Agency agency;

    public RentalOperation(Costumer costumer, Vehicle vehicle, LocalDate startDate, LocalDate endDate, Agency agency) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data de locação deve ser maior do que a data de entrega");
        }
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        calculateCost();
    }

    private void calculateCost() {
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        BigDecimal rentalFee = vehicle.getRentalFee();

        if(days > 5) {
            // 10% de desconto para locações acima de 5 dias
            rentalFee = rentalFee.multiply(new BigDecimal("0.9"));
        }

        cost = rentalFee.multiply(new BigDecimal(days));
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}
