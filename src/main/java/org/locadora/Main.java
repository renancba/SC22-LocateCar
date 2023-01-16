package org.locadora;

import org.locadora.database.Database;
import org.locadora.views.Home;

public class Main {
    public static void main(String[] args) {
        Database db = Database.getInstance();

        try {
            db.init();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Home.init();

        try {
            db.close();
            System.out.println("APLICAÇÃO ENCERRADA!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}