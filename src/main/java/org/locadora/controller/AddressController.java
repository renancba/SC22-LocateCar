package org.locadora.controller;

import org.locadora.model.Address;
import org.locadora.model.costumer.Costumer;
import org.locadora.views.AddressUI;

import java.util.List;

public class AddressController {
    public static void create(Costumer costumer) throws Exception {
        List<String> fieldsAddress = AddressUI.add();

        String cep = fieldsAddress.get(0).toString();
        String street = fieldsAddress.get(1).toString();
        String number = fieldsAddress.get(2).toString();
        String city = fieldsAddress.get(3).toString();
        String state = fieldsAddress.get(4).toString();

        Address address = new Address(cep, street.toUpperCase(), number, state.toUpperCase(), city.toUpperCase());
        System.out.println("| ENDEREÇO SALVO |");

        //TODO: AVALIAR SE PRECISAMOS DE VERIFICAÇÃO JÁ QUE NÃO VAMOS PERMITIR CADASTRAR MAIS DE UM ENDEREÇO POR CLIENTE
    }
}

