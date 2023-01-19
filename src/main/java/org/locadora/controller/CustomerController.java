package org.locadora.controller;

import org.locadora.database.Database;
import org.locadora.model.Address;
import org.locadora.model.Telephone;
import org.locadora.model.customer.LegalPerson;
import org.locadora.model.customer.NaturalPerson;
import org.locadora.views.CustomerUI;

public class CustomerController {
    public String paginatedList() {
        Database db = Database.getInstance();
        return CustomerUI.paginatedCustomerList(db.getCustomers(),5,0);
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

    public void view() {
        String option = paginatedList();
        if (option.equals("EDITAR")) {
            viewCustomerInfo();
        }
    }

    public void viewCustomerInfo() {
        Database db = Database.getInstance();
        try {
            int index = CustomerUI.getIndex();
            db.getCustomer(index).completeInfo();

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }
}

