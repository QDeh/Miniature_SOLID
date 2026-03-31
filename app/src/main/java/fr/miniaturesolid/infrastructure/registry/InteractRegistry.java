package fr.miniaturesolid.infrastructure.registry;

import fr.miniaturesolid.domain.entity.InteractionType;
import fr.miniaturesolid.domain.strategy.InteractStrategy;

public interface InteractRegistry {
    InteractStrategy getStrategy(InteractionType type);
}
