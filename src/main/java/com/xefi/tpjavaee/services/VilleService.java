package com.xefi.tpjavaee.services;

import com.xefi.tpjavaee.dao.VilleDAO;
import com.xefi.tpjavaee.pojos.Ville;

import java.util.List;

public class VilleService {
    private VilleDAO villeDAO = new VilleDAO();

    public Ville addVille(Ville ville) {
        return villeDAO.create(ville);
    }

    public Ville updateVille(Ville ville) {
        return villeDAO.update(ville);
    }

    public void deleteVille(Ville ville) {
        villeDAO.delete(ville);
    }

    public Ville getVille(Long id) {
        return villeDAO.findById(id);
    }

    public List<Ville> getVilles() {
        return  villeDAO.findAll();
    }
}
