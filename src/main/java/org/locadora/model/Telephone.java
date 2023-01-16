package org.locadora.model;

public class Telephone {

    private String ddd;
    private String number;

    public Telephone() {
    }

    public Telephone(String ddd, String number) {
        this.ddd = ddd;
        this.number = number;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

}
