package org.locadora.model.customer;

import org.json.JSONObject;
import org.locadora.model.Address;
import org.locadora.model.Telephone;

import java.util.Objects;

public class NaturalPerson extends Customer {
    private String surname;
    private String cpf;
    private String driverLicense;

    public NaturalPerson() {
    }

    public NaturalPerson(String name, String surname, String cpf, String driverLicense,
                         Telephone telephone, Address address) {
        super(name, telephone, address);
        this.surname = surname;
        this.cpf = cpf;
        this.driverLicense = driverLicense;
    }

    public NaturalPerson(String name, String surname, String cpf, String driverLicense) {
        super(name);
        this.surname = surname;
        this.cpf = cpf;
        this.driverLicense = driverLicense;
    }

    public JSONObject toJSONObject() {
        JSONObject costumerObject = new JSONObject();

        costumerObject.put("name", this.getName());
        costumerObject.put("surname", this.getSurname());
        costumerObject.put("cpf", this.getCpf());
        costumerObject.put("driverLicense", this.getDriverLicense());

        if (address != null) {
            costumerObject.put("address", address.toJSONObject());

        }
        if (telephone != null) {
            costumerObject.put("telephone", telephone.toJSONObject());
        }

        return costumerObject;
    }

    public void shortInfo() {
        System.out.println(" NOME: " + this.getName());
        System.out.println(" SOBRENOME: " + this.getSurname());
        System.out.println(" CPF: " + this.getCpf());
    }

    @Override
    public String getDocument() {
        return this.cpf;
    }

    public void completeInfo() {
        this.shortInfo();
        System.out.println(" CNH: " + this.getDriverLicense());
        System.out.println(" ENDEREÇO: " + this.getAddress());
        System.out.println(" TELEFONE: " + this.getTelephone());
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getFullName(String fullname) {
        return this.getName() + this.getSurname();
    }

    // TODO: SOBRESCREVER EQUALS, HASHCODE E TOSTRING

    // PRECISA DO NAME PARA O .EQUALS?
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NaturalPerson that = (NaturalPerson) o;
        return Objects.equals(surname, that.surname) && Objects.equals(cpf, that.cpf) && Objects.equals(driverLicense, that.driverLicense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, cpf, driverLicense);
    }

    @Override
    public String toString() {
        return "NaturalPerson{" +
                "surname='" + surname + '\'' +
                ", cpf='" + cpf + '\'' +
                ", driverLicense='" + driverLicense + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", address=" + address +
                '}';
    }
}
