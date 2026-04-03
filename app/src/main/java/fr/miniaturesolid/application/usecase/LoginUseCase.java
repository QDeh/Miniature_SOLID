package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

public class LoginUseCase {
    private final UserRepositoryInterface userRepository;

    public LoginUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public String validate(String login, String password) {
        User user = userRepository.findByLogin(login).orElse(null);
        if (user.getLogin().isEmpty() || !user.getPassword().equals(password)){
            return "Erreur d'authentification";
        }
        return null;
    }
}
