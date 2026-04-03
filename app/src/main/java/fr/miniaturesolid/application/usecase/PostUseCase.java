package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;

public class PostUseCase {
    private final PostRepositoryInterface postRepository;
    private final UserRepositoryInterface userRepository;

    public PostUseCase(PostRepositoryInterface postRepository, UserRepositoryInterface userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post execute(int userId, String content){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null || content == null || content.isEmpty()) return null;

        Post post = new Post(user,content,null);
        postRepository.save(post);
        return post;
    }

}
