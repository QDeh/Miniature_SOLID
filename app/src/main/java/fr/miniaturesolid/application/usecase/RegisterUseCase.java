package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

import java.util.Optional;

public class RegisterUseCase {

    private final UserRepositoryInterface userRepository;

    public RegisterUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    private String validate(String login, String email, String password, String confirmPassword) {
        Optional<User> userOpt = userRepository.findByLogin(login);
        if (login.isBlank())
            return "Username vide";
        if (!password.equals(confirmPassword))
            return "Les mots de passe ne correspondent pas";
        if (userOpt.isPresent())
            return "Username déjà pris";
        return null;
    }
}
