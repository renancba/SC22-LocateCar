package org.locadora.model;


import java.util.Objects;

public class Address {
    private String cep;
    private String street;
    private String number;
    private String city;
    private String state;

    public Address() {
    }

    public Address(String cep, String street, String number, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return  "CEP: " + cep + ", " + street + " - " + number + ", " + city + " - " + state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(cep, address.cep) && Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(city, address.city) && Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, street, number, city, state);
    }
}
