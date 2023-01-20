package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.model.Address;
import org.locadora.model.Agency;
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

        boolean working = true;

        while (working) {
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
                    System.out.print(" ID: " + i + "\n");
                    paginatedAgencies.get(i).shortInfo();
                    System.out.println("-------------------------\n");
                }

                if (agencies.size() == 0) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR AGÊNCIA");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            add();
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
                            list(agencies, pageSize, pageNumber + pageSize);
                            break;
                        case 2:
                            list(agencies, pageSize, pageNumber - pageSize);
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
//                case 1 -> /*Cadastrar veículo*/;
//                case 2 -> /*Listar Veículos*/
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
