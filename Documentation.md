
# Documentation

Nous avons pris un peu de temps pour réfléchir à la conception du projet, voici les diagrammes de cas d'utilisation et de classes.

## Diagramme de cas d'utilisation
![use-case-diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/QDeh/Miniature/main/diagramme_cas_utilisation.iuml)

## Diagramme de classes

```mermaid
classDiagram

Post --o Post : parent (1)
User --o User : abonné à (*)
Post --o User : est possédé par (1)
Post --o User : est liké par (*) 
class User{
-int nbUsers
+int id
+String login
+String email
+String password
+isSubscribed (User user) boolean
+updateSubscriptions(User user)
}

class Post{
-int nbPosts
+int id
+String content
+Date createdAt
-String formattedDate
+hashCode() int
+equals() boolean
+getLikes() int
+updateLikes(User user)
}
```
