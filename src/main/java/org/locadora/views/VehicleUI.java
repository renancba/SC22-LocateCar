package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.VehicleController;
import org.locadora.model.Agency;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Motorcycle;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;

import java.math.BigDecimal;
import java.util.List;

public class VehicleUI {
    public static void add() {
        VehicleController vehicleController = new VehicleController();
        AgencyController agencyController = new AgencyController();

        Integer option;
        String vehicleManufacturer;
        String vehicleModel;
        String registrationPlate;
        BigDecimal rentalFee;

        try {

            System.out.println("PARA QUAL AGÊNCIA DESEJA CADASTRAR O VEÍCULO?");
            Agency agency = agencyController.searchById();


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
                    String numberOfDoors = Input.stringNotNullable("NUMERO DE PORTAS : ", 3);

                    vehicleController.saveCar(vehicleManufacturer, vehicleModel, registrationPlate, rentalFee, numberOfDoors, agency);
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

    public static int getIndex() throws Exception {
        int index = 0;

        index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
        System.out.println("");
        return index;
    }

    public static void list(List<Vehicle> vehicles) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        do {
            working = false;

            try {

                if (vehicles.size() == 0) {
                    System.out.println("NENHUM VEÍCULO ENCONTRADO PARA O TERMO INFORMADO.\n");
                    break;
                }

                if (vehicles.size() > 1) {
                    System.out.println("");
                    for (Vehicle vehicle : vehicles) {
                        System.out.println("-------- VEÍCULO -------");
                        System.out.println("ID: " + index);
                        System.out.println("Tipo: " + vehicle.getClass());
                        System.out.println("Fabricante: " + vehicle.getVehicleManufacturer());
                        System.out.println("Modelo: " + vehicle.getVehicleModel());
                        System.out.println("Placa: " + vehicle.getRegistrationPlate());
                        System.out.println("Diária:" + vehicle.getRentalFee());
                        System.out.println("------------------------");
                        index++;
                    }
                    System.out.println("");

                    int indexOption = getIndex();
                    if (indexOption > vehicles.size()) {
                        System.out.println("-> Opção inválida\n");
                        index = 0;
                        tentativas++;
                        working = true;
                        continue;
                    }

                    if (tentativas > 3) {
                        System.out.println("-> Número de tentativas excedidas");
                        System.out.println("voltando...\n");
                        break;
                    }

                    VehicleUI.view(vehicles.get(indexOption));

                } else {
                    VehicleUI.view(vehicles.get(0));
                    break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "\n");
                break;
            }

        } while (working);
    }

    public static String paginatedList(List<Vehicle> vehicles) {
        boolean working = true;
        int ammount = 0;
        int start = 0;
        String option = "";

        while (working) {
            try {
                if (ammount == 0) {
                    ammount = Input.integer("Informe a quantidade de veículos por página: ");
                    System.out.println("");
                }

                if (start < 0 || start > vehicles.size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > vehicles.size()) ammount = vehicles.size();
                if (start + ammount > vehicles.size()) start = vehicles.size() - ammount;

                System.out.println("------ VEÍCULOS ------");

                for (int i = start; i < start + ammount; i++) {
                    if (i == vehicles.size()) break;
                    System.out.println("ID: " + i);
                    System.out.println("FABRICANTE: " + vehicles.get(i).getVehicleManufacturer());
                    System.out.println("MODELO: " + vehicles.get(i).getVehicleModel());
                    System.out.println("----------------------");
                }
                System.out.println("");

                boolean better = true;

                while (better) {
                    if (vehicles.size() == 0) {
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR VEÍCULO")) {
                            case 0 -> {
                                better = false;
                                working = false;
                                option = "VOLTAR";
                            }
                            case 1 -> {
                                add();
                                better = false;
                            }
                            default -> System.out.println(" OPÇÃO INVÁLIDA\n");
                        }
                    } else {
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR VEÍCULO")) {
                            case 0 -> {
                                better = false;
                                working = false;
                                option = "VOLTAR";
                            }
                            case 1 -> {
                                start = start + ammount;
                                better = false;
                            }
                            case 2 -> {
                                start = start - ammount;
                                better = false;
                            }
                            case 3 -> {
                                better = false;
                                working = false;
                                option = "EDITAR";
                            }
                            default -> System.out.println(" OPÇÃO INVÁLIDA\n");
                        }
                    }

                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage() + " VOLTANDO AO MENU PRINCIPAL...");
            }

        }
        return option;
    }

    public static void search() {
        VehicleController vehicleController = new VehicleController();
        String term = Input.string("DIGITE O NOME OU PARTE DO NOME DO VEÍCULO: ");
        vehicleController.search(term.toUpperCase());
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
