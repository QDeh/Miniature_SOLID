package fr.miniaturesolid.application.services.interact;

import fr.miniaturesolid.domain.entity.*;
import fr.miniaturesolid.domain.strategy.InteractStrategy;

public class Like implements InteractStrategy {

    @Override
    public void interact(User user, Entity entity) {
        if (!(entity instanceof Post)) {
            throw new IllegalArgumentException();
        }
        Post post = (Post) entity;
        if (!post.getLikedBy().contains(user)) {
            post.addLike(user);
        } else {
            post.removeLike(user);
        }
    }
}
