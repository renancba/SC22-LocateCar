package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.Agency;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Truck;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.Input;
import org.locadora.utils.InputAddress;
import org.locadora.views.AgencyUI;
import org.locadora.views.VehicleUI;

import java.math.BigDecimal;


public class VehicleController {
    public void create() {
        try {
            AgencyController agencyController = new AgencyController();
            System.out.println("PARA QUAL AGÊNCIA DESEJA CADASTRAR O VEÍCULO?");
            Agency agency = agencyController.searchById();
            VehicleUI.add(agency);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }

    }

    public void saveMotorcycle(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String cylinderCapacity, Agency agency) {
        Database db = Database.getInstance();

        Motorcycle vehicle = new Motorcycle(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, cylinderCapacity);
        dbResponse(db.addVehicle(agency, vehicle));
    }

    public void saveCar(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String transmission, Agency agency) {
        Database db = Database.getInstance();

        Vehicle vehicle = new Car(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, transmission);
        dbResponse(db.addVehicle(agency, vehicle));
    }

    public void saveTruck(String vehicleManufacturer, String vehicleModel, String registrationPlate, BigDecimal rentalFee, String numberOfAxies, Agency agency) {
        Database db = Database.getInstance();

        Vehicle vehicle = new Truck(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, numberOfAxies);
        dbResponse(db.addVehicle(agency, vehicle));
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

    public String list(Agency agency) {
        Database db = Database.getInstance();
        return VehicleUI.list(db.getVehicles(agency), agency, 5, 0);
    }

    public void listAll() {
        AgencyController agencyController = new AgencyController();
        try {
            Agency agency = agencyController.searchById();
            String option = list(agency);

            if (option.equals("exibir")) {
                viewVehicle(agency);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }

    public void listAllFromAgency(Agency agency) {
        try {
            String option = list(agency);

            if (option.equals("exibir")) {
                viewVehicle(agency);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }

    public void viewVehicle(Agency agency) {
        Database db = Database.getInstance();
        try {
            int index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
            VehicleUI.viewVehicle((Vehicle) agency.getVehicles().get(index), agency);

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }

    public void edit(String option, Vehicle vehicle, Agency agency) throws Exception {
        Database db = Database.getInstance();

        switch (option) {
            case "trasmissão" -> {
                vehicle.setParticularity(Input.stringNotNullable("INFORME UMA NOVA TRANSMISSÂO (MANUAL/AUTOMÁTICA): ", 3));
                vehicle.setRegistrationPlate(Input.stringNotNullable("INFORME A PLACA DO VEÍCULO: ", 3));
            }
            case "cilindradas" -> {
                vehicle.setParticularity(Input.stringNotNullable("INFORME UMA NOVA CILINDRADA: ", 3));
                vehicle.setRegistrationPlate(Input.stringNotNullable("INFORME A PLACA DO VEÍCULO: ", 3));
            }
            case "eixos" -> {
                vehicle.setParticularity(Input.stringNotNullable("INFORME UM NOVO NUMERO DE EIXOS: ", 3));
                vehicle.setRegistrationPlate(Input.stringNotNullable("INFORME A PLACA DO VEÍCULO: ", 3));
            }
            case "marca/modelo" -> {
                vehicle.setVehicleManufacturer(Input.stringNotNullable("INFORME UMA NOVA TRANSMISSÂO (MANUAL/AUTOMÁTICA): ", 3));
                vehicle.setVehicleModel(Input.stringNotNullable("INFORME UMA NOVA TRANSMISSÂO (MANUAL/AUTOMÁTICA): ", 3));
                vehicle.setRegistrationPlate(Input.stringNotNullable("INFORME UMA NOVA TRANSMISSÂO (MANUAL/AUTOMÁTICA): ", 3));
            }
        }

        if (db.updateVehicle(vehicle, agency)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| VEÍCULO ATUALIZADO COM SUCEESSO |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| ERRO AO ATUALIZAR VEÍCULO. TENTE NOVAMENTE MAIS TARDE |");
            System.out.println("----------------------");
            System.out.println("");
        }
    }

//    public void search(String value) {
//        Database db = Database.getInstance();
//        if (value != null) {
//            VehicleUI.list(db.searchVehicles(value));
//        } else {
//            VehicleUI.search();
//        }
//    }
}
