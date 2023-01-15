package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.Agency;
import org.locadora.views.AgencyUI;

public class AgencyController {
    //TODO: SÓ ESTÁ SENDO SALVA COM O NOME. ADICIONAR ADDRESS
    public void create() {
        AgencyUI.add();
    }
    public void saveAgency(String name) {
        Agency agency = new Agency();
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
}

