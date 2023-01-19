package org.locadora.utils;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetLocalDateFromString {
    public static LocalDate Convert(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }
}