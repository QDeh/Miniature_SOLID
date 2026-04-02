package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;

import fr.miniaturesolid.application.usecase.*;


import fr.miniaturesolid.domain.entity.*;
import fr.miniaturesolid.infrastructure.config.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/feeds")
public class FeedController extends AuthenticatedController {


    private GetFeedUseCase feedUseCase;
    private LikeUseCase likeUseCase;
    private PostUseCase postUseCase;
    private SubscribeUseCase subscribeUseCase;
    private List<Post> postsList;

    
    @Override
    public void init() throws ServletException {
        super.init();
        feedUseCase = ServiceLocator.getInstance().getGetFeedUseCase();
        likeUseCase = ServiceLocator.getInstance().getLikeUseCase();
        postUseCase = ServiceLocator.getInstance().getPostUseCase();
        subscribeUseCase = ServiceLocator.getInstance().getSubscribeUseCase();

    }



    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

        String action = req.getParameter("action");
        
        if ("subscriptions".equals(action)) {
            postsList = feedUseCase.execute(user.getId(), "Subscriptions");
        }else {
            postsList = feedUseCase.execute(user.getId(), "Recommendations");
        }

        req.setAttribute("posts", postsList);
        req.getRequestDispatcher("/WEB-INF/views/feeds.jsp").forward(req, resp);
    }

    protected void getSubscriptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {

        String content = req.getParameter("content");
        String subscribe = req.getParameter("subscribe");
        String like = req.getParameter("like");
        String comment = req.getParameter("comment");


        for (Post post : postsList) {
            String id = String.valueOf(post.getId());

            if (id.equals(subscribe)) {
                User owner = post.getOwner();
                subscribeUseCase.execute(user.getId(), owner.getId());
                resp.sendRedirect("/feeds");
                return;
            }

            if (id.equals(like)) {
                likeUseCase.execute(user.getId(), post.getId());
                resp.sendRedirect("/feeds");
                return;
            }

            if (id.equals(comment)) {
                resp.sendRedirect("/details/" + id);
                return;
            }

        }

        if (content != null && !content.isEmpty()) {
            postUseCase.execute(user.getId(), content);
            resp.sendRedirect("/feeds");
            return;
        } else {
            resp.sendRedirect("/feeds");
            return;
        }
    }
}
