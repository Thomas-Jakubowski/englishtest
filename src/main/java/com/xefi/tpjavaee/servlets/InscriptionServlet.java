package com.xefi.tpjavaee.servlets;

import com.xefi.tpjavaee.pojos.Joueur;
import com.xefi.tpjavaee.pojos.Verbe;
import com.xefi.tpjavaee.pojos.Question;
import com.xefi.tpjavaee.pojos.Ville;
import com.xefi.tpjavaee.services.JoueurService;
import com.xefi.tpjavaee.services.VerbeService;
import com.xefi.tpjavaee.services.QuestionService;
import com.xefi.tpjavaee.services.VilleService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "inscriptionServlet", urlPatterns = { "/inscription" })
public class InscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // créer les services
        VilleService villeService = new VilleService();

        List<Ville> villes = villeService.getVilles();

        req.setAttribute("villes", villes);

        this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // récupère la session
        HttpSession session = req.getSession();

        // stock les erreurs
        String erreurs = "";

        // créer les services
        VilleService villeService = new VilleService();
        JoueurService joueurService = new JoueurService();

        // récupère les données du formulaire
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String villeString = req.getParameter("ville");

        // valide les données
        if (nom.isEmpty()) {
            erreurs += "Le champ nom doit être renseigné<br>";
        }
        if (prenom.isEmpty()) {
            erreurs += "Le champ prénom doit être renseigné<br>";
        }
        if (password.isEmpty()) {
            erreurs += "Le champ mot de passe doit être renseigné<br>";
        }
        if (email.isEmpty()) {
            erreurs += "Le email doit être renseigné<br>";
        }

        if (erreurs.isEmpty()) {

            // réécupère le country à partir de l'id
            Ville ville = villeService.getVille(Long.valueOf(villeString));

            // crée un objet member
            Joueur joueur = new Joueur(1, ville,password, prenom, nom, email);

            // enregistre le member en base de données
            joueurService.addJoueur(joueur);

            // ajoute le member dans la session
            session.setAttribute("joueur", joueur);

            resp.sendRedirect("/confirmation");
        }
        else {

            // envoie l'erreurs à la JSP
            req.setAttribute("erreurs", erreurs);

            // renvoie les informations déjà saisies
            req.setAttribute("nom", nom);
            req.setAttribute("prenom", prenom);
            req.setAttribute("email", email);

            this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(req, resp);
        }
    }
}
