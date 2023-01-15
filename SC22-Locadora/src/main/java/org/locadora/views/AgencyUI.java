package org.locadora.views;




import org.locadora.controller.AgencyController;
import org.locadora.model.Agency;
import org.locadora.utils.Input;
import org.locadora.utils.MenuCreator;


import java.util.List;

public class AgencyUI {
    public static void add() {
        AgencyController agencyController = new AgencyController();
        Integer option;
        String name;

        try {
            name = Input.stringNotNullable("NOME DA AGÊNCIA: ", 3);

            agencyController.saveAgency(name);

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
    public static void list(List<Agency> agencies) {
        int index = 0;
        int tentativas = 0;
        boolean working;

        do {
            working = false;

            try {

                if (agencies.size() == 0) {
                    System.out.println("NENHUMA AGÊNCIA ENCONTRADA PARA O TERMO INFORMADO.\n");
                    break;
                }

                if (agencies.size() > 1) {

                    System.out.println("");
                    for (Agency agency : agencies) {
                        System.out.println("-------- AGÊNCIA -------");
                        System.out.println("ID: " + index);
                        System.out.println("Nome: " + agency.getName());
                        System.out.println("Endereço: " + agency.getAddress());
                        System.out.println("------------------------");
                        index++;
                    }
                    System.out.println("");

                    int indexOption = getIndex();
                    if (indexOption > agencies.size()) {
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

                    AgencyUI.view(agencies.get(indexOption));

                } else {
                    AgencyUI.view(agencies.get(0));
                    break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage()+ "\n");
                break;
            }

        } while (working);
    }
    public static String paginatedList(List<Agency> agencies) {
        boolean working = true;
        int ammount = 0;
        int start = 0;
        String option = "";

        while (working) {
            try {
                if (ammount == 0) {
                    ammount = Input.integer("Informe a quantidade de agências por página: ");
                    System.out.println("");
                }

                if (start < 0 || start > agencies.size()) start = 0;
                if (ammount < 0) ammount = 0;
                if (ammount > agencies.size()) ammount = agencies.size();
                if (start + ammount > agencies.size()) start = agencies.size() - ammount;

                System.out.println("------ AGÊNCIAS ------");

                for (int i = start; i < start + ammount; i++) {
                    if (i == agencies.size()) break;
                    System.out.println("ID: " + i);
                    System.out.println("NOME: " + agencies.get(i).getName());
                    System.out.println("ENDEREÇO: " + agencies.get(i).getAddress());
                    System.out.println("----------------------");
                }
                System.out.println("");

                boolean better = true;

                while (better) {
                    if (agencies.size() == 0) {
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "ADICIONAR AGÊNCIA")) {
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
                        switch (MenuCreator.exec(".:: NAVEGAÇÃO ::.", "SAIR", "PAGINA SEGUINTE", "PAGINA ANTERIOR", "EXIBIR AGÊNCIA")) {
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
        AgencyController agencyController = new AgencyController();
        String term = Input.string("DIGITE O NOME OU PARTE DO NOME DA AGÊNCIA OU LOGRADOURO: ");
        agencyController.search(term.toUpperCase());
    }
    public static void view(Agency agency) {
        boolean working = true;

        while (working) {
            System.out.println("------- AGÊNCIA -------");
            System.out.println(" NOME: " + agency.getName());
            System.out.println(" ENDEREÇO: " + agency.getAddress());
            System.out.println("-----------------------");
            System.out.println("");
        }
    }
}
