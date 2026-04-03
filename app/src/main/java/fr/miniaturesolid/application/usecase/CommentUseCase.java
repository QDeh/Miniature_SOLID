package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.application.decorator.ContentProcessor;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.entity.User;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;
import fr.miniaturesolid.domain.repository.UserRepositoryInterface;


public class CommentUseCase {
    private final PostRepositoryInterface postRepository;
    private final UserRepositoryInterface userRepository;
    private final ContentProcessor contentProcessor;


    public CommentUseCase(PostRepositoryInterface postRepository, UserRepositoryInterface userRepository,  ContentProcessor contentProcessor) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.contentProcessor = contentProcessor;
    }

    public void execute(int userId, String content, Post postParent){
        User user = userRepository.findById(userId).orElse(null);
        if (user == null || content == null || content.isEmpty()) return;

        Post comment = new Post(user,content, postParent);
        postRepository.save(comment);

    }

}
