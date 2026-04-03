# Miniature_S.O.L.I.D.

## Tables des Matières

[[#Description]]
[[#Pré-requis]]
[[#Installation et lancement]]
[[#Structure du projet]]

## Description

Miniature est un mini réseau social en architecture MVC qui donne de la place aux petites choses, il permet d'intéragir avec tous les autres Miniaturists (nous sommes déjà 2 !)

Partagez, likez, suivez, commentez.

## Pré-requis

- Installer gradle en version 9.0.4 ou supérieure
- Installer JDK 21 ou supérieur

## Installation et lancement

Pour installer miniature, veuillez cloner le repository :

```bash
git clone https://github.com/QDeh/Miniature_SOLID
```

Une fois cette étape réalisée, ouvrez un terminal dans le dossier "Miniature_SOLID" crée puis lancez la commande

```bash
./gradlew run
```

## Structure du projet

```
app
|──src/main
|       |──java/fr/miniaturesolid
|       |              |──application
|       |              |    |
|       |              |    |──decorator # decorteurs pour le contenu des posts 
|       |              |    |    |──BaseContentProcessor.java
|       |              |    |    |──ContentProcessor.java 
|       |              |    |    |──ContentProcessorDecorator.java 
|       |              |    |    |──EscapeHtmlContentDecorator.java 
|       |              |    |──usecase
|       |              |    |    |──CommentUseCase.java #logique de commentaire
|       |              |    |    |──GetFeedUseCase.java #logique de feed
|       |              |    |    |──GetPostDetailUseCase.java #logique de détail
|       |              |    |    |──LikeUseCase.java #logique de like
|       |              |    |    |──LoginUseCase.java #logique de connexion
|       |              |    |    |──PostUseCase.java #logique de créeation de post
|       |              |    |    |──RegisterUseCase.java #logique d'inscription
|       |              |    |    |──SubscribeUseCase.java #logique d'abonnement
|       |              |──domain
|       |              |    |
|       |              |    |──database
|       |              |    |    |──Database.java # base de données
|       |              |    |──entity
|       |              |    |    |──Post.java #classe Post
|       |              |    |    |──User.java #classe User
|       |              |    |──repository
|       |              |    |    |──PostRepositoryInterface.java
|       |              |    |    |──UserRepositoryInterface.java
|       |              |──infrastructure
|       |              |    |
|       |              |    |──config
|       |              |    |    |──ServiceLocator.java # singleton instanciant nos classes
|       |              |    |──database
|       |              |    |    |──InMemoryDatabase.java #implémentation de Database
|       |              |    |──repository
|       |              |    |    |──PostRepository.java #implémentation de l'interface posts
|       |              |    |    |──UserRepository.java #implémentation de l'interface users
|       |              |──presentation
|       |              |    |
|       |              |    |──controller #servlets
|       |              |    |    |──AuthenticatedController.java
|       |              |    |    |──DetailsController.java
|       |              |    |    |──FeedController.java
|       |              |    |    |──LoginController.java
|       |              |    |    |──LogoutController.java
|       |              |    |    |──RegisterController.java
|       |              |──App.java #serveur de l'application (dossiers, port...)
|       |──webapp
|       |    |──css
|       |    |   |──miniature.css #fichier css du projet
|       |    |   |
|       |    |──images
|       |    |   |──miniature-banner.png #l'image dont nous avons besoin
|       |    |   |
|       |    |──WEB-INF/views
|       |    |   |──details.jsp #page pour afficher les détails d'un post
|       |    |   |──feeds.jsp #page pour afficher les feeds (découverte & abo)
|       |    |   |──login.jsp #page de connexion
|       |    |   |──register.jsp #page d'inscription
|       |    |   |
|       |    |──index.html #page d'accueil du site
build.gradle.kts #gére l'installation des dépendances (tomcat et java 21)    
```
