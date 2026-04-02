<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"  %>
<%@ page import="fr.miniaturesolid.domain.entity.Post"  %>
<%@ page import="fr.miniaturesolid.domain.entity.User"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature</title>
    <link rel="stylesheet" href="/css/miniature.css">
</head>
<body>
    <header>
        <h1>Miniature</h1>
        <p>Bienvenue <%= ((User) session.getAttribute("user")).getLogin() %></p>
        <form method="post" action="/details">
            <button name="action" value="logout" type="submit">Se déconnecter</button>
        </form>
    </header>
    <main>



        <% 
                Post post = (Post) request.getAttribute("post");
 
            %>        
                    <article>
                        <form method="post" action="/details/<%=post.getId() %>">
                            <h3><%= post.getOwner().getLogin() %></h3>
                        </form>
                        <p>le <%= post.getFormattedDate()%></p>
                        <p><%= post.getContent() %></p>
                        <form method="post" action="/details/<%=post.getId() %>">
                            <input type="hidden" name="postId" value="<%= post.getId() %>">
                            <button type="submit" name="like" value="<%= post.getId() %>"><%= post.getLikes() %>❤️</button>
                        </form>
            
                    </article>
            
              <% 
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                for (Post postComment : posts) {
                   
                %>        
                    <article>
                        <form method="post" action="/details/<%=post.getId() %>">
                            <h3><%= postComment.getOwner().getLogin() %></h3>
                        </form>
                        <p>le <%= postComment.getFormattedDate()%></p>
                        <p><%= postComment.getContent() %></p>
                        <form method="post" action="/details/<%=post.getId() %>">
                            <input type="hidden" name="postId" value="<%= post.getId() %>">
                            <button type="submit" name="like" value="<%= postComment.getId() %>"><%= postComment.getLikes() %>❤️</button>
                        </form>
                        
                    </article>
            <%    
                } 
            %>
            
        <article>
            <form method="post" action="/details/<%=post.getId() %>">
                <h2>Ecrire un commentaire</h2>
                <input type="hidden" name="postId" value="<%= post.getId() %>">
                <textarea rows="5" cols="33" name="postComment" placeholder="Ecrivez votre commentaire..."></textarea>
                <br>
                <button type="submit" name="newComment">Commenter</button>
            </form>
            <br>
            <a href="/feeds">Retour</a>
        </article>

        
        
        


    </main>

</body>
</html>