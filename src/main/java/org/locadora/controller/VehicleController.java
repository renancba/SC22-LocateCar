package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.Agency;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Truck;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.views.VehicleUI;

import java.math.BigDecimal;


public class VehicleController {
    // TODO: INSTANCIAR CAMINHÃO, MOTO OU CARRO
    public void create() {
        VehicleUI.add();
    }

    public void saveMotorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String cylinderCapacity, Agency agency) {
        Database db = Database.getInstance();

        Motorcycle vehicle = new Motorcycle(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, cylinderCapacity);
        agency.addVehicle(vehicle);

        dbResponse(db.updateAgency(agency));
    }

    public void saveCar(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String numberOfDoors, Agency agency) {
        Database db = Database.getInstance();

        Vehicle vehicle = new Car(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, numberOfDoors);
        agency.addVehicle(vehicle);

        dbResponse(db.updateAgency(agency));
    }

    public void saveTruck(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String numberOfAxies, Agency agency) {
        Database db = Database.getInstance();

        Vehicle vehicle = new Truck(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, numberOfAxies);
        agency.addVehicle(vehicle);

        dbResponse(db.updateAgency(agency));
    }

    public void dbResponse(Boolean response) {
        if (response) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| VEÍCULO SALVO |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| VEÍCULO DUPLICADO! |");
            System.out.println("----------------------");
            System.out.println("");
        }
    }

    public void search(String value) {
        Database db = Database.getInstance();
        if (value != null) {
            VehicleUI.list(db.searchVehicles(value));
        } else {
            VehicleUI.search();
        }
    }
}
