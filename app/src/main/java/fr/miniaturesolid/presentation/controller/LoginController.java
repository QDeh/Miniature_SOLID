package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;

import org.jspecify.annotations.NonNull;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login" )
public class LoginController extends HttpServlet {

    private Database database;
    private List<@NonNull User> users = database.getAll(User.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");

        String error = validate(login, email, password, confirmPassword);
        if (error != null) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            return;
        } else {
            User newUser = new User(login, email, password);
            users.add(newUser);
            resp.sendRedirect("/login");
        }
 
    }

    private String validate(String login, String email, String password, String confirmPassword) {
        if (login.isBlank())
            return "Username vide";
        if (!password.equals(confirmPassword))
            return "Les mots de passe ne correspondent pas";
        if (users.stream().anyMatch(u -> u.getLogin().equals(login)))
            return "Username déjà pris";
        return null;
    }

}
