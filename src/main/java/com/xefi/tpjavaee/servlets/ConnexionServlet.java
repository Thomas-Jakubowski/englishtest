package com.xefi.tpjavaee.servlets;

import com.xefi.tpjavaee.pojos.Joueur;
import com.xefi.tpjavaee.pojos.Question;
import com.xefi.tpjavaee.services.JoueurService;
import com.xefi.tpjavaee.services.QuestionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "connexionServlet", value = "/connexion")
public class ConnexionServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // récupère la session
        HttpSession session = request.getSession();

        // crée les services
        JoueurService joueurService = new JoueurService();

        // récupère les paramètres
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String erreurs = "";

        if (email.isEmpty() || password.isEmpty()) {
            erreurs += "Email ou mot de passe non renseigné !";
        }

        if (erreurs.isEmpty()) {
            if(joueurService.auth(email, password)) {
                Joueur joueur = joueurService.getJoueurByEmail(email);
                session.setAttribute("joueur", joueur);
                response.sendRedirect("/partie");
            } else {
                erreurs += "Email ou mot de passe incorrect !";
            }
        }

        if (!erreurs.isEmpty()) {

            request.setAttribute("erreurs", erreurs);

            this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
        }
    }
}
