package org.locadora.controller;

import org.locadora.database.Database;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.costumer.Costumer;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.views.OperationUI;

import java.time.LocalDate;

public class OperationController {

    public void create() {
        OperationUI.add();
    }

    public void save(Costumer costumer, Agency agency, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {

        Database db = Database.getInstance();
        RentalOperation operation = new RentalOperation(costumer,vehicle,  startDate, endDate, agency);

        if (db.addOperation(operation)) {
            System.out.println("");
            System.out.println("-----------------");
            System.out.println("| OPERAÇÂO CRIADA COM SUCESSO |");
            System.out.println("-----------------");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println("----------------------");
            System.out.println("| OPERAÇÃO DUPLICADA! |");
            System.out.println("----------------------");
            System.out.println("");
        }

    }
    public String list(){
        Database db = Database.getInstance();
        return OperationUI.List(db.getOperations(), 5, 0);
    }

    public void ListAll(){
        String options = list();

        if (options.equals("EDITAR")) {
            viewOperationInfo();
        }
    }

    public void viewOperationInfo() {
        Database db = Database.getInstance();
        try {
            int index = OperationUI.getIndex();
            OperationUI.view(db.getOperation(index));

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }


}
