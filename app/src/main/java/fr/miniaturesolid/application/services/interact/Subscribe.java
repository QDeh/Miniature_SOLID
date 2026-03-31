package fr.miniaturesolid.application.services.interact;

import fr.miniaturesolid.domain.entity.*;
import fr.miniaturesolid.domain.strategy.InteractStrategy;

public class Subscribe implements InteractStrategy{
    
    @Override
    public void interact(User user, Entity entity) {
        if (!(entity instanceof User)) {
            throw new IllegalArgumentException();
        }
        User followedUser = (User) entity;
        if (!user.getSubscriptions().contains(user)) {
            user.subscribe(followedUser);
        } else {
            user.unsubscribe(followedUser);
        }
    }
}
