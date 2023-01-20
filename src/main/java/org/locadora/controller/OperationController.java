package org.locadora.controller;

import org.locadora.database.Database;
import org.locadora.model.Agency;
import org.locadora.model.RentalOperation;
import org.locadora.model.customer.Customer;
import org.locadora.model.vehicle.Vehicle;
import org.locadora.utils.GetDate;
import org.locadora.utils.Input;
import org.locadora.views.OperationUI;

import java.time.LocalDate;

public class OperationController {

    public void create() {
        OperationUI.add();
    }

    public void save(Customer customer, Agency agency, Vehicle vehicle, LocalDate startDate, LocalDate endDate) {

        Database db = Database.getInstance();
        RentalOperation operation = new RentalOperation(customer, vehicle, startDate, endDate, agency);

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

    public String list() {
        Database db = Database.getInstance();
        return OperationUI.List(db.getOperations(), 5, 0);
    }

    public void listAll() {
        String options = list();
        if (options.equals("exibir")) {
            viewOperation();
        }
    }

    public void search() {
        Database db = Database.getInstance();
        RentalOperation operation = null;
        int tentativas = 0;

        boolean working = true;

        while (working) {
            try {
                int operationId = Input.integer("INFORME O NUMERO DO CONTRATO: ");
                operation = db.searchByOperationId(operationId);

                if (operation == null) {
                    if (tentativas > 3) {
                        working = false;
                        continue;
                    }

                    tentativas += 1;
                    continue;
                }

                foundList(operation);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                if (tentativas > 3) {
                    working = false;
                    System.out.println("voltando...\n");
                    continue;
                }

                tentativas += 1;
            }
        }
    }

    public void foundList(RentalOperation operation) {
        OperationUI.viewOperation(operation);
    }

    public void viewOperation() {
        Database db = Database.getInstance();
        try {
            int index = Input.integer("INFORME O ID DA LOCACAO: ");
            OperationUI.viewOperation(db.getOperation(index));

        } catch (Exception ex) {
            System.out.println(ex.getMessage() + "VOLTANDO AO MENU PRINCIPAL ...\n");
        }
    }

    public void extendReturnDate(RentalOperation operation) {
        LocalDate newDate = GetDate.exec("DATA DA ENTREGA: ");
        operation.updateEndDate(newDate);
    }

    public void returVehicle(RentalOperation operation) {
        Agency agency = OperationUI.returnVehicle(operation);
        operation.returnVehicle(agency);
    }


}
