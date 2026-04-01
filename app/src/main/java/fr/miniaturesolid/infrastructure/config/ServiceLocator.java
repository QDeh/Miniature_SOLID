package fr.miniaturesolid.infrastructure.config;



import fr.miniaturesolid.application.decorator.BaseContentProcessor;
import fr.miniaturesolid.application.decorator.ContentProcessor;
import fr.miniaturesolid.application.decorator.EscapeHtmlContentDecorator;
import fr.miniaturesolid.application.usecase.*;
import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;
import fr.miniaturesolid.infrastructure.database.InMemoryDatabase;
import fr.miniaturesolid.infrastructure.repository.PostRepository;
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
    private final PostRepositoryInterface postRepository;
    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final GetFeedUseCase getFeedUseCase;
    private final GetPostDetailUseCase getPostDetailUseCase;
    private final LikeUseCase likeUseCase;
    private final SubscribeUseCase subscribeUseCase;
    private final PostUseCase postUseCase;
    private final CommentUseCase commentUseCase;


    private ServiceLocator() {
        database = new InMemoryDatabase();
        userRepository = new UserRepository(database);
        postRepository = new PostRepository(database);
        loginUseCase = new LoginUseCase(userRepository);
        registerUseCase = new RegisterUseCase(userRepository);
        getFeedUseCase = new GetFeedUseCase(database, userRepository, postRepository);
        getPostDetailUseCase = new GetPostDetailUseCase(postRepository, database);
        likeUseCase =  new LikeUseCase(postRepository, userRepository);
        subscribeUseCase = new SubscribeUseCase(userRepository);
        postUseCase = new PostUseCase(postRepository, userRepository, contentProcessor);
        commentUseCase = new CommentUseCase(postRepository, userRepository, contentProcessor);

        userRepository.save(new User("admin", "admin@admin.com", "admin"));
        userRepository.save(new User("suer", "suer@suer.com", "suer"));


    }

    public static synchronized ServiceLocator getInstance() {
        if (instance == null) {
            instance = new ServiceLocator();
        }
        return instance;
    }

    ContentProcessor contentProcessor = new EscapeHtmlContentDecorator(new BaseContentProcessor());


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
