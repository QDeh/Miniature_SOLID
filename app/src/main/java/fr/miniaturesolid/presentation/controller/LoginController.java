package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.Session;
import org.jspecify.annotations.NonNull;

import fr.miniaturesolid.application.usecase.LoginUseCase;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.infrastructure.config.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login" )
public class LoginController extends HttpServlet {

    private LoginUseCase loginUseCase;

    @Override
    public void init() throws ServletException {
        super.init();
        this.loginUseCase = ServiceLocator.getInstance().getLoginUseCase();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String error = loginUseCase.validate(login, password);
        if (error == null) {
            User user = ServiceLocator.getInstance().getUserRepository()
                    .findByLogin(login).orElseThrow();
            HttpSession session = req.getSession(true);
            session.setAttribute("user", user);
            resp.sendRedirect("/feeds");
        } else {
            System.out.println(error);
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
 
    }

}
