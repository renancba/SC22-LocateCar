package org.locadora.utils;


import org.locadora.model.Telephone;

public class InputTelephone {
    public static Telephone exec(String message) throws Exception {

        if(!message.isBlank()){
            System.out.println(message);
        }

        String ddd = Input.stringNotNullable("DDD: ", 3);
        String number = Input.stringNotNullable("NÚMERO: ", 3);


        return new Telephone(ddd, number);
    }
}
