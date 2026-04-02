package fr.miniaturesolid.application.usecase;


import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

import java.util.Optional;

public class SubscribeUseCase {

    private final UserRepositoryInterface userRepository;

    public SubscribeUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }


    public void execute(int userId, int targetUserId) {
        if (userId == targetUserId) return;

        Optional<User> userOpt = userRepository.findById(userId);
        User targetUserOpt = userRepository.findById(targetUserId).orElse(null);

        if (targetUserOpt == null) return;

        userOpt.ifPresent(user -> {
            if (!user.getSubscriptions().contains(targetUserOpt)) {
                user.subscribe(targetUserOpt);
            } else {
                user.unsubscribe(targetUserOpt);
            }
        });
    }
}
