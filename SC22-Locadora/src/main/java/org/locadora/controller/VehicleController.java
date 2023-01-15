package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Truck;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.views.VehicleUI;


public class VehicleController {
    // TODO: INSTANCIAR CAMINHÃO, MOTO OU CARRO
    public void create() {
        VehicleUI.add();
    }
    public void saveMotorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        Motorcycle motorcycle = new Motorcycle(vehicleManufacturer, vehicleModel, registrationPlate);
        Database db = Database.getInstance();

        if (db.addVehicle(motorcycle)) {
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
    public void saveCar(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        Vehicle vehicle = new Car();
        Database db = Database.getInstance();

        if (db.addVehicle(vehicle)) {
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
    public void saveTruck(String vehicleManufacturer, String vehicleModel, String registrationPlate) {
        Vehicle vehicle = new Truck();
        Database db = Database.getInstance();

        if (db.addVehicle(vehicle)) {
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
