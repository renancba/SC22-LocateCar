package org.locadora.views;

import org.locadora.controller.AgencyController;
import org.locadora.controller.OperationController;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.model.vehicle.Car;
import org.locadora.utils.GetLocalDateFromString;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;
import org.locadora.utils.Pagination;
import java.time.LocalDate;
import java.util.List;

public class OperationUI {
    public static void add() {
        OperationController operationController = new OperationController();
        try {
        /*
        pesquisar o cliente

        Informar o Id da Agência
        *
        * mostrar lista de carros
        * escolher um carro
        *        *
        * */


//            String startDateString = Input.stringNotNullable("DATA DA LOCAÇÃO (dd/mm/aaaa)", 3);
//            LocalDate startDate = GetLocalDateFromString.Convert(startDateString);
//
//            String endDateString = Input.stringNotNullable("DATA DA ENTREGA (dd/mm/aaaa)", 3);
//            LocalDate endDate = GetLocalDateFromString.Convert(endDateString);
//
//            NaturalPerson person = new NaturalPerson("Washington", "Ferreira", "23044797829", "321654987");
//
//
//            //mocking ome info
//            operationController.save(person, new Agency("São Paulo"), new Car("Nissan", "Versa", "Qnr668"), startDate, endDate);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }

    }

    public static String List(List<RentalOperation> operations, int pageSize, int pageNumber) {
        String option = "";

        boolean working = true;

        while (working) {
            try {

                if (operations.size() == 0) {
                    System.out.println("NENHUMA OPERACAO ENCONTRADA");
                    working = false;
                    continue;
                }

                List<RentalOperation> paginatedOperations = Pagination.exec(operations, pageSize, pageNumber);

                // display the paginated list
                System.out.println("------ OPERAÇÕES ------");
                System.out.println("");
                for (RentalOperation operation : paginatedOperations) {
                    System.out.println(operation.toString());
                }
                System.out.println("--------------------------");

                if (operations.size() == 0) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR OPERACAO");
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
                } else if (operations.size() > pageSize) {
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR OPERACAO", "ADICIONAR OPERACAO");
                    switch (choice) {
                        case 0:
                            working = false;
                            break;
                        case 1:
                            List(operations, pageSize, pageNumber + pageSize);
                            break;
                        case 2:
                            List(operations, pageSize, pageNumber - pageSize);
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
                    int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "EXIBIR OPERACAO", "ADICIONAR OPERACAO");
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

    public static void viewOperation(RentalOperation operation) {
        OperationController operationController = new OperationController();
        boolean working = true;

        while (working) {
            try {
                System.out.println("------- CONTATO -------");
                System.out.println("");
                operation.toString();

                switch (MenuCreator.exec(".:: OPÇÔES DE CONTATO ::.", "VOLTAR", "EXTENDER DEVOLUÇÃO", "REALIZAR DEVOLUÇÃO")) {
                    case 0 -> {
                        working = false;
                    }
                    case 1 -> operationController.extendReturnDate(operation);
                    case 2 -> operationController.returVehicle(operation);
                    default -> System.out.println("-> Opção inválida \n");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");

            }
        }
    }

    public static Agency returnVehicle(RentalOperation operation) {
        AgencyController agencyController = new AgencyController();
        // Operação retorna uma agencia
        boolean working = true;
        Agency agency = null;

        while (working) {
            System.out.println("------- AGENCIA LOCADORA -------");
            System.out.println("");
            operation.getAgency();

            int option = MenuCreator.exec("A DEVOLUÇÃO SERÁ NA MESMA AGÊNCIA?", "SIM", "NÃO", "VOLTAR");

            switch (option) {
                case 0 -> {
                    working = false;
                }
                case 1 -> agency = operation.getAgency();
//                case 2 ->  agency = agencyController.view();
                default -> System.out.println("-> Opção inválida \n");
            }
        }
        System.out.println();
        //caso a agencia nao seja
        //caso a agencia seja diferente da informada, buscar na lsta paginada de agencias por um id e retornar para o controller
        return agency;
    }
}
