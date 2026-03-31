<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="/css/miniature.css">
</head>
<body class="auth-page">
    <h1>Miniature</h1>
    <main>
        <h2>Connexion</h2>
        <form method="post" action="/login">
            
            <input type="text" name="login" placeholder="Nom d'utilisateur">
            <input type="password" name="password" placeholder="Mot de passe">
            <input type="submit" value="Se connecter">
            
        </form>
        <a href="/"><input type="submit" value="Retour à l'acceuil"></a>
    </main>
</body>
</html>