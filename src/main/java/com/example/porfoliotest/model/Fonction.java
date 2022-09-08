package com.example.porfoliotest.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;


@Entity
public class Fonction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fonction_id;

    private String fonction_nom;

    public Long getFonction_id() {
        return fonction_id;
    }

    public void setFonction_id(Long fonction_id) {
        this.fonction_id = fonction_id;
    }

    public String getFonction_nom() {
        return fonction_nom;
    }

    public void setFonction_nom(String fonction_nom) {
        this.fonction_nom = fonction_nom;
    }
    @Override
    public String toString() {
        return "Fonction{" +
                "fonction_id=" + fonction_id +
                ", fonction_nom='" + fonction_nom + '\'' +
                '}';
    }
}
