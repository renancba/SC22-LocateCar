package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.OperationController;
import org.locadora.utils.MenuCreator;

public class Home {

    // TODO: TRATAR ERROS
    public static void init() {
        boolean executing = true;
        int option;

        while (executing) {

            option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "SAIR", "CLIENTES", "VEÍCULOS",
                    "AGÊNCIAS", "LOCAÇÕES");


            switch (option) {
                case 1 -> {
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR CLIENTE",
                            "LISTAR CLIENTES", "ALTERAR DADOS DE UM CLIENTE", "RETORNAR AO MENU INICIAL");
                    submenuCustomer(option);

                }
                case 2 -> {
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR VEÍCULO", "LISTAR VEÍCULOS",
                            "ALTERAR DADOS DE UM VEÍCULO", "BUSCAR VEÍCULO","RETORNAR AO MENU INICIAL");
                    submenuVehicle(option);
                }
                case 3 -> {
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "CADASTRAR AGÊNCIA", "LISTAR AGÊNCIAS",
                            "ALTERAR DADOS DE UMA AGÊNCIA", "BUSCAR AGÊNCIA","RETORNAR AO MENU INICIAL");
                    submenuAgency(option);
                }
                case 4 -> {
                    option = MenuCreator.exec("DIGITE A OPÇÃO DESEJADA:", "ALUGAR VEÍCULO", "LISTAR CONTRATOS", "PESQUISAR NUMERO DO CONTRATO" ,"RETORNAR AO MENU INICIAL");
                    submenuRent(option);
                }
                case 0 -> {
                    System.out.println("ENCERRANDO APLICAÇÃO");
                    executing = false;
                }

                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }

    // TODO: NÃO SOUBE EXATAMENTE SE ESSES SUBMENUS ENTRARIAM AQUI. CONTINUAR IMPLEMENTANDO.
    public static void submenuCustomer(Integer option){
        CustomerUI customerUI = new CustomerUI();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> { // CADASTRAR
                    customerUI.add();
                    executing = false;
                }
                case 1 -> { // LISTAR
                    customerUI.paginatedCustomerList(null, 5,0);
                    executing = false;

                }
                case 2 -> { // ALTERAR
                    System.out.println("ALTERANDO CLIENTE...");
                    executing = false;
                }
                case 3 -> { // RETORNAR AO MENU INICIAL
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
    public static void submenuVehicle (Integer option){
        VehicleUI vehicleUI = new VehicleUI();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> { // CADASTRAR
                    vehicleUI.add();
                    executing = false;
                }
                case 1 -> { // LISTAR
                    System.out.println("LISTANDO VEÍCULO...");
                    vehicleUI.paginatedList(null); //TODO: FAZER METODO PAGINADO COMO O DA CUSTOMER E ADICIONAR AQUI
                    executing = false;
                }
                case 2 -> { // ALTERAR
                    System.out.println("ALTERANDO VEÍCULO..."); // TODO: CRIAR UM ALTERAR VEÍCULO
                    executing = false;
                }
                case 3 -> {
                    System.out.println("BUSCANDO VEÍCULO");
                    vehicleUI.search();
                    executing = false;
                }
                case 4 -> { // RETORNAR AO MENU INICIAL
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
//                    agencyController.search(null);
                    // TODO: NÃO ESTÁ ACHANDO A AGÊNCIA
                    executing = false;
                }
                case 3 -> {
                    // TODO: CRIAR LISTA DE AGÊNCIAS, NÃO EXIBIR OS DADOS COMPLETOS NA LISTA GERAL
//                    agencyController.view();
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

        OperationController operationController = new OperationController();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("LOCAR UM VEÍCULO");
                    operationController.create();
                    executing = false;
                }
                case 1 -> {
                    System.out.println("LISTAR CONTRATOS");
                    operationController.list();
                    executing = false;
                }
                case 2 -> {
                    System.out.println("PESQUISAR POR NUMERO DE CONTRATO");
                    executing = false;
                }
                case 3 -> {
                    System.out.println("PESQUISAR POR NOME DO CLIENTE");
                    executing = false;
                }
                case 4 -> { //retornar ao menu inicial
                    executing = false;
                }
                default -> System.out.println("OPÇÃO INVÁLIDA");
            }
        }
    }
}
