package org.locadora.model;

import java.util.Objects;

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

    @Override
    public String toString() {
        return " (" + ddd + ") " + number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Telephone telephone = (Telephone) o;
        return Objects.equals(ddd, telephone.ddd) && Objects.equals(number, telephone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, number);
    }

    public String getNumber() {
        return number;
    }
}
