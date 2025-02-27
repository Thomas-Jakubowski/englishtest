package com.xefi.tpjavaee.services;

import com.xefi.tpjavaee.dao.VerbeDAO;
import com.xefi.tpjavaee.pojos.Verbe;

import java.util.List;

public class VerbeService {
    private VerbeDAO verbeDAO = new VerbeDAO();

    public Verbe addVerbe(Verbe verbe) {
        return verbeDAO.create(verbe);
    }

    public Verbe updateVerbe(Verbe verbe) {
        return verbeDAO.update(verbe);
    }

    public void deleteVerbe(Verbe verbe) {
        verbeDAO.delete(verbe);
    }

    public Verbe getVerbe(Long id) {
        return verbeDAO.findById(id);
    }

    public List<Verbe> getVerbes() {
        return  verbeDAO.findAll();
    }
}
