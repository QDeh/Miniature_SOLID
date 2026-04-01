package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.Entity;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

import java.util.Optional;

public class LikeUseCase {

    private final PostRepositoryInterface postRepository;
    private final UserRepositoryInterface userRepository;

    public LikeUseCase(PostRepositoryInterface postRepository, UserRepositoryInterface userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void execute(int userId, int postId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return;
        }

        Optional<Post> postOpt = postRepository.findById(postId);
        postOpt.ifPresent(post -> {

            if (!post.getLikedBy().contains(user)) {
                post.addLike(user);
            } else {
                post.removeLike(user);
            }
        });

    }
    
}
