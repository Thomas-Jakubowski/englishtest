package com.xefi.tpjavaee.services;

import com.xefi.tpjavaee.dao.JoueurDAO;
import com.xefi.tpjavaee.pojos.Joueur;

import java.util.List;

public class JoueurService {

    private JoueurDAO joueurDao = new JoueurDAO();

    public Joueur addJoueur(Joueur joueur) {
        return joueurDao.create(joueur);
    }

    public Joueur updateJoueur(Joueur joueur) {
        return joueurDao.update(joueur);
    }

    public void deleteJoueur(Joueur joueur) {
        joueurDao.delete(joueur);
    }

    public Joueur getJoueur(Long id) {
        return joueurDao.findById(id);
    }

    public Joueur getJoueurByEmail(String email) {
        return joueurDao.findByEmail(email);
    }

    public boolean auth(String email, String password) {
        return joueurDao.auth(email, password);
    }
}
