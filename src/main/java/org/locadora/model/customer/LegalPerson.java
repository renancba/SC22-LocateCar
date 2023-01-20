package org.locadora.model.customer;


import org.json.JSONObject;
import org.locadora.model.Address;
import org.locadora.model.Telephone;

import java.util.Objects;

public class LegalPerson extends Customer {
    private String cnpj;
    private String nickname;

    public LegalPerson() {
    }

    public LegalPerson(String name, String nickname, String cnpj, Telephone telephone, Address adress) {
        super(name, telephone, adress);
        this.cnpj = cnpj;
        this.nickname = nickname;
    }

    public LegalPerson(String name, String nickname, String cnpj) {
        super(name);
        this.nickname = nickname;
        this.cnpj = cnpj;

    }


    public JSONObject toJSONObject() {
        JSONObject costumerObject = new JSONObject();

        costumerObject.put("name", this.getName());
        costumerObject.put("nickname", this.getNickname());
        costumerObject.put("cnpj", this.getCnpj());

        if (address != null) {
            costumerObject.put("address", address.toJSONObject());

        }
        if (telephone != null) {
            costumerObject.put("telephone", telephone.toJSONObject());
        }

        return costumerObject;
    }

    public void shortInfo() {
        System.out.println(" RAZÃO SOCIAL: " + this.getName());
        System.out.println(" NOME FANTASIA: " + this.getNickname());
        System.out.println(" CNPJ: " + this.getCnpj());
        System.out.println("-------------------------");
    }

    @Override
    public String getDocument() {
        return this.cnpj;
    }

    public void completeInfo() {
        this.shortInfo();
        System.out.println(" ENDEREÇO: " + this.getAddress());
        System.out.println(" TELEFONE: " + this.getTelephone());
        System.out.println("-------------------------");
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    // TODO: SOBRESCREVER EQUALS, HASHCODE E TOSTRING

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegalPerson that = (LegalPerson) o;
        return Objects.equals(cnpj, that.cnpj) && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, nickname);
    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "cnpj='" + cnpj + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", telephone=" + telephone +
                ", address=" + address +
                '}';
    }


}
