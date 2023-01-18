package org.locadora.views;

import org.locadora.controller.OperationController;
import org.locadora.model.RentalOperation;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;

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

//            operationController.save();

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

    public static <T> List<T> pagination(List<T> list, int pageSize, int pageNumber) {
        List<T> pagination;

        pagination = list.stream()
                .skip((pageNumber) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        return pagination;
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

        // validate input
        if (pageNumber < 0) pageNumber = 0;
        if (pageSize < 0) pageSize = 0;
        if (pageNumber + pageSize > operations.size()) pageNumber = operations.size() - pageSize;
        if (pageNumber < 0 || pageNumber >= operations.size()) pageNumber = 0;

        // paginate the list
        List<RentalOperation> paginatedOperations = pagination(operations, pageSize, pageNumber);

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
        } else if(operations.size() > pageSize) {
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

        }else{
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

    public static String searchOperation(List<RentalOperation> operations, int pageSize, int pageNumber) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        if (pageNumber < 0) pageNumber = 0;
        if (pageSize < 0) pageSize = 0;
        if (pageNumber + pageSize > operations.size()) pageNumber = operations.size() - pageSize;
        if (pageNumber < 0 || pageNumber >= operations.size()) pageNumber = 0;

        do {
            working = false;

            try {

                if (operations.size() == 0) {
                    System.out.println("NENHUMA OPERACAO ENCONTRADA PARA O TERMO INFORMADO.\n");
                    break;
                }

                operations.forEach(RentalOperation::toString);

                return OptionMenu(operations, pageSize, pageNumber);

            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "\n");
                break;
            }

        } while (working);
        return "2";
    }

}
