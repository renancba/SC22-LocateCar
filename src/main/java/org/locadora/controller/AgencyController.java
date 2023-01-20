package org.locadora.controller;


import org.locadora.database.Database;
import org.locadora.model.Address;
import org.locadora.model.Agency;
import org.locadora.utils.Input;
import org.locadora.utils.InputAddress;
import org.locadora.views.AgencyUI;

import java.util.ArrayList;
import java.util.List;

public class AgencyController {
    public void create() {
        AgencyUI.add();
    }

    public void saveAgency(String name, Address address) {

        Agency agency = new Agency(name, address);
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

    public String list() {
        Database db = Database.getInstance();
        return AgencyUI.list(db.getAgencies(), 5, 0);
    }

    public void listAll() {
        String option = list();
        if (option.equals("exibir")) {
            viewAgency();
        }
    }

    public void foundList(List<Agency> foundAgencies) {
        if (foundAgencies.size() == 0) {
            AgencyUI.viewAgency(foundAgencies.get(0));
        } else {
            if (AgencyUI.list(foundAgencies, 5, 0).equals("exibir")) {
                viewAgency();
            }
        }
    }

    public void edit(String option, Agency agency) throws Exception {
        Database db = Database.getInstance();
        switch (option) {
            case "name" -> agency.setName(Input.stringNotNullable("INFORME UM NOVO NOME: ", 3));
            case "address" -> agency.setAddress(InputAddress.exec(""));
        }

        if (db.updateAgency(agency)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| AGÊNCIA ATUALIZADA COM SUCESSO|");
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

    public void search() {
        List<Agency> agencies = new ArrayList<>();

        boolean working = true;

        while (working) {
            try {
                String option = AgencyUI.searchBy();

                if (option == "codigo") {
                    Agency agency = searchById();

                    if (agency != null) {
                        agencies.add(agency);
                    }

                } else if (option == "name") {

                    List<Agency> foundAgencies = searchString();
                    if (foundAgencies.size() > 0) {
                        agencies = foundAgencies;
                    }
                }

                foundList(agencies);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                System.out.println("voltando...\n");

            }
        }
    }


    public Agency searchById() throws Exception {
        Database db = Database.getInstance();
        int agencyId = Input.integer("INFORME O CÓDIGO DA AGÊNCIA: ");
        return db.searchByAgencyId(agencyId);
    }

    public List<Agency> searchString() throws Exception {
        Database db = Database.getInstance();
        String param = Input.stringNotNullable("INFORME O NOME DA AGÊNCIA OU O LOGRADOURO", 3);
        return db.searchAgencies(param.toUpperCase());
    }


    public void viewAgency() {
        Database db = Database.getInstance();
        try {
            int index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
            AgencyUI.viewAgency(db.getAgency(index));


        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }
}

