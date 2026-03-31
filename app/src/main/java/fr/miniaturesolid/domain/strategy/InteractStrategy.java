package fr.miniaturesolid.domain.strategy;

import fr.miniaturesolid.domain.entity.Entity;
import fr.miniaturesolid.domain.entity.User;

public interface InteractStrategy {
    public void interact(User user, Entity entity);
}
