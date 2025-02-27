package com.xefi.tpjavaee.servlets;

import com.xefi.tpjavaee.pojos.Question;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "confirmationServlet", urlPatterns = { "/confirmation" })
public class PartieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // récupère la session
        HttpSession session = req.getSession();

        // récupère le member de la session
        Question member = (Question) session.getAttribute("member");

        // envoie le member à la JSP
        req.setAttribute("member", member);

        this.getServletContext().getRequestDispatcher("/WEB-INF/confirmation.jsp").forward(req, resp);
    }
}
