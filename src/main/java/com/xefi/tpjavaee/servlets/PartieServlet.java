package com.xefi.tpjavaee.servlets;

import com.xefi.tpjavaee.pojos.Joueur;
import com.xefi.tpjavaee.pojos.Partie;
import com.xefi.tpjavaee.pojos.Question;
import com.xefi.tpjavaee.pojos.Verbe;
import com.xefi.tpjavaee.services.PartieService;
import com.xefi.tpjavaee.services.QuestionService;
import com.xefi.tpjavaee.services.VerbeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@WebServlet(name = "partieServlet", urlPatterns = { "/partie" })
public class PartieServlet extends HttpServlet {

    private static final int TIME_LIMIT_SECONDS = 60;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("joueur") == null) {
            resp.sendRedirect("connexion");
            return;
        }

        Joueur joueur = (Joueur) session.getAttribute("joueur");
        PartieService partieService = new PartieService();

        Partie partie = (Partie) session.getAttribute("partie");
        if (partie == null) {
            partie = new Partie(joueur);
            partieService.addPartie(partie);
            session.setAttribute("partie", partie);
            session.setAttribute("score", 0);
        }

        Question currentQuestion = (Question) session.getAttribute("currentQuestion");
        if (currentQuestion == null) {
            currentQuestion = generateNewQuestion(partie);
            session.setAttribute("currentQuestion", currentQuestion);
        }

        LocalDateTime startTime = currentQuestion.getDateEnvoi();
        if (startTime != null && startTime.plusSeconds(TIME_LIMIT_SECONDS).isBefore(LocalDateTime.now())) {
            session.removeAttribute("currentQuestion");
            session.removeAttribute("partie");
            req.setAttribute("result", "Vous avez perdu, le temps est écoulé !");
            req.getRequestDispatcher("/WEB-INF/fin.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("verbe", currentQuestion.getVerbe());
        req.setAttribute("startTime", currentQuestion.getDateEnvoi());

        this.getServletContext().getRequestDispatcher("/WEB-INF/partie.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("joueur") == null) {
            resp.sendRedirect("connexion");
            return;
        }

        Partie partie = (Partie) session.getAttribute("partie");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");

        if (currentQuestion == null || partie == null) {
            resp.sendRedirect("partie");
            return;
        }

        String preteritAnswer = req.getParameter("preterit").trim();
        String pastParticipleAnswer = req.getParameter("pastParticiple").trim();

        Verbe verbe = currentQuestion.getVerbe();
        boolean isCorrect = verbe.getPreterit().equalsIgnoreCase(preteritAnswer) &&
                verbe.getParticipePasse().equalsIgnoreCase(pastParticipleAnswer);

        Integer score = (Integer) session.getAttribute("score");
        if (score == null) {
            score = 0;
            session.setAttribute("score", score);
        }

        if (isCorrect) {
            score++;
        }
        session.setAttribute("score", score);
        currentQuestion.setReponsePreterit(preteritAnswer);
        currentQuestion.setReponseParticipePasse(pastParticipleAnswer);

        currentQuestion.setDateReponse(LocalDateTime.now());

        QuestionService questionService = new QuestionService();
        questionService.addquestion(currentQuestion);

        if (currentQuestion.getDateEnvoi().plusSeconds(TIME_LIMIT_SECONDS).isBefore(LocalDateTime.now())) {
            session.removeAttribute("currentQuestion");
            session.removeAttribute("partie");
            req.setAttribute("result", "Vous avez perdu, le temps est écoulé !");
            req.getRequestDispatcher("/WEB-INF/fin.jsp").forward(req, resp);
            return;
        }

        if (isCorrect) {
            req.setAttribute("feedback", "Correct! The verb was " + verbe.getBaseVerbale() +
                    " - " + verbe.getPreterit() +
                    " - " + verbe.getParticipePasse());
            session.setAttribute("currentQuestion", null);
            resp.sendRedirect("partie");
        } else {
            req.setAttribute("feedback", "Incorrect. The correct answer was " + verbe.getBaseVerbale() +
                    " - " + verbe.getPreterit() +
                    " - " + verbe.getParticipePasse());
            session.removeAttribute("currentQuestion");
            session.removeAttribute("partie");
            req.setAttribute("result", "Vous avez perdu !");
            req.getRequestDispatcher("/WEB-INF/fin.jsp").forward(req, resp);
        }
    }

    private Question generateNewQuestion(Partie partie) {
        VerbeService verbeService = new VerbeService();
        List<Verbe> verbes = verbeService.getVerbes();

        Random random = new Random();
        Verbe randomVerbe = verbes.get(random.nextInt(verbes.size()));

        Question question = new Question();
        question.setIdPartie(partie.getId());
        question.setVerbe(randomVerbe);
        question.setDateEnvoi(LocalDateTime.now());

        return question;
    }
}
