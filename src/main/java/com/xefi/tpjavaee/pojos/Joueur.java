package com.xefi.tpjavaee.pojos;

import jakarta.persistence.*;

@Entity
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false)
    private String motDePasse;

    @ManyToOne
    @JoinColumn(name = "id_ville", nullable = false)
    private Ville ville;

    @Column(nullable = false)
    private Integer niveau;

    public Joueur() {
    }

    public Joueur(Integer niveau, Ville ville, String motDePasse, String prenom, String nom, String email) {
        this.id = id;
        this.niveau = niveau;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }
}
