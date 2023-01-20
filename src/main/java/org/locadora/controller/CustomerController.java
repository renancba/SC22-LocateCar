package org.locadora.controller;

import org.locadora.database.Database;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.model.Telephone;
import org.locadora.model.customer.Customer;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.utils.GetIndex;
import org.locadora.utils.Input;
import org.locadora.utils.InputAddress;
import org.locadora.views.AgencyUI;
import org.locadora.views.CustomerUI;

import java.util.List;

public class CustomerController {
    public String paginatedList() {
        Database db = Database.getInstance();
        return CustomerUI.list(db.getCustomers(),5,0);
    }

    public void create() {
        CustomerUI.add();
    }

    //TODO: TEM COMO DEIXAR MAIS UNIVERSAL PARA NÃO REPETIR CÓDIGO?

    public void saveNaturalPerson(String name, String surname, String cpf, String driverLicense, String street, String number, String cep, String city, String state, String ddd, String telephone) {

        NaturalPerson naturalPerson = new NaturalPerson(name, surname, cpf, driverLicense, new Telephone(ddd, telephone), new Address(cep, street, number, city, state));
        Database db = Database.getInstance();

        if (db.addCustomer(naturalPerson)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| CONTATO SALVO |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| CONTATO DUPLICADO! |");
            System.out.println("----------------------");
            System.out.println("");
        }
    }
    public void saveLegalPerson(String name, String nickname, String cnpj, String ddd, String telephone, String cep, String street, String number, String city, String state) {

        LegalPerson legalPerson = new LegalPerson(name, nickname, cnpj, new Telephone(ddd,telephone), new Address(cep,street,number,city,state));
        Database db = Database.getInstance();

        if (db.addCustomer(legalPerson)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| CONTATO SALVO |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| CONTATO DUPLICADO! |");
            System.out.println("----------------------");
            System.out.println("");
        }
    }
    public String list() {
        Database db = Database.getInstance();
        return CustomerUI.list(db.getCustomers(), 5, 0);
    }

    public void listAll() {
        String option = list();
        if (option.equals("exibir")) {
            viewCustomer();
        }
    }


    public void edit(String option, Customer customer) throws Exception {
        Database db = Database.getInstance();

        //SE PESSOA FISICA
        if (customer instanceof NaturalPerson) {
            switch (option) {
                case "name" -> customer.setName(Input.stringNotNullable("INFORME UM NOVO NOME: ", 3));
                case "surname" -> customer.setAddress(InputAddress.exec(""));
                case "driverLicense" -> ((NaturalPerson) customer).setDriverLicense(Input.stringNotNullable("INFORME A NOVA CNH: ", 3));
            }

            //SE PPESSOA JURIDICA
        } else {
            switch (option){
                case "name" -> customer.setName(Input.stringNotNullable("INFORME UM NOVO NOME: ", 3));
                case "nickname" -> ((LegalPerson) customer).setNickname(Input.stringNotNullable("INFORME O NOVO NOME FANTASIA: ", 3));
            }
        }

            if (db.updateCustomer(customer)) {
                System.out.println("");
                System.out.println("-----------------");
                System.out.println("| CLIENTE ATUALIZADO COM SUCESSO|");
                System.out.println("-----------------");
                System.out.println("");
            } else {
                System.out.println("");
                System.out.println("----------------------");
                System.out.println("| ERRO AO ATUALIZAR AGENDA. TENTE NOVAMENTE MAIS TARDE |");
                System.out.println("----------------------");
                System.out.println("");
            }
    }


    public void viewCustomer() {
        Database db = Database.getInstance();
        try {
            int index = GetIndex.exec("DIGITE O ID QUE DESEJA EXIBIR: ");
            CustomerUI.viewCustomer(db.getCustomer(index));


        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }

}

