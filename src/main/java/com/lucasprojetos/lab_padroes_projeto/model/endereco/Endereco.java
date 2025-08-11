package com.lucasprojetos.lab_padroes_projeto.model.endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Endereco {
    @Id
    private String cep;
    private String rua;
    private String complemento;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public Endereco(String cep, String rua, String complemento, String localidade, String uf, String ibge, String gia, String ddd, String siafi) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.localidade = localidade;
        this.uf = uf;
        this.ibge = ibge;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public Endereco() {
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return Objects.equals(cep, endereco.cep) && Objects.equals(rua, endereco.rua) && Objects.equals(complemento, endereco.complemento) && Objects.equals(localidade, endereco.localidade) && Objects.equals(uf, endereco.uf) && Objects.equals(ibge, endereco.ibge) && Objects.equals(gia, endereco.gia) && Objects.equals(ddd, endereco.ddd) && Objects.equals(siafi, endereco.siafi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, rua, complemento, localidade, uf, ibge, gia, ddd, siafi);
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "cep=" + cep +
                ", rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", localidade='" + localidade + '\'' +
                ", uf='" + uf + '\'' +
                ", ibge='" + ibge + '\'' +
                ", gia='" + gia + '\'' +
                ", ddd='" + ddd + '\'' +
                ", siafi='" + siafi + '\'' +
                '}';
    }
}
