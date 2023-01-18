package org.locadora.model;

import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

public class RentalOperation<T extends Vehicle> {
    private Integer contrato;
    private Costumer costumer;
    private T vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal cost;
    private Agency agency;

    private boolean concluido = false;

    public RentalOperation(Costumer costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency) {
        if (isActive()) {
            throw new IllegalArgumentException("A data de locação deve ser maior do que a data de entrega");
        }
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        this.contrato = (int) (Math.random() * 200) + 1;
        calculateCost();
    }

    public RentalOperation(Integer id, Costumer costumer, T vehicle, LocalDate startDate, LocalDate endDate, Agency agency, BigDecimal cost) {
        this.costumer = costumer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.agency = agency;
        this.cost = cost;
        this.contrato = id;
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

    public T getVehicle() {
        return vehicle;
    }



    public Integer getContrato() {
        return contrato;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }



    public BigDecimal getCost() {
        return cost;
    }

    public Agency getAgency() {
        return agency;
    }

    public boolean isActive() {
        return LocalDate.now().isAfter(startDate) && LocalDate.now().isBefore(endDate);
    }

    public void updateEndDate(LocalDate newEndDate) {
        this.endDate = newEndDate;
        calculateCost();
    }

    public void returnVehicle(Agency agency) {
        LocalDate returnDate = LocalDate.now();
        if (returnDate.isAfter(endDate)) {
            long lateDays = Duration.between(endDate.atStartOfDay(), returnDate.atStartOfDay()).toDays();
            BigDecimal lateFee = new BigDecimal("5").multiply(new BigDecimal(lateDays));
            cost = cost.add(lateFee);
        }

        //Verificar se está sendo entregue na mesma agencia que foi retirado, se nao, setar agencia de entrega e mudar o veículo de agencia.
        this.concluido = true;
        vehicle.setAvaible(true);
    }

    @Override
    public String toString() {
        return "--------------------------" +
                "Cliente:" + costumer +
                ", Veículo:" + vehicle +
                ", Locação:" + startDate +
                ", Devolução:" + endDate +
                ", Custo:" + cost +
                ", Agência:" + agency;

    }
}
