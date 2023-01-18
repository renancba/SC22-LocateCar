package org.locadora.views;


import org.locadora.controller.AgencyController;
import org.locadora.controller.CostumerController;
import org.locadora.controller.OperationController;
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
                            "LISTAR CLIENTES", "ALTERAR DADOS DE UM CLIENTE", "RETORNAR AO MENU INICIAL");
                    submenuCostumer(option);

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
    public static void submenuCostumer (Integer option){
        CostumerController costumerController = new CostumerController();
        boolean executing = true;
        while (executing){
            switch (option) {
                case 0 -> {
                    System.out.println("CADASTRANDO CLIENTE...");
                    costumerController.create();
                    executing = false;
                }
                case 1 -> {
                    System.out.println("LISTAR CLIENTES");
                    costumerController.view();
                    executing = false;

                }
                case 2 -> {
                    System.out.println("ALTERANDO CLIENTE...");
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
                    operationController.listAll();
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
