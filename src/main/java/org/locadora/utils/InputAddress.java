package org.locadora.utils;

import org.locadora.model.Address;

public class InputAddress {
    public static Address exec(String message) throws Exception {

        if(!message.isBlank()){
            System.out.println(message);
        }

        String cep = Input.stringNotNullable("CEP: ", 3);
        String street = Input.stringNotNullable("NOME DA RUA: ", 3);
        String number = Input.stringNotNullable("NÚMERO: ", 3);
        String city = Input.stringNotNullable("CIDADE: ", 3);
        String state = Input.stringNotNullable("ESTADO: ", 3);

        return new Address(cep, street, number, city, state);
    }
}
