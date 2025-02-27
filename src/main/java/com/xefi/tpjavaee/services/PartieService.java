package com.xefi.tpjavaee.services;

import com.xefi.tpjavaee.dao.PartieDAO;
import com.xefi.tpjavaee.pojos.Partie;

public class PartieService {

    private PartieDAO joueurDao = new PartieDAO();

    public Partie addPartie(Partie partie) {
        return joueurDao.create(partie);
    }

    public Partie updatePartie(Partie partie) {
        return joueurDao.update(partie);
    }

    public void deletePartie(Partie partie) {
        joueurDao.delete(partie);
    }

    public Partie getPartie(Long id) {
        return joueurDao.findById(id);
    }
}
