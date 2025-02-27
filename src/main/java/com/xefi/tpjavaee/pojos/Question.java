package com.xefi.tpjavaee.pojos;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Question {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "idpartie", nullable = false)
    private Long idPartie;

    @ManyToOne
    @JoinColumn(name = "idverbe", nullable = false)
    private Verbe verbe;

    @Column(name = "reponse_preterit")
    private String reponsePreterit;

    @Column(name = "reponse_participe_passe")
    private String reponseParticipePasse;

    @Column(name = "date_envoi", nullable = false)
    private LocalDateTime dateEnvoi;

    @Column(name = "date_reponse")
    private LocalDateTime dateReponse;

    public Question() {
    }

    public Question(Long idPartie, Verbe verbe, String reponsePreterit, String reponseParticipePasse, LocalDateTime dateEnvoi, LocalDateTime dateReponse) {
        this.idPartie = idPartie;
        this.verbe = verbe;
        this.reponsePreterit = reponsePreterit;
        this.reponseParticipePasse = reponseParticipePasse;
        this.dateEnvoi = dateEnvoi;
        this.dateReponse = dateReponse;
    }
}
