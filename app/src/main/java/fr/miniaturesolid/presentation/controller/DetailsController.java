package fr.miniaturesolid.presentation.controller;

import java.io.IOException;
import java.util.List;


import fr.miniaturesolid.application.usecase.CommentUseCase;
import fr.miniaturesolid.application.usecase.GetPostDetailUseCase;
import fr.miniaturesolid.application.usecase.LikeUseCase;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.infrastructure.config.ServiceLocator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/details/*")
public class DetailsController extends AuthenticatedController {

    private GetPostDetailUseCase postDetailUseCase;
    private LikeUseCase likeUseCase;
    private CommentUseCase commentUseCase;

    
    @Override
    public void init() throws ServletException {
        super.init();
        likeUseCase = ServiceLocator.getInstance().getLikeUseCase();
        postDetailUseCase = ServiceLocator.getInstance().getGetPostDetailUseCase();
        commentUseCase = ServiceLocator.getInstance().getCommentUseCase();
    }

    @Override
    protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
        int id = Integer.parseInt(req.getPathInfo().substring(1));

        Post post = postDetailUseCase.showPost(id);
        if (post == null) {
            resp.sendError(404, "Post introuvable");
            return;
        }

        List<Post> comments = postDetailUseCase.showComments(post);

        req.setAttribute("post", post);
        req.setAttribute("posts", comments);
        req.getRequestDispatcher("/WEB-INF/views/details.jsp").forward(req, resp);

    }

    @Override
    protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
        String like = req.getParameter("like");
        String postId = req.getParameter("postId");
        String postComment = req.getParameter("postComment");
        String newComment = req.getParameter("newComment");

        Post post = postDetailUseCase.showPost(Integer.parseInt(postId));
        List<Post> comments = postDetailUseCase.showComments(post);

        if (postId.equals(like)) {
            likeUseCase.execute(user.getId(),Integer.parseInt(postId));
            resp.sendRedirect("/details/" + postId);
            return;
        }

        for (Post comment : comments) {
            String commentId = String.valueOf(comment.getId());

            if (commentId.equals(like)) {
                likeUseCase.execute(user.getId(),comment.getId());
                resp.sendRedirect("/details/" + postId);
                return;
            }
        }

        if (newComment != null && postComment != null && !postComment.isEmpty()) {
            commentUseCase.execute(user.getId(), postComment, post);
            resp.sendRedirect("/details/" + postId);
            return;
        }
        
    }
}
