package org.locadora.model.costumer;


import org.locadora.model.Address;
import org.locadora.model.Telephone;

import java.util.Objects;

public class LegalPerson extends Costumer {
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
