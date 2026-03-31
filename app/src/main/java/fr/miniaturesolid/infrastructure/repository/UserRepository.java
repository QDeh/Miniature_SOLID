package fr.miniaturesolid.infrastructure.repository;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

import java.util.List;
import java.util.Optional;

/**
 * Implémentation de UserRepositoryInterface utilisant une Database.
 * Permet de séparer la logique métier de la persistance des données.
 * Pour changer de base de données, il suffit de remplacer l'implémentation de Database.
 */
public class UserRepository implements UserRepositoryInterface {

    private final Database database;

    public UserRepository(Database database) {
        this.database = database;
    }

    @Override
    public Optional<User> findById(int id) {
        return database.findFirst(User.class, u -> u.getId() == id);
    }

    @Override
    public List<User> findAll() {
        return database.getAll(User.class);
    }

    @Override
    public void save(User user) {
        database.removeIf(User.class, u -> u.getId() == (user.getId()));
        database.add(user);
    }

    @Override
    public void delete(int id) {
        database.removeIf(User.class, u -> u.getId() == id);
    }
}
