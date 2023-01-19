package org.locadora.utils;

public class GetIndex {

    public static int exec() throws Exception {
        int index = 0;

        index = Input.integer("DIGITE O ID QUE DESEJA EXIBIR: ");
        System.out.println("");
        return index;

    }
}
