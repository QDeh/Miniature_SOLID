package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

import java.util.Optional;

public class LoginUseCase {
    private final UserRepositoryInterface userRepository;

    public LoginUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    private String validate(String login, String email, String password, String confirmPassword) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (userOpt.isEmpty() || !password.equals(confirmPassword)){
            return "Erreur d'authentification";
        }
        return null;
    }
}
