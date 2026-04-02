package fr.miniaturesolid.presentation.controller;



import java.io.IOException;

import fr.miniaturesolid.domain.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public abstract class AuthenticatedController extends HttpServlet{
    
    private User getUserFromSession(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if (session == null ) {
            return null;
        }
        User user = (User) session.getAttribute("user");
        return user;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getUserFromSession(req);
        if (user == null){
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        handleGet(req, resp, user);
    }

    @Override
    protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = getUserFromSession(req);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        handlePost(req, resp, user);
    }

    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/feeds");
    }

    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
