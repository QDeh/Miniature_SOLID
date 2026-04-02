package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

import fr.miniaturesolid.application.usecase.LoginUseCase;
import fr.miniaturesolid.application.usecase.RegisterUseCase;
import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.infrastructure.config.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterController extends HttpServlet  {

    private RegisterUseCase registerUseCase;

    @Override
    public void init() throws ServletException {
        super.init();
        this.registerUseCase = ServiceLocator.getInstance().getRegisterUseCase();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirm");

        String error = registerUseCase.validate(login, email, password, confirmPassword);
        if (error == null) {
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("error", error);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
