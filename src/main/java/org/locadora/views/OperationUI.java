package org.locadora.views;

import org.locadora.controller.OperationController;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.model.vehicle.Car;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;
import org.locadora.utils.Pagination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationUI {
    public static void add() {
        OperationController operationController = new OperationController();
        try {
        /*
        Informar o cpf do cliente

        Informar o Id da Agência
        *
        * mostrar lista de carros
        * escolher um carro
        *        *
        * */

            String startDateString = Input.stringNotNullable("DATA DA LOCAÇÃO", 3);
            LocalDate startDate = LocalDate.parse(startDateString);

            String endDateString = Input.stringNotNullable("DATA DA ENTREGA", 3);
            LocalDate endDate = LocalDate.parse(endDateString);

            //mocking ome info
            operationController.save(new NaturalPerson(), new Agency(), new Car(), startDate, endDate);

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

    public static String OptionMenu(List<RentalOperation> operations, int pageSize, int pageNumber) {
        String option = "";

        if (operations.size() == 0) {
            int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR OPERACAO");
            switch (choice) {
                case 0:
                    option = "VOLTAR";
                    break;
                case 1:
                    add();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA\n");
                    break;
            }
        } else {
            int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR OPERACAO", "ADICIONAR OPERACAO");
            switch (choice) {
                case 0:
                    option = "VOLTAR";
                    break;
                case 1:
                    List(operations, pageSize, pageNumber + pageSize);
                    break;
                case 2:
                    List(operations, pageSize, pageNumber - pageSize);
                    break;
                case 3:
                    option = "EDITAR";
                    break;
                case 4:
                    add();
                    break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA\n");
                    break;
            }
        }
        return option;
    }

    public static String List(List<RentalOperation> operations, int pageSize, int pageNumber) {
        String option = "";
        List<RentalOperation> paginatedOperations = Pagination.exec(operations, pageSize, pageNumber);

        // display the paginated list
        System.out.println("------ OPERAÇÕES ------");
        System.out.println("");
        paginatedOperations.forEach(RentalOperation::toString);
        System.out.println("--------------------------");

        // handle navigation

        if (operations.size() == 0) {
            int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR OPERACAO");
            switch (choice) {
                case 0:
                    option = "VOLTAR";
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
                    option = "VOLTAR";
                    break;
                case 1:
                    List(operations, pageSize, pageNumber + pageSize);
                    break;
                case 2:
                    List(operations, pageSize, pageNumber - pageSize);
                    break;
                case 3:
                    option = "EDITAR";
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
                    option = "VOLTAR";
                    break;
                case 2:
                    option = "EDITAR";
                    break;
                case 3:
                    add();
                    break;
                default:
                    System.out.println("OPÇÃO INVÁLIDA\n");
                    break;
            }

        }


        return OptionMenu(operations, pageSize, pageNumber);
    }

    public static void view(RentalOperation operation) {
        OperationController operationController = new OperationController();
        boolean working = true;

        while (working) {
            System.out.println("------- CONTATO -------");
            System.out.println("");
            operation.toString();

            int option = MenuCreator.exec(".:: OPÇÔES DE CONTATO ::.", "VOLTAR", "EXTENDER DEVOLUÇÃO", "REALIZAR DEVOLUÇÃO");

            switch (option) {
                case 0 -> {
                    working = false;
                }
                case 1 -> operationController.extendReturnDate(operation);
                case 2 -> operationController.returVehicle(operation);
                default -> System.out.println("-> Opção inválida \n");
            }
        }
    }

    public static String searchBy(String param) {
        String option = null;
        boolean working;

        do {
            working = false;
            try {
                option = Input.stringNotNullable("INFORME O " + param + ": ", 3);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");
            }

        } while (working);

        return option;
    }

    public static LocalDate getDate() {
        LocalDate date = null;
        try {
            String endDateString = Input.stringNotNullable("DATA DA ENTREGA", 3);
            date = LocalDate.parse(endDateString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
        return date;

    }

    public static Agency returnVehicle(RentalOperation operation) {
        // Operação retorna uma agencia
        //caso a agencia nao seja

        //caso a agencia seja diferente da informada, buscar na lsta paginada de agencias por um id e retornar para o controller
        return operation.getLocationAgency();
    }
}
