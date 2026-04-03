package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class GetFeedUseCase {
    private final UserRepositoryInterface userRepository;
    private final PostRepositoryInterface postRepository;

    private final Database database;

    public GetFeedUseCase(Database db, UserRepositoryInterface userRepository, PostRepositoryInterface postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        database = db;
    }

    public List<Post> execute(int userId, String toggleView) {

        List<@NonNull Post> allPosts = database.getAll(Post.class);
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }

        if (toggleView.equals("Recommendations")){
            allPosts = allPosts.stream()
                    .filter(p -> p.getParent() == null)
                    .sorted((a, b) -> b.getcreatedAt().compareTo(a.getcreatedAt()))
                    .toList();
        } else if (toggleView.equals("Subscriptions")){
            allPosts = allPosts.stream()
                    .filter(p -> p.getParent() == null)
                    .filter(u -> user.getSubscriptions().stream()
                    .anyMatch(sub -> sub.getLogin().equals(u.getOwner().getLogin())))
                    .toList();
        }

        return allPosts;
    }

}
