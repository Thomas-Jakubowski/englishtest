package com.xefi.tpjavaee.dao;

import com.xefi.tpjavaee.pojos.Question;
import org.hibernate.Transaction;
import java.util.List;

public class QuestionDAO extends Dao {
    /**
     * Méthode permettant de récupérer une question suivant son id
     * @return Une Question
     */
    public Question findById(Long id) {
        Question question = null;

        try {
            session = openSession();
            question = (Question)session.get(Question.class, id);
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return question;
    }

    /**
     * Méthode permettant de récupérer les questions d'une partie
     * @return La liste des questions
     */
    public List<Question> findByPartieId(Long partieId) {
        List<Question> questions = null;

        try {
            session = openSession();
            questions = session.createQuery(
                            "select q from Question as q where q.idPartie = :partieId",
                            Question.class)
                    .setParameter("partieId", partieId)
                    .list();
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeSession();
        }

        return questions;
    }

    /**
     * Méthode permettant de créer une question
     * @return Question créée avec le nouvel id
     */
    public Question create(Question question) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.persist(question);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return question;
    }

    /**
     * Méthode permettant de mettre à jour une question
     * @return Question mise à jour
     */
    public Question update(Question question) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.merge(question);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw ex;
        } finally {
            closeSession();
        }

        return question;
    }

    /**
     * Méthode permettant de supprimer une question
     * @return void
     */
    public void delete(Question question) {
        Transaction transaction = null;

        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.remove(question);
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
