package org.locadora.utils;

public class GetIndex {

    public static int exec(String message) throws Exception {
        int index = 0;

        index = Input.integer(message);
        System.out.println("");
        return index;

    }
}
