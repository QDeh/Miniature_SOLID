<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <link rel="stylesheet" href="/css/miniature.css">
</head>
<body class="auth-page">
    <a href="/index.jsp"><h1>MINIATURE</h1></a>
    
    <main>
        <h2>Inscription</h2>
        <form method="post" >
            <input type="text" name="login" placeholder="Nom d'utilisateur">
            <input type="email" name="email" placeholder="Adresse mail">
            <input type="password" name="password" placeholder="Mot de passe">
            <input type="password" name="confirm" placeholder="Confirmer le mot de passe">
            <input type="submit" value="S'inscrire">
        </form>
        <a href="/"><input type="submit" value="Retour à l'acceuil"></a>

    </main>


</body>
</html>