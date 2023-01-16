package org.locadora.controller;

import org.locadora.database.Database;
import org.locadora.model.costumer.LegalPerson;
import org.locadora.model.costumer.NaturalPerson;
import org.locadora.views.CostumerUI;

public class CostumerController {
    public String paginatedList() {
        Database db = Database.getInstance();
        return CostumerUI.paginatedList(db.getCostumers());
    }

    public void create() {
        CostumerUI.add();
    }

    //TODO: TEM COMO DEIXAR MAIS UNIVERSAL PARA NÃO REPETIR CÓDIGO?

    public void saveNaturalPerson(String name, String surname, String cpf,String driverLicense) {

        NaturalPerson naturalPerson = new NaturalPerson(name, surname, cpf, driverLicense);
        Database db = Database.getInstance();

        if (db.addCostumer(naturalPerson)) {
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
    public void saveLegalPerson(String name, String nickname, String cnpj) {

        LegalPerson legalPerson = new LegalPerson(name, nickname, cnpj);
        Database db = Database.getInstance();

        if (db.addCostumer(legalPerson)) {
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
            viewContactInfo();
        }
    }

    public void viewContactInfo() {
        Database db = Database.getInstance();
        try {
            int index = CostumerUI.getIndex();
            CostumerUI.view(db.get(index));

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }
}

