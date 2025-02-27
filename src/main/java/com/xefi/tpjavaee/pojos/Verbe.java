package com.xefi.tpjavaee.pojos;

import jakarta.persistence.*;

@Entity
public class Verbe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String baseVerbale;

    @Column(nullable = false)
    private String preterit;

    @Column(nullable = false)
    private String participePasse;

    @Column(nullable = false)
    private String traduction;

    public Verbe(String baseVerbale, String participePasse, String preterit, String traduction) {
        this.baseVerbale = baseVerbale;
        this.participePasse = participePasse;
        this.preterit = preterit;
        this.traduction = traduction;
    }

    public Verbe() {
    }

    public String getBaseVerbale() {
        return baseVerbale;
    }

    public void setBaseVerbale(String baseVerbale) {
        this.baseVerbale = baseVerbale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreterit() {
        return preterit;
    }

    public void setPreterit(String preterit) {
        this.preterit = preterit;
    }

    public String getParticipePasse() {
        return participePasse;
    }

    public void setParticipePasse(String participePasse) {
        this.participePasse = participePasse;
    }

    public String getTraduction() {
        return traduction;
    }

    public void setTraduction(String traduction) {
        this.traduction = traduction;
    }
}
