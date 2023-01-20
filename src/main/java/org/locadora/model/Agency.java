package org.locadora.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.locadora.model.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Agency<T extends Vehicle> {
    private Integer id;
    private String name;
    private Address address;
    private List<T> vehicles;

    public Agency() {
        this.vehicles = new ArrayList<>();
    }

    public Agency(String name) {
        this.name = name;
    }

    public Agency(String name, Address address) {
        this.id = (int) (Math.random() * 200) + 1;
        this.name = name;
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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

    public void addVehicle(T vehicle) {
        this.vehicles.add(vehicle);
    }

    public List<T> getVehicles() {
        return this.vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agency agency = (Agency) o;
        return Objects.equals(name, agency.name) && Objects.equals(address, agency.address);
    }

    public JSONObject toJSONObject() {
        JSONObject agencyObject = new JSONObject();
        JSONArray vehiclesArray = new JSONArray();

        agencyObject.put("id", this.getId());
        agencyObject.put("name", this.getName());


        if (address != null) {
            agencyObject.put("address", address.toJSONObject());
        }
        agencyObject.put("vehicles", vehiclesArray);

        if (vehicles != null) {
            for (T vehicle : vehicles) {
                JSONObject vehicleObject = vehicle.toJSONObject();
                vehiclesArray.put(vehicleObject);
            }
        }

        return agencyObject;
    }
    public void shortInfo() {
        System.out.println(" AGÊNCIA " + this.getId() + " - " + this.getName() );
    }
    public void completeInfo() {
        this.shortInfo();
        System.out.println(" ENDEREÇO: " + address);
        System.out.println("VEÍCULOS: " + vehicles.size());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public String toString() {
        return "AGÊNCIA " + this.getId() + " - " + name + "\n" +
                "ENDEREÇO: " + address + "\n";
    }


}

