package fr.miniaturesolid.infrastructure.repository;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation de UserRepositoryInterface utilisant une Database.
 * Permet de séparer la logique métier de la persistance des données.
 * Pour changer de base de données, il suffit de remplacer l'implémentation de Database.
 */
public class PostRepository implements PostRepositoryInterface {

    private final Database database;

    public PostRepository(Database database) {
        this.database = database;
    }

    @Override
    public Optional<Post> findById(int id) {
        return database.findFirst(Post.class, u -> u.getId() == id);
    }

    @Override
    public List<Post> findAll() {
        return database.getAll(Post.class);
    }

    @Override
    public void save(Post post) {
        database.removeIf(Post.class, u -> u.getId() == (post.getId()));
        database.add(post);
    }

    @Override
    public void delete(int id) {
        database.removeIf(Post.class, u -> u.getId() == id);
    }
}
