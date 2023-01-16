package org.locadora.views;


import org.locadora.controller.CostumerController;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;

import java.util.List;

public class CostumerUI {
    public static void add() {
        CostumerController costumerController = new CostumerController();
        Integer option;
        String name;
        String surname;
        String cpf;
        String driverLicense;
        String nickname;
        String cnpj;

        option = MenuCreator.exec("DIGITE O TIPO DE CLIENTE A SER ADICIONADO","PESSOA FÍSICA", "PESSOA JURÍDICA");

        switch (option) {
            case 0 -> {
                try {
                    name = Input.stringNotNullable("NOME: ", 3);
                    surname = Input.stringNotNullable("SOBRENOME: ", 3);
                    cpf = Input.stringNotNullable("CPF: ", 3);
                    driverLicense = Input.stringNotNullable("HABILITAÇÃO: ", 3);

                    costumerController.saveNaturalPerson(name, surname, cpf, driverLicense);

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

                    costumerController.saveLegalPerson(name, nickname, cnpj);

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
    public static void listNaturalPerson(List<NaturalPerson> naturalPersonList) {
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
                    for (NaturalPerson naturalPerson : naturalPersonList) {
                        System.out.println("-------- CLIENTE -------");
                        System.out.println("ID: " + index);
                        System.out.println("Nome: " + naturalPerson.getName() + " " + naturalPerson.getSurname());
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

                    CostumerUI.view(naturalPersonList.get(indexOption));

                } else {
                    CostumerUI.view(naturalPersonList.get(0));
                    break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage()+ "\n");
                break;
            }

        } while (working);
    }

    //TODO: RESOLVER LISTA PAGINADA COM CASTING?

    public static String paginatedList(List<Costumer> costumers) {
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

                if (start < 0 || start > costumers.size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > costumers.size()) ammount = costumers.size();
                if (start + ammount > costumers.size()) start = costumers.size() - ammount;

                System.out.println("------ CLIENTES ------");

                for (int i = start; i < start + ammount; i++) {
                    if (i == costumers.size()) break;
                    if (costumers instanceof NaturalPerson) {
                        System.out.println("ID: " + i);
                        System.out.println("NOME: " + costumers.get(i)/* + " " + costumers.get(i).getSurname()*/);
                        System.out.println("----------------------");
                    }
                }
                System.out.println("");

                boolean better = true;

                while (better) {
                    if (costumers.size() == 0) {
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

    public static void view(Costumer costumer) {
        boolean working = true;

        while (working) {
            if(costumer instanceof NaturalPerson) {
                System.out.println("------- CLIENTE -------");
                System.out.println(" NOME: " + costumer.getName());
                System.out.println(" SOBRENOME: " + ((NaturalPerson)costumer).getSurname());
                System.out.println("-----------------------");
                System.out.println("");
            } else {
                System.out.println("------- CLIENTE -------");
                System.out.println(" NOME: " + costumer.getName());
                System.out.println(" NOMEFANTASIA: " + ((LegalPerson)costumer).getNickname());
                System.out.println("-----------------------");
                System.out.println("");
            }
        }
    }
}

