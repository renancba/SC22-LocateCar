package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.views.AgencyUI;

public class AgencyController {
    //TODO: SÓ ESTÁ SENDO SALVA COM O NOME. ADICIONAR ADDRESS
    public void create() {
        AgencyUI.add();
    }

    public String paginatedList() {
        Database db = Database.getInstance();
        return AgencyUI.list(db.getAgencies());
    }
    public void saveAgency(String name, String street,String number,String cep,String city,String state) {
        Agency agency = new Agency(name, new Address(street, number, cep, city, state));
        Database db = Database.getInstance();

        if (db.addAgency(agency)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| AGÊNCIA SALVA |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| AGÊNCIA DUPLICADA! |");
            System.out.println("----------------------");
            System.out.println("");
        }
    }

    public void search(String value) {
        Database db = Database.getInstance();
        if (value != null) {
            AgencyUI.list(db.searchAgencies(value));
        } else {
            AgencyUI.search();
        }
    }

    public void view() {
        String option = paginatedList();
        if (option.equals("EDITAR")) {
            viewAgencyInfo();
        }
    }
    public void viewAgencyInfo() {
        Database db = Database.getInstance();
        try {
            int index = AgencyUI.getIndex();
            AgencyUI.view(db.getAgency(index));

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }
}

