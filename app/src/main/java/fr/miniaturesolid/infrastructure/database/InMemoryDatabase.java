package fr.miniaturesolid.infrastructure.database;

import fr.miniaturesolid.domain.database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/** 
 * Implémentation de Database qui stocke les données en mémoire.
 */
public class InMemoryDatabase implements Database {

    private final List<Object> store = new ArrayList<>();

    @Override
    public void add(Object item) {
        store.add(item);
    }

    @Override
    public boolean remove(Object item) {
        return store.remove(item);
    }

    @Override
    public boolean removeIf(Predicate<Object> predicate) {
        return store.removeIf(predicate);
    }

    @Override
    public Optional<Object> findFirst(Predicate<Object> predicate) {
        return store.stream().filter(predicate).findFirst();
    }

    @Override
    public List<Object> findAll(Predicate<Object> predicate) {
        return store.stream().filter(predicate).toList();
    }

    @Override
    public List<Object> getAll() {
        return Collections.unmodifiableList(store);
    }

    @Override
    public int size() {
        return store.size();
    }

    @Override
    public void clear() {
        store.clear();
    }
}