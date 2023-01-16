package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.CustomerController;
import org.locadora.controller.VehicleController;
import org.locadora.utils.MenuCreator;

public class Home {

    // TODO: TRATAR ERROS
    public static void init() {
        boolean executing = true;
        int option;

        while (executing) {

            option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CLIENTES", "VEÍCULOS",
                    "AGÊNCIAS", "ALUGUEL E DEVOLUÇÃO", "SAIR");


            switch (option) {
                case 0 -> {
                    System.out.println("ESCOLHEU CLIENTES");
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR CLIENTE",
                            "ALTERAR DADOS DE UM CLIENTE", "LISTAR CLIENTE", "RETORNAR AO MENU INICIAL");
                    submenuCustomer(option);

                }
                case 1 -> {
                    System.out.println("ESCOLHEU VEÍCULOS");
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR VEÍCULO",
                            "ALTERAR DADOS DE UM VEÍCULO", "BUSCAR VEÍCULO","RETORNAR AO MENU INICIAL");
                    submenuVehicle(option);
                }
                case 2 -> {
                    System.out.println("ESCOLHEU AGÊNCIA");
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR AGÊNCIA",
                            "ALTERAR DADOS DE UMA AGÊNCIA", "BUSCAR AGÊNCIA","LISTAR AGÊNCIAS","RETORNAR AO MENU INICIAL");
                    submenuAgency(option);
                }
                case 3 -> {
                    System.out.println("ESCOLHEU ALUGUEL E DEVOLUÇÃO");
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "ALUGAR VEÍCULO",
                            "DEVOLVER VEÍCULO", "GERAR COMPROVANTE","RETORNAR AO MENU INICIAL");
                    submenuRent(option);
                }
                case 4 -> {
                    System.out.println("ENCERRANDO APLICAÇÃO");
                    executing = false;
                }

                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }



    }

    // TODO: NÃO SOUBE EXATAMENTE SE ESSES SUBMENUS ENTRARIAM AQUI. CONTINUAR IMPLEMENTANDO.
    public static void submenuCustomer(Integer option){
        CustomerController customerController = new CustomerController();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("CADASTRANDO CLIENTE...");
                    customerController.create();
                    executing = false;
                }
                case 1 -> {
                    System.out.println("ALTERANDO CLIENTE...");
                    executing = false;
                }
                case 2 -> {
                    System.out.println("LISTAR CLIENTES");
                    customerController.view();
                    // TODO: CRIAR LISTA DE CLIENTES, NÃO EXIBIR OS DADOS COMPLETOS NA LISTA GERAL
                    executing = false;
                }
                case 3 -> { //retornar ao menu inicial
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
    public static void submenuVehicle (Integer option){
        VehicleController vehicleController = new VehicleController();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("CADASTRANDO VEÍCULO...");
                    vehicleController.create();
                    executing = false;
                }
                case 1 -> {
                    System.out.println("ALTERANDO VEÍCULO...");
                    executing = false;
                }
                case 2 -> {
                    System.out.println("BUSCANDO VEÍCULO");
                    vehicleController.search(null);
                    executing = false;
                }
                // TODO: CRIAR NOVO CASE COM LISTA DE VEÍCULOS, NÃO EXIBIR OS DADOS COMPLETOS NA LISTA GERAL
                case 3 -> { //retornar ao menu inicial
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
    public static void submenuAgency (Integer option){
        AgencyController agencyController = new AgencyController();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("CADASTRANDO AGÊNCIA...");
                    agencyController.create();
                    executing = false;

                }
                case 1 -> {
                    System.out.println("ALTERANDO AGÊNCIA...");
                    // TODO: CRIAR ALTERAÇÃO DE DADOS DA AGÊNCIA.
                    executing = false;

                }
                case 2 -> {
                    System.out.println("BUSCANDO AGÊNCIA");
                    agencyController.search(null);
                    // TODO: NÃO ESTÁ ACHANDO A AGÊNCIA
                    executing = false;
                }
                case 3 -> {
                    // TODO: CRIAR LISTA DE AGÊNCIAS, NÃO EXIBIR OS DADOS COMPLETOS NA LISTA GERAL
                    agencyController.view();
                    executing = false;
                }
                case 4 -> { //retornar ao menu inicial
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
    public static void submenuRent (Integer option){
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("ALUGANDO VEÍCULO");
                    executing = false;
                }
                case 1 -> {
                    System.out.println("DEVOLVENDO VEÍCULO...");
                    executing = false;
                }
                case 2 -> {
                    System.out.println("GERANDO COMPROVANTE");
                    executing = false;
                }
                case 3 -> { //retornar ao menu inicial
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}
