package org.locadora.utils;

import java.time.LocalDate;

public class GetDate {
    public static LocalDate exec(String message) {
        LocalDate date = null;
        try {
            String endDateString = Input.stringNotNullable(message, 3);
            date = LocalDate.parse(endDateString);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            System.out.println("voltando...\n");
        }
        return date;

    }
}
