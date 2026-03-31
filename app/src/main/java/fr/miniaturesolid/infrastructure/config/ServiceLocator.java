package fr.miniaturesolid.infrastructure.config;

//import fr.simplon.application.usecase.GetGreetingUseCase;
//import fr.simplon.application.usecase.LoginUseCase;
//import fr.simplon.application.usecase.RegisterUseCase;
import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;
import fr.miniaturesolid.infrastructure.database.InMemoryDatabase;
import fr.miniaturesolid.infrastructure.repository.UserRepository;

/**
 * Point central d'initialisation et d'injection des dépendances.
 * Singleton qui crée et fournit les instances pour toute l'application.
 */
public class ServiceLocator {

    private static ServiceLocator instance;

    private final Database database;

    public Database getDatabase() {
        return database;
    }

    private final UserRepositoryInterface userRepository;
    // private final LoginUseCase loginUseCase;
    // private final RegisterUseCase registerUseCase;
    // private final GetGreetingUseCase getGreetingUseCase;

    private ServiceLocator() {
        this.database = new InMemoryDatabase();
        this.userRepository = new UserRepository(database);

        this.userRepository.save(new User("admin", "admin@admin.com", "admin"));
        this.userRepository.save(new User("suer", "suer@suer.com", "suer"));

        // this.loginUseCase = new LoginUseCase(userRepository);
        // this.registerUseCase = new RegisterUseCase(userRepository);
        // this.getGreetingUseCase = new GetGreetingUseCase();
    }

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    /*
     * public LoginUseCase getLoginUseCase() {
     * return loginUseCase;
     * }
     * 
     * public RegisterUseCase getRegisterUseCase() {
     * return registerUseCase;
     * }
     * 
     * public GetGreetingUseCase getGetGreetingUseCase() {
     * return getGreetingUseCase;
     * }
     */
}
