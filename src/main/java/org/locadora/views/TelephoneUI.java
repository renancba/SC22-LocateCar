package org.locadora.views;

import org.locadora.utils.Input;

import java.util.ArrayList;
import java.util.List;

public class TelephoneUI {
    public static List<String> add() throws Exception {
        List<String> camposTelephone = new ArrayList<>();
        String ddd, numero;

        System.out.println("ADICIONAR TELEFONE:");

        ddd = Input.stringNotNullable("DDD: ", 3);
        camposTelephone.add(ddd);

        numero = Input.stringNotNullable("NÚMERO: ", 3);

        camposTelephone.add(numero);

        return camposTelephone;
    }
}

