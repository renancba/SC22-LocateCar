package org.locadora.model;

import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class RentalOperation<T extends Vehicle> {
    private Costumer costumer;
    private T vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal cost;
    private Agency agency;

    public RentalOperation(Costumer costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency) {
        if (isActive()) {
            throw new IllegalArgumentException("A data de locação deve ser maior do que a data de entrega");
        }
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        calculateCost();
    }

    public RentalOperation(Costumer costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency, BigDecimal cost) {
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        this.cost = cost;
    }

    private void calculateCost() {
        long days = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
        BigDecimal rentalFee = vehicle.getRentalFee();

        if (days > 5) {
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

    public T getVehicle() {
        return vehicle;
    }

    public void returnVehicle() {
        LocalDate returnDate = LocalDate.now();
        if (returnDate.isAfter(endDate)) {
            long lateDays = Duration.between(endDate.atStartOfDay(), returnDate.atStartOfDay()).toDays();
            BigDecimal lateFee = new BigDecimal("5").multiply(new BigDecimal(lateDays));
            cost = cost.add(lateFee);
        }
        vehicle.setAvaible(true);
    }

    public void setVehicle(T vehicle) {
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

    public void updateEndDate(LocalDate newEndDate) {
        this.endDate = newEndDate;
        calculateCost();
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

    public boolean isActive() {
        return LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate);
    }

    @Override
    public String toString() {
        return "RentalOperation{" +
                "costumer=" + costumer +
                ", vehicle=" + vehicle +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                ", agency=" + agency +
                '}';
    }
}
