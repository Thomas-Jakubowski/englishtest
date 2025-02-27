package com.xefi.tpjavaee.dao;

import com.xefi.tpjavaee.pojos.Joueur;
import org.hibernate.Transaction;
import java.util.List;

public class JoueurDAO extends Dao{
    public Joueur findById(Long id) {
        Joueur joueur = null;

        try {
            session = openSession();
            joueur = (Joueur)session.get(Joueur.class, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return joueur;
    }

    /**
     * Méthode permettant de récupérer un joueur par son email
     * @return Un Joueur
     */
    public Joueur findByEmail(String email) {
        Joueur joueur = null;

        try {
            session = openSession();
            joueur = session.createQuery(
                            "select j from Joueur as j where j.email = :email",
                            Joueur.class)
                    .setParameter("email", email)
                    .uniqueResult();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return joueur;
    }

    /**
     * Méthode permettant de récupérer la liste des joueurs
     * @return La liste des joueurs
     */
    public List<Joueur> findAll() {
        List<Joueur> joueurs = null;

        try {
            session = openSession();
            joueurs = session.createQuery("select j from Joueur as j", Joueur.class).list();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return joueurs;
    }

    /**
     * Méthode permettant de créer un joueur
     * @return Joueur créé avec le nouvel id
     */
    public Joueur create(Joueur joueur) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.persist(joueur);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return joueur;
    }

    /**
     * Méthode permettant de mettre à jour un joueur
     * @return Joueur mis à jour
     */
    public Joueur update(Joueur joueur) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.merge(joueur);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return joueur;
    }

    /**
     * Méthode permettant de supprimer un joueur
     * @return void
     */
    public void delete(Joueur joueur) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.remove(joueur);
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
    public boolean auth(String email, String password) {

        Joueur joueur = null;

        try {
            session = openSession();
            joueur = session.createQuery(
                            "select j from Joueur as j where j.email = :email and j.motDePasse = :password",
                            Joueur.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
            return joueur != null;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }
    }
}
