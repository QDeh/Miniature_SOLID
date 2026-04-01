package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import fr.miniaturesolid.application.usecase.GetFeedUseCase;
import org.jspecify.annotations.NonNull;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/feeds")
public class FeedController extends HttpServlet {

    private Database database;
    private List<@NonNull Post> posts = database.getAll(Post.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect("/login");
            return;
        }
        String action = req.getParameter("action");
        User user = (User) session.getAttribute("user");

        List<@NonNull Post> postsList = posts;
        if ("subscriptions".equals(action)) {
            postsList = posts.stream()
                    .filter(u -> user.getSubscriptions().stream()
                    .anyMatch(sub -> sub.getLogin().equals(u.getOwner().getLogin())))
                    .toList();
            // feedUseCase.execute(user.getId(), "Subscriptions")
        }

        postsList = postsList.stream()
                .filter(p -> p.getParent() == null)
                .sorted((a, b) -> b.getcreatedAt().compareTo(a.getcreatedAt()))
                .collect(Collectors.toList());
        // feedUseCase.execute(user.getId(), "Recommendations")

        req.setAttribute("posts", postsList);
        req.getRequestDispatcher("/WEB-INF/views/feeds.jsp").forward(req, resp);
    }

    protected void getSubscriptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        User connectedUser = (User) session.getAttribute("user");

        String content = req.getParameter("content");
        String subscribe = req.getParameter("subscribe");
        String like = req.getParameter("like");
        String comment = req.getParameter("comment");


        for (Post post : posts) {
            String id = String.valueOf(post.getId());

            if (id.equals(subscribe)) {
                User user = post.getOwner();
                connectedUser.updateSubscriptions(user);
                resp.sendRedirect("/feeds");
                return;
            }

            if (id.equals(like)) {
                post.updateLikes(connectedUser);
                resp.sendRedirect("/feeds");
                return;
            }

            if (id.equals(comment)) {
                resp.sendRedirect("/details/" + id);
                return;
            }

        }

        if (content != null && !content.isEmpty()) {
            Post post = new Post(connectedUser, content, null);
            posts.add(post);
            resp.sendRedirect("/feeds");
            return;
        } else {
            resp.sendRedirect("/feeds");
            return;
        }
    }
}
