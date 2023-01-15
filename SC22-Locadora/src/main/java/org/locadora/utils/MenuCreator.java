package org.locadora.utils;

public class MenuCreator {
    public static int exec(String Message, String... options) {
        int chooseYourDestiny = 0;
        boolean isInvalid;

        do {
            isInvalid = false;
            try {
                System.out.println(Message);
                for (int i = 0; i < options.length; i++) {
                    System.out.printf("[%d] - %s\n", i, options[i]);
                }
                chooseYourDestiny = Input.integer("Escolha uma das opções: ");


                System.out.println("");

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                break;
            }

        } while (isInvalid);

        return chooseYourDestiny;
    }
}
