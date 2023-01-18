package org.locadora.views;


import org.locadora.controller.CostumerController;
import org.locadora.model.costumer.Costumer;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;

import java.util.List;
import java.util.stream.Collectors;

public class CostumerUI {
    public static void add() {
        CostumerController costumerController = new CostumerController();
        Integer option;
        String name;
        String surname;
        String cpf;
        String companyDriver;
        String driverLicense;
        String nickname;
        String cnpj;
        String street;
        String number;
        String cep;
        String city;
        String state;
        String ddd;
        String telephone;

        option = MenuCreator.exec("DIGITE O TIPO DE CLIENTE A SER ADICIONADO", "PESSOA FÍSICA", "PESSOA JURÍDICA");

        switch (option) {
            case 0 -> {
                try {
                    name = Input.stringNotNullable("PRIMEIRO NOME: ", 3);
                    surname = Input.stringNotNullable("SOBRENOME: ", 3);
                    cpf = Input.stringNotNullable("CPF: ", 3);
                    driverLicense = Input.stringNotNullable("HABILITAÇÃO: ", 3);
                    System.out.println("\nCADASTRO DE ENDEREÇO: ");
                    street = Input.stringNotNullable("NOME DA RUA: ", 3);
                    number = Input.stringNotNullable("NÚMERO: ", 3);
                    cep = Input.stringNotNullable("CEP: ", 3);
                    city = Input.stringNotNullable("CIDADE: ", 3);
                    state = Input.stringNotNullable("ESTADO: ", 3);
                    System.out.println("\nCADASTRO DE TELEFONE: ");
                    ddd = Input.stringNotNullable("DDD: ", 3);
                    telephone = Input.stringNotNullable("NÚMERO TELEFONE: ", 3);

                    costumerController.saveNaturalPerson(name, surname, cpf, driverLicense, cep, street, number, city, state, ddd, telephone);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("voltando...\n");
                }
            }
            case 1 -> {
                try {
                    name = Input.stringNotNullable("NOME: ", 3);
                    nickname = Input.stringNotNullable("NOME FANTASIA: ", 3);
                    cnpj = Input.stringNotNullable("CNPJ: ", 3);
                    companyDriver = Input.stringNotNullable("MOTORISTA: ", 3);
                    driverLicense = Input.stringNotNullable("HABILITAÇÃO: ", 3);
                    System.out.println("\nCADASTRO DE ENDEREÇO: ");
                    street = Input.stringNotNullable("NOME DA RUA: ", 3);
                    number = Input.stringNotNullable("NÚMERO: ", 3);
                    cep = Input.stringNotNullable("CEP: ", 3);
                    city = Input.stringNotNullable("CIDADE: ", 3);
                    state = Input.stringNotNullable("ESTADO: ", 3);
                    System.out.println("\nCADASTRO DE TELEFONE: ");
                    ddd = Input.stringNotNullable("DDD: ", 3);
                    telephone = Input.stringNotNullable("NÚMERO TELEFONE: ", 3);

                    costumerController.saveLegalPerson(name, nickname, cnpj, companyDriver, driverLicense, cep, street, number, city, state, ddd, telephone);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    System.out.println("voltando...\n");
                }
            }
        }
    }

    public static int getIndex() throws Exception {
        int index = 0;

        index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
        System.out.println("");
        return index;
    }


    public static <T extends Costumer> String ListCostumer(List<T> costumers) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        do {
            working = false;

            try {

                if (costumers.size() == 0) {
                    System.out.println("NENHUM CLIENTE ENCONTRADO PARA O TERMO INFORMADO.\n");
                    break;
                }
                //Listar os contatos
                for (T costumer : costumers) {
                    costumer.shortInfo();
                    System.out.println("");
                    System.out.println("---------------------");
                }


                int indexOption = getIndex();
                if (indexOption > costumers.size()) {
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

                costumers.get(indexOption).completeInfo();


            } catch (Exception ex) {
                System.out.println(ex.getMessage() + "\n");
                break;
            }

        } while (working);
        return "2";
    }

    //TODO: RESOLVER LISTA PAGINADA COM CASTING?

    public static <T extends Costumer> String paginatedCostumerList(List<T> costumers, int pageSize, int pageNumber) {
        String option = "";

        // validate input
        if (pageNumber < 0) pageNumber = 0;
        if (pageSize < 0) pageSize = 0;
        if (pageNumber + pageSize > costumers.size()) pageNumber = costumers.size() - pageSize;
        if (pageNumber < 0 || pageNumber >= costumers.size()) pageNumber = 0;

        // paginate the list
        List<T> paginatedCostumers = costumers.stream()
                .skip((pageNumber) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        // display the paginated list
        System.out.println("------ CLIENTES ------");
        System.out.println("");
        paginatedCostumers.forEach(Costumer::shortInfo);
        System.out.println("");

        // handle navigation
        if (costumers.size() == 0) {
            int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR CLIENTE");
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
            int choice = MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR CLIENTE", "ADICIONAR CLIENTE");
            switch (choice) {
                case 0:
                    option = "VOLTAR";
                    break;
                case 1:
                    paginatedCostumerList(costumers, pageSize, pageNumber + pageSize);
                    break;
                case 2:
                    paginatedCostumerList(costumers, pageSize, pageNumber - pageSize);
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
}

