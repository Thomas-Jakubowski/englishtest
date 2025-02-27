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
}
