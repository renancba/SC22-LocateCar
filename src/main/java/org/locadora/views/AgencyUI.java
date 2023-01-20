package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.VehicleController;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.Input;
import org.locadora.utils.InputAddress;
import org.locadora.utils.MenuCreator;
import org.locadora.utils.Pagination;


import java.util.List;

public class AgencyUI {
    public static void add() {
        AgencyController agencyController = new AgencyController();

        try {
            String name = Input.stringNotNullable("NOME DA AGÊNCIA: ", 3);
            Address address = InputAddress.exec("");
            agencyController.saveAgency(name, address);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
    }

    public static String list(List<Agency> agencies, int pageSize, int pageNumber) {
        String option = "";
        int totalDisplayed = 0;

        boolean working = true;

        while (working) {
            if (pageNumber < 0) pageNumber = 0;
            if (pageSize < 0) pageSize = 0;
            if (pageNumber + pageSize > agencies.size()) pageNumber = agencies.size() - pageSize;
            if (pageNumber < 0 || pageNumber >= agencies.size()) pageNumber = 0;

            try {

                if (agencies.size() == 0) {
                    System.out.println("NENHUMA AGÊNCIA ENCONTRADA");
                    working = false;
                    continue;
                }

                List<Agency> paginatedAgencies = Pagination.exec(agencies, pageSize, pageNumber);

                System.out.println("------ AGÊNCIAS ------");
                System.out.println("");

                for (int i = 0; i < paginatedAgencies.size(); i++) {
                    System.out.print(" ID: " + (i + totalDisplayed) + "\n");
                    paginatedAgencies.get(i).shortInfo();
                    System.out.println("-------------------------\n");
                }

                totalDisplayed += paginatedAgencies.size();

                if (agencies.size() == 0) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR AGÊNCIA");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            add();
                            working = false;
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }
                } else if (agencies.size() > pageSize) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR AGÊNCIA", "ADICIONAR AGÊNCIA");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            pageNumber = pageNumber + 1;
                            break;
                        case 2:
                            pageNumber = pageNumber - 1;
                            totalDisplayed -= pageSize +2;
                            break;
                        case 3:
                            option = "exibir";
                            working = false;
                            break;
                        case 4:
                            add();
                            break;
                        default:
                            System.out.println("OPÇÃO INVÁLIDA\n");
                            break;
                    }

                } else {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "EXIBIR AGÊNCIA", "ADICIONAR AGÊNCIA");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            option = "exibir";
                            working = false;
                            break;
                        case 2:
                            add();
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

    public static void viewAgency(Agency agency) {
        AgencyController agencyController = new AgencyController();
        VehicleController vehicleController = new VehicleController();

        boolean working = true;
        while (working) {
            try {
                System.out.println("\n------- AGÊNCIA -------");
                agency.completeInfo();
                System.out.println("-------------------------\n");
                switch (MenuCreator.exec(".:: OPÇÕES DE AGÊNCIA ::.", "VOLTAR", "CADASTRAR VEÍCULO", "LISTAR VEICULOS", "EDITAR NOME", "EDITAR ENDERECO")) {
                    case 0 -> {
                        working = false;
                    }
                    case 1 -> VehicleUI.add(agency);
                    case 2 -> vehicleController.listAllFromAgency(agency);
                    case 3 -> agencyController.edit("name", agency);
                    case 4 -> agencyController.edit("address", agency);
                    default -> System.out.println("-> Opção inválida \n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");
            }
        }
    }

    public static String searchBy() {
        String response = "";
        boolean working = true;
        try {
            while (working) {

                int option = MenuCreator.exec("COMO GOSTARIA DE REALIZAR A PESQUISA? ", "VOLTAR", "CÓDIGO DA AGÊNCIA", "NOME/LOGRADOURO");
                switch (option) {
                    case 0 -> working = false;
                    case 1 -> {
                        response = "codigo";
                        working = false;
                    }
                    case 2 -> {
                        response = "name";
                        working = false;
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
        return response;
//        agencyController.search(term.toUpperCase());
    }
}
