package org.locadora.model;

import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;

import java.time.Duration;
import java.time.LocalDate;

public class RentalOperation {
    private Costumer costumer;
    private Vehicle vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private double cost;
    private Agency agency;

    public RentalOperation(Costumer costumer, Vehicle vehicle, LocalDate startDate, LocalDate endDate, Agency agency) {
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        calculateCost();
    }

    private void calculateCost() {
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        if (days > 5) {
            cost = (vehicle.getPricePerDay() * days) * 0.9;
        } else {
            cost = vehicle.getPricePerDay() * days;
        }
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public Vehicle getCar() {
        return vehicle;
    }

    public void setCar(Vehicle vehicle) {
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

    public double getCost() {
        return cost;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }
}