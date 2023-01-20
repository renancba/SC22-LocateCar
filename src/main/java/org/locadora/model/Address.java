package org.locadora.model;


import org.json.JSONObject;

import java.util.Objects;

public class Address {
    private String zipcode;
    private String street;
    private String number;
    private String city;
    private String state;

    public Address(String zipcode, String street, String number, String city, String state) {
        this.zipcode = zipcode;
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public JSONObject toJSONObject() {
        JSONObject addressObject = new JSONObject();
        addressObject.put("zipcode", zipcode);
        addressObject.put("street", street);
        addressObject.put("number", number);
        addressObject.put("city", city);
        addressObject.put("state", state);
        return addressObject;
    }

    @Override
    public String toString() {
        return "CEP-" + zipcode + ", " + street + " - " + number + ", " + city + " - " + state;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(zipcode, address.zipcode) && Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(city, address.city) && Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipcode, street, number, city, state);
    }
}
