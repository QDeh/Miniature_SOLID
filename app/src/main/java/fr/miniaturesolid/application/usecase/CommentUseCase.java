package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;


public class CommentUseCase {
    private final PostRepositoryInterface postRepository;
    private final UserRepositoryInterface userRepository;


    public CommentUseCase(PostRepositoryInterface postRepository, UserRepositoryInterface userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void execute(int userId, String content, Post postParent){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null || content == null || content.isEmpty()) return;

        Post comment = new Post(user,content, postParent);
        postRepository.save(comment);

    }

}
