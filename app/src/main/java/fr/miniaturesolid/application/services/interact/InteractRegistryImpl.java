package fr.miniaturesolid.application.services.interact;

import java.util.Map;

import fr.miniaturesolid.domain.entity.InteractionType;
import fr.miniaturesolid.domain.strategy.InteractStrategy;
import fr.miniaturesolid.infrastructure.registry.InteractRegistry;

public class InteractRegistryImpl implements InteractRegistry {
    private final Map<InteractionType, InteractStrategy> strategies;

    public InteractRegistryImpl(Map<InteractionType, InteractStrategy> strategies){
        this.strategies = strategies;
    }

    @Override
    public InteractStrategy getStrategy(InteractionType type){
        return strategies.get(type);
    }
}
