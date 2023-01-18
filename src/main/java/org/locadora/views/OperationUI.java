package org.locadora.views;

import org.locadora.controller.OperationController;
import org.locadora.model.RentalOperation;
import org.locadora.model.vehicle.Car;
import org.locadora.utils.Input;

import java.time.LocalDate;

public class OperationUI {
    public static void add() {
        OperationController operationController = new OperationController();
        try {
        /*
        Informar o cpf do cliente
        Informar o Id da Agência
        *
        * mostrar lista de carros
        * escolher um carro
        *        *
        * */

            String startDateString = Input.stringNotNullable("DATA DA LOCAÇÃO", 3);
            LocalDate startDate = LocalDate.parse(startDateString);

            String endDateString = Input.stringNotNullable("DATA DA ENTREGA", 3);
            LocalDate endDate = LocalDate.parse(endDateString);

//            operationController.save();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }

    }


}
