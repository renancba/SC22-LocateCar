package org.locadora.model;


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
}
