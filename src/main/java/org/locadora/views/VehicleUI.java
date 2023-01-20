package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.VehicleController;
import org.locadora.model.Agency;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Truck;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;
import org.locadora.utils.Pagination;

import java.math.BigDecimal;
import java.util.List;

public class VehicleUI {
    public static void add(Agency agency) {
        VehicleController vehicleController = new VehicleController();

        Integer option;
        String vehicleManufacturer;
        String vehicleModel;
        String registrationPlate;
        BigDecimal rentalFee;

        try {

            option = MenuCreator.exec("DIGITE O TIPO DE VEÍCULO A SER ADICIONADO", "MOTO", "CARRO", "CAMINHÃO");

            switch (option) {
                case 0 -> {
                    vehicleManufacturer = Input.stringNotNullable("FABRICANTE: ", 3);
                    vehicleModel = Input.stringNotNullable("MODELO: ", 3);
                    registrationPlate = Input.stringNotNullable("PLACA: ", 3);
                    rentalFee = Input.bigDecimal("TAXA DE LOCAÇÃO : ", 3);
                    String cylinderCapacity = Input.stringNotNullable("QUANTIDADE DE CILINDROS: ", 3);

                    vehicleController.saveMotorcycle(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, cylinderCapacity, agency);
                }
                case 1 -> {
                    vehicleManufacturer = Input.stringNotNullable("FABRICANTE: ", 3);
                    vehicleModel = Input.stringNotNullable("MODELO: ", 3);
                    registrationPlate = Input.stringNotNullable("PLACA: ", 3);
                    rentalFee = Input.bigDecimal("TAXA DE LOCAÇÃO : ", 3);
                    String transmission = Input.stringNotNullable("Transmissão : ", 3);

                    vehicleController.saveCar(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, transmission, agency);
                }
                case 2 -> {
                    vehicleManufacturer = Input.stringNotNullable("FABRICANTE: ", 3);
                    vehicleModel = Input.stringNotNullable("MODELO: ", 3);
                    registrationPlate = Input.stringNotNullable("PLACA: ", 3);
                    rentalFee = Input.bigDecimal("TAXA DE LOCAÇÃO: ", 3);
                    String numberOfAxies = Input.stringNotNullable("NUMERO DE EIXOS: ", 3);

                    vehicleController.saveTruck(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, numberOfAxies, agency);
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
    }

    public static String list(List<Vehicle> vehicles, Agency agency, int pageSize, int pageNumber) {
        String option = "";
        int totalDisplayed = 0;

        boolean working = true;

        while (working) {
            if (pageNumber < 0) pageNumber = 0;
            if (pageSize < 0) pageSize = 0;
            if (pageNumber + pageSize > vehicles.size()) pageNumber = vehicles.size() - pageSize;
            if (pageNumber < 0 || pageNumber >= vehicles.size()) pageNumber = 0;

            try {

                if (vehicles.size() == 0) {
                    System.out.println("NENHUM VEÍCULO ENCONTRADO");
                    working = false;
                } else {
                    List<Vehicle> paginatedVehicles = Pagination.exec(vehicles, pageSize, pageNumber);

                    System.out.println("------ VEÍCULOS ------");
                    System.out.println("");
                    for (int i = 0; i < paginatedVehicles.size(); i++) {
                        System.out.print("ID: " + (i + totalDisplayed) + "\n");
                        paginatedVehicles.get(i).shortInfo();
                        System.out.println("-------------------------\n");
                    }

                    totalDisplayed += paginatedVehicles.size();
                }

                if (vehicles.size() == 0) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR AGÊNCIA");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            add(agency);
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }
                } else if (vehicles.size() > pageSize) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR VEÍCULO", "ADICIONAR VEÍCULO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            pageNumber = pageNumber + 1;
                            break;
                        case 2:
                            pageNumber = pageNumber - 1;
                            totalDisplayed -= pageSize + 2;
                            break;
                        case 3:
                            option = "exibir";
                            working = false;
                            break;
                        case 4:
                            add(agency);
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }

                } else {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "EXIBIR VEÍCULO", "ADICIONAR VEÍCULO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            option = "exibir";
                            working = false;
                            break;
                        case 2:
                            add(agency);
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }

                }
            } catch (Exception ex) {
                working = false;
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");
            }
        }
        return option;
    }

    public static void viewVehicle(Vehicle vehicle, Agency agency) {
        VehicleController vehicleController = new VehicleController();
        boolean working = true;
        while (working) {
            try {
                System.out.println("\n------- VEÍCULO -------");
                vehicle.completeInfo();
                System.out.println("-------------------------\n");

                if (vehicle instanceof Car) {

                    switch (MenuCreator.exec(".:: OPÇÕES DE VEÍCULO ::.", "VOLTAR", "EDITAR TRANSMISSÃO", "EDITAR MARCA OU MODELO")) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> vehicleController.edit("trasmissão", vehicle, agency);
                        case 2 -> vehicleController.edit("marca/modelo", vehicle, agency);
                        default -> System.out.println("-> Opção inválida \n");
                    }
                }

                if (vehicle instanceof Motorcycle) {

                    switch (MenuCreator.exec(".:: OPÇÕES DE VEÍCULO ::.", "VOLTAR", "EDITAR CILINDRADAS", "EDITAR MARCA OU MODELO")) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> vehicleController.edit("cilindradas", vehicle, agency);
                        case 2 -> vehicleController.edit("marca/modelo", vehicle, agency);
                        default -> System.out.println("-> Opção inválida \n");
                    }
                }

                if (vehicle instanceof Truck) {

                    switch (MenuCreator.exec(".:: OPÇÕES DE VEÍCULO ::.", "VOLTAR", "EDITAR NÚMERO DE EIXOS", "EDITAR MARCA OU MODELO")) {
                        case 0 -> {
                            working = false;
                        }
                        case 1 -> vehicleController.edit("eixos", vehicle, agency);
                        case 2 -> vehicleController.edit("marca/modelo", vehicle, agency);
                        default -> System.out.println("-> Opção inválida \n");
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");
            }
        }
    }


    public static void view(Vehicle vehicle) {

        if (vehicle instanceof Motorcycle) {
            System.out.println("------- VEÍCULO -------");
            System.out.println(" FABRICANTE: " + (vehicle).getVehicleManufacturer());
            System.out.println(" MODELO: " + (vehicle).getVehicleModel());
            System.out.println("-----------------------");
            System.out.println("");
        } else if (vehicle instanceof Car) {
            System.out.println("------- VEÍCULO -------");
            System.out.println(" FABRICANTE: " + (vehicle).getVehicleManufacturer());
            System.out.println(" MODELO: " + (vehicle).getVehicleModel());
            System.out.println("-----------------------");
            System.out.println("");
        } else {
            System.out.println("------- VEÍCULO -------");
            System.out.println(" FABRICANTE: " + (vehicle).getVehicleManufacturer());
            System.out.println(" MODELO: " + (vehicle).getVehicleModel());
            System.out.println("-----------------------");
            System.out.println("");
        }
    }


}
