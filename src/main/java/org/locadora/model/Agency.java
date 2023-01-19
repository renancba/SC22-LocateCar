package org.locadora.model;

import org.locadora.model.vehicle.Vehicle;

import java.util.List;
import java.util.Objects;

public class Agency {
    private String name;
    private Address address;

    private List<Vehicle> vehicles;

    // adicionar atributo de data
    // adicionar atributo de horário


    public Agency() {
    }

    public Agency(String name) {
        this.name = name;
    }

    public Agency(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(name, agency.name) && Objects.equals(address, agency.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "Agency{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

