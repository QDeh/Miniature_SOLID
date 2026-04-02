// package fr.miniaturesolid.presentation.controller;

// import java.io.IOException;
// import java.util.List;
// import java.util.stream.Collectors;

// import org.jspecify.annotations.NonNull;

// import fr.miniaturesolid.application.usecase.CommentUseCase;
// import fr.miniaturesolid.application.usecase.GetPostDetailUseCase;
// import fr.miniaturesolid.application.usecase.LikeUseCase;
// import fr.miniaturesolid.domain.entity.Post;
// import fr.miniaturesolid.domain.entity.User;
// import fr.miniaturesolid.infrastructure.config.ServiceLocator;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.annotation.WebServlet;

// import jakarta.servlet.http.HttpServlet;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

// @WebServlet("/details/*")
// public class DetailsController extends AuthenticatedController {

//     private GetPostDetailUseCase PostDetailUseCase;
//     private LikeUseCase likeUseCase;
//     private CommentUseCase postUseCase;
//     private List<Post> postsList;

    
//     @Override
//     public void init() throws ServletException {
//         super.init();
//         feedUseCase = ServiceLocator.getInstance().getGetFeedUseCase();
//         likeUseCase = ServiceLocator.getInstance().getLikeUseCase();
//         postUseCase = ServiceLocator.getInstance().getPostUseCase();
//         subscribeUseCase = ServiceLocator.getInstance().getSubscribeUseCase();

//     }

//     @Override
//     protected void handleGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
//         int id = Integer.parseInt(req.getPathInfo().substring(1));

//         Post post = posts.stream()
//             .filter(p -> p.getId() == id)
//             .findFirst().orElse(null);
//         if (post == null) {
//             resp.sendError(404, "Post introuvable");
//             return;
//         }

//         List<Post> comments = posts.stream()
//             .filter(p -> p.getParent() != null && p.getParent().equals(post))
//             .collect(Collectors.toList());

//         req.setAttribute("post", post);
//         req.setAttribute("posts", comments);
//         req.getRequestDispatcher("/WEB-INF/views/details.jsp").forward(req, resp);

//     }

//     @Override
//     protected void handlePost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        
//         HttpSession session = req.getSession(false);
//         User connectedUser = (User) session.getAttribute("user");
//         String action = req.getParameter("action");

//         String like = req.getParameter("like");
//         String postId = req.getParameter("postId");
//         String postComment = req.getParameter("postComment");
        
//         if ("logout".equals(action)) {
//             if (session != null) {
//                 session.invalidate();
//                 resp.sendRedirect("/login");
//             }
//             return;
//         }

//         for (Post post : posts) {
//         String id = String.valueOf(post.getId());
//         if (like != null && id.equals(like)) {
//             post.updateLikes(connectedUser);
//             resp.sendRedirect("/details/" + postId);
//             return;
//         }
//         if (postComment != null && !postComment.isEmpty()) {
//             if (id.equals(postId)) {
//                 Post newComment = new Post(connectedUser, postComment, post);
//                 posts.add(newComment);
//                 resp.sendRedirect("/details/" + id);
//                 return;
//             }
//         }
//         }
//     }

    
// }
