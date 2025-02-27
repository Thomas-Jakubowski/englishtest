package com.xefi.tpjavaee.pojos;

import jakarta.persistence.*;

@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_postal", nullable = false)
    private String codePostal;

    @Column(nullable = false)
    private String nom;

    public Ville() {
    }

    public Ville(Long id, String codePostal, String nom) {
        this.id = id;
        this.codePostal = codePostal;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}