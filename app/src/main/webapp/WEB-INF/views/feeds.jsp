<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"  %>
<%@ page import="fr.miniaturesolid.domain.entities.Post"  %>
<%@ page import="fr.miniaturesolid.domain.entities.User"  %>

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
        <a href="/logout">Se déconnecter</a>
    </header>
    <main>
        <form method="get">
            <button name="action" value="recommendations" type="submit">Recommendations</button>
            <button name="action" value="subscriptions" type="submit">Abonnements</button>
        </form>

        <article>
            <form method="post" action="/feeds">
                <h2>Postez sur Miniature</h2>
                <textarea rows="5" cols="33" name="content" placeholder="Ecrivez votre minia..."></textarea>
                <br>
                <button type="submit" name="newPost">Postez votre minia</button>
            </form>
        </article>

        <% 
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                for (Post post : posts) {
                   
            %>        
                    <article>
                        <form method="post" action="/feeds">
                            <h3><%= post.getOwner().getLogin() %></h3>
                            <button type="submit" name="subscribe" value="<%= post.getId() %>"><%= ((User) session.getAttribute("user")).isSubscribed(post.getOwner()) ? "Suivi" : "Suivre" %></button>
                        </form>
                        
                        <p>le <%= post.getFormattedDate()%></p>
                        <p><%= post.getContent() %></p>
                        <form method="post" action="/feeds">
                            <button type="submit" name="like" value="<%= post.getId() %>"><%= post.getLikes() %>❤️</button>
                            <a href="/details/<%= post.getId() %>">💬</a>
                        </form>
            
                    </article>
            <%
                    
                } 
            %>

    </main>
</body>
</html>