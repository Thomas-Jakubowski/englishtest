package com.xefi.tpjavaee.pojos;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Partie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_joueur", nullable = false)
    private Joueur joueur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
}
