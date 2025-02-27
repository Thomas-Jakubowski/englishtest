package com.xefi.tpjavaee.dao;

import com.xefi.tpjavaee.pojos.Partie;
import org.hibernate.Transaction;
import java.util.List;

public class PartieDAO extends Dao{
    /**
     * Méthode permettant de récupérer une partie suivant son id
     * @return Une Partie
     */
    public Partie findById(Long id) {
        Partie partie = null;

        try {
            session = openSession();
            partie = (Partie)session.get(Partie.class, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return partie;
    }

    /**
     * Méthode permettant de récupérer les parties d'un joueur
     * @return La liste des parties
     */
    public List<Partie> findByJoueurId(Long joueurId) {
        List<Partie> parties = null;

        try {
            session = openSession();
            parties = session.createQuery(
                            "select p from Partie as p where p.joueur.id = :joueurId",
                            Partie.class)
                    .setParameter("joueurId", joueurId)
                    .list();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return parties;
    }

    /**
     * Méthode permettant de créer une partie
     * @return Partie créée avec le nouvel id
     */
    public Partie create(Partie partie) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.persist(partie);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return partie;
    }

    /**
     * Méthode permettant de mettre à jour une partie
     * @return Partie mise à jour
     */
    public Partie update(Partie partie) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.merge(partie);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return partie;
    }

    /**
     * Méthode permettant de supprimer une partie
     * @return void
     */
    public void delete(Partie partie) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.remove(partie);
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
