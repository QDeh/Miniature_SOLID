package fr.miniaturesolid.domain.repository;

import fr.miniaturesolid.domain.entity.*;

import java.util.List;
import java.util.Optional;

/**
 * Interface pour accéder aux données des utilisateurs.
 * Permet de séparer la logique métier de la persistance des données.
 * Pour changer de base de données, il suffit de remplacer l'implémentation de UserRepositoryInterface.
 */
public interface PostRepositoryInterface {

    Optional<Post> findById(int id);

    List<Post> findAll();

    void save(Post post);

    void delete(int id);
}