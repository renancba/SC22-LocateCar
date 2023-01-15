package org.locadora.views;

import org.locadora.utils.Input;
import java.util.ArrayList;
import java.util.List;

public class AddressUI {
    public static List<String> add() throws Exception {
        List<String> camposAdress = new ArrayList<>();
        String cep, street, number, state, city;

        System.out.println("ADICIONAR ENDEREÇO:");

        cep = Input.stringNotNullable("CEP: ", 3);
        camposAdress.add(cep);

        street = Input.stringNotNullable("LOGRADOURO: ", 3);
        camposAdress.add(street);

        number = Input.stringNotNullable("NUMERO: ", 3);
        camposAdress.add(number);

        state = Input.stringNotNullable("ESTADO: ", 3);
        camposAdress.add(state);

        city = Input.stringNotNullable("CIDADE: ", 3);
        camposAdress.add(city);

        return camposAdress;

    }
}
