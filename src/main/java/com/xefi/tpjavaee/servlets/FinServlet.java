package com.xefi.tpjavaee.servlets;

import com.xefi.tpjavaee.pojos.Joueur;
import com.xefi.tpjavaee.pojos.Partie;
import com.xefi.tpjavaee.pojos.Question;
import com.xefi.tpjavaee.services.PartieService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "finServlet", urlPatterns = { "/fin" })
public class FinServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Joueur joueur = (Joueur) session.getAttribute("joueur");
        Integer score = (Integer) session.getAttribute("score");
        if (joueur == null) {
            resp.sendRedirect("connexion");
            return;
        }

        Partie partie = (Partie) session.getAttribute("partie");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");

        String resultMessage = "You lost!";

        if (partie != null && currentQuestion == null) {
            resultMessage = "You win!";
        } else if (currentQuestion != null) {
            long timeElapsed = System.currentTimeMillis() - currentQuestion.getDateEnvoi().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli();
            if (timeElapsed >= 60000) {
                resultMessage = "You lost! Time's up!";
            }
        }

        req.setAttribute("result", resultMessage);
        req.setAttribute("score", 0);

        this.getServletContext().getRequestDispatcher("/WEB-INF/fin.jsp").forward(req, resp);
    }
}
