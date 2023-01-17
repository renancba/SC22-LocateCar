package org.locadora.model.costumer;

import org.json.JSONObject;
import org.locadora.model.Address;
import org.locadora.model.Telephone;

public abstract class Costumer {
    protected String name;
    protected Telephone telephone;
    protected Address address;
    public Costumer() {
    }

    public Costumer(String name) {
        this.name = name;
    }

    public Costumer(String name, Telephone telephone, Address adress) {
        this.name = name;
        this.telephone = telephone;
        this.address = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public JSONObject toJSONObject() {
        return null;
    }

    public abstract void info();


// TODO: SOBRESCREVER EQUALS, HASHCODE E TOSTRING

}
