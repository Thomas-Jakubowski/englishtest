package com.xefi.tpjavaee.dao;

import com.xefi.tpjavaee.pojos.Ville;
import org.hibernate.Transaction;
import java.util.List;

public class VilleDAO extends Dao {
    /**
     * Méthode permettant de récupérer une ville suivant son id
     * @return Une Ville
     */
    public Ville findById(Long id) {
        Ville ville = null;

        try {
            session = openSession();
            ville = (Ville)session.get(Ville.class, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return ville;
    }

    /**
     * Méthode permettant de récupérer la liste des villes
     * @return La liste des villes
     */
    public List<Ville> findAll() {
        List<Ville> villes = null;

        try {
            session = openSession();
            villes = session.createQuery("select v from Ville as v", Ville.class).list();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return villes;
    }

    /**
     * Méthode permettant de créer une ville
     * @return Ville créée avec le nouvel id
     */
    public Ville create(Ville ville) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.persist(ville);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return ville;
    }

    /**
     * Méthode permettant de mettre à jour une ville
     * @return Ville mise à jour
     */
    public Ville update(Ville ville) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.merge(ville);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return ville;
    }

    /**
     * Méthode permettant de supprimer une ville
     * @return void
     */
    public void delete(Ville ville) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.remove(ville);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }
    }
}
