package org.locadora.views;


import org.locadora.controller.CustomerController;
import org.locadora.model.customer.Customer;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;

import java.util.List;

public class CustomerUI {
    public static void add() {
        CustomerController customerController = new CustomerController();
        AddressUI addressUI = new AddressUI();
        Integer option;
        String name;
        String surname;
        String cpf;
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

        option = MenuCreator.exec("DIGITE O TIPO DE CLIENTE A SER ADICIONADO","PESSOA FÍSICA", "PESSOA JURÍDICA");

        switch (option) {
            case 0 -> {
                try {
                    name = Input.stringNotNullable("NOME: ", 3);
                    surname = Input.stringNotNullable("SOBRENOME: ", 3);
                    cpf = Input.stringNotNullable("CPF: ", 3);
                    driverLicense = Input.stringNotNullable("HABILITAÇÃO: ", 3);
                    // TODO: NÃO SERIA MELHOR DE FATO TERMOS O ADDRESS UI E SÓ CHAMARMOS O MÉTODO ADD AQUI PARA FICAR LIMPO?
                    System.out.println("\nCADASTRO DE ENDEREÇO: ");
                    street = Input.stringNotNullable("NOME DA RUA: ", 3);
                    number = Input.stringNotNullable("NÚMERO: ", 3);
                    cep = Input.stringNotNullable("CEP: ", 3);
                    city = Input.stringNotNullable("CIDADE: ", 3);
                    state = Input.stringNotNullable("ESTADO: ", 3);
                    // TODO: MESMA SUGESTÃO PARA O CASO DO TELEFONE (CRIARIAMOS O TELEPHONEUI)
                    System.out.println("\nCADASTRO DE TELEFONE: ");
                    ddd = Input.stringNotNullable("DDD: ", 3);
                    telephone = Input.stringNotNullable("NÚMERO TELEFONE: ", 3);

                    customerController.saveNaturalPerson(name, surname, cpf, driverLicense, cep, street, number, city, state, ddd, telephone);

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
                    // TODO: NÃO SERIA MELHOR DE FATO TERMOS O ADDRESS UI E SÓ CHAMARMOS O MÉTODO ADD AQUI PARA FICAR LIMPO?
                    System.out.println("\nCADASTRO DE ENDEREÇO: ");
                    street = Input.stringNotNullable("NOME DA RUA: ", 3);
                    number = Input.stringNotNullable("NÚMERO: ", 3);
                    cep = Input.stringNotNullable("CEP: ", 3);
                    city = Input.stringNotNullable("CIDADE: ", 3);
                    state = Input.stringNotNullable("ESTADO: ", 3);
                    // TODO: MESMA SUGESTÃO PARA O CASO DO TELEFONE (CRIARIAMOS O TELEPHONEUI)
                    System.out.println("\nCADASTRO DE TELEFONE: ");
                    ddd = Input.stringNotNullable("DDD: ", 3);
                    telephone = Input.stringNotNullable("NÚMERO TELEFONE: ", 3);

                    customerController.saveLegalPerson(name, nickname, cnpj, ddd, telephone, cep, street, number, city, state);

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
    public static String listNaturalPerson(List<Customer> naturalPersonList) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        do {
            working = false;

            try {

                if (naturalPersonList.size() == 0) {
                    System.out.println("NENHUM CLIENTE ENCONTRADO PARA O TERMO INFORMADO.\n");
                    break;
                }

                if (naturalPersonList.size() > 1) {

                    System.out.println("");
                    for (Customer naturalPerson : naturalPersonList) {
                        System.out.println("-------- CLIENTE -------");
                        System.out.println("ID: " + index);
                        System.out.println("Nome: " + naturalPerson.getName() + " ");
                        System.out.println("------------------------");
                        index++;
                    }
                    System.out.println("");

                    int indexOption = getIndex();
                    if (indexOption > naturalPersonList.size()) {
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

                    CustomerUI.view(naturalPersonList.get(indexOption));

                } else {
                    CustomerUI.view(naturalPersonList.get(0));
                    break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage()+ "\n");
                break;
            }

        } while (working);
        return "2";
    }

    //TODO: RESOLVER LISTA PAGINADA COM CASTING? NÃO CONSIGO ACESSAR O INDEX QUANDO FAÇO CASTING PRA ACESSAR O GETSURNAME

    public static String paginatedList(List<Customer> customers) {
        boolean working = true;
        int ammount = 0;
        int start = 0;
        String option = "";

        while (working) {
            try {
                if (ammount == 0) {
                    ammount = Input.integer("Informe a quantidade de clientes por página: ");
                    System.out.println("");
                }

                if (start < 0 || start > customers.size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > customers.size()) ammount = customers.size();
                if (start + ammount > customers.size()) start = customers.size() - ammount;

                System.out.println("------ CLIENTES ------");

                for (int i = start; i < start + ammount; i++) {
                    if (i == customers.size()) break;
                    if (customers instanceof NaturalPerson) {
                        System.out.println("ID: " + i);
                        System.out.println("NOME: " + customers.get(i)/* + " " + customers.get(i).getSurname()*/);
                        System.out.println("----------------------");
                    }
                }
                System.out.println("");

                boolean better = true;

                while (better) {
                    if (customers.size() == 0) {
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR CLIENTE")) {
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
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR CLIENTE")) {
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

    public static void view(Customer customer) {

        if (customer instanceof NaturalPerson) {
            System.out.println("------- CLIENTE (PF) -------");
            System.out.println(" NOME: " + customer.getName());
            System.out.println(" SOBRENOME: " + ((NaturalPerson) customer).getSurname());
            System.out.println(" CNH: " + ((NaturalPerson) customer).getDriverLicense());
            System.out.println(" ENDEREÇO: " + customer.getAddress());
            System.out.println(" TELEFONE: " + customer.getTelephone());
            System.out.println("-----------------------");
            System.out.println("");
        } else {
            System.out.println("------- CLIENTE (PJ) -------");
            System.out.println(" NOME: " + customer.getName());
            System.out.println(" NOMEFANTASIA: " + ((LegalPerson) customer).getNickname());
            System.out.println(" CNPJ: " + ((LegalPerson) customer).getCnpj());
            System.out.println(" ENDEREÇO: " + customer.getAddress());
            System.out.println(" TELEFONE: " + customer.getTelephone());
            System.out.println("-----------------------");
            System.out.println("");
        }
    }
}

