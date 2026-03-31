package fr.miniaturesolid.domain.database;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Interface commune pour toute base de données.
 * Stocke des Object — les méthodes typées évitent les casts manuels.
 * Permet de swapper InMemoryDatabase par PostgresDatabase, etc.
 */
public interface Database {

    void add(Object item);

    boolean remove(Object item);

    boolean removeIf(Predicate<Object> predicate);

    Optional<Object> findFirst(Predicate<Object> predicate);

    List<Object> findAll(Predicate<Object> predicate);

    List<Object> getAll();

    int size();

    void clear();

    // ── Méthodes typées (génériques sur la méthode, pas sur la classe) ──

    /**
     * Recherche le premier élément du type donné correspondant au prédicat.
     * Usage : database.findFirst(User.class, u -> u.getUsername().equals("admin"))
     */
    default <T> Optional<T> findFirst(Class<T> type, Predicate<T> predicate) {
        return getAll().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .filter(predicate)
                .findFirst();
    }

    /**
     * Retourne tous les éléments du type donné correspondant au prédicat.
     */
    default <T> List<T> findAll(Class<T> type, Predicate<T> predicate) {
        return getAll().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .filter(predicate)
                .toList();
    }

    /**
     * Retourne tous les éléments du type donné.
     * Usage : database.getAll(User.class)
     */
    default <T> List<T> getAll(Class<T> type) {
        return getAll().stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }

    /**
     * Supprime les éléments du type donné correspondant au prédicat.
     */
    default <T> boolean removeIf(Class<T> type, Predicate<T> predicate) {
        return removeIf(obj -> type.isInstance(obj) && predicate.test(type.cast(obj)));
    }
}
