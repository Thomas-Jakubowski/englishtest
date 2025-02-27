package com.xefi.tpjavaee.services;

import com.xefi.tpjavaee.dao.QuestionDAO;
import com.xefi.tpjavaee.pojos.Question;

import java.util.List;

public class QuestionService {

    private QuestionDAO questionDao = new QuestionDAO();

    public Question addquestion(Question question) {
        return questionDao.create(question);
    }

    public Question updatequestion(Question question) {
        return questionDao.update(question);
    }

    public void deletequestion(Question question) {
        questionDao.delete(question);
    }

    public Question getquestion(Long id) {
        return questionDao.findById(id);
    }
}
