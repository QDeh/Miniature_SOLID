package fr.miniaturesolid.application.usecase;

import fr.miniaturesolid.domain.database.Database;
import fr.miniaturesolid.domain.entity.Post;
import fr.miniaturesolid.domain.repository.PostRepositoryInterface;

import java.util.List;

public class GetPostDetailUseCase {

    private final PostRepositoryInterface postRepository;


    public GetPostDetailUseCase(PostRepositoryInterface postRepository, Database database) {
        this.postRepository = postRepository;
    }

    public Post showPost(int postId) {
        return postRepository.findAll().stream()
                .filter(p -> p.getId() == postId)
                .findFirst().orElse(null);
    }

    public List<Post> showComments(Post post) {
        return postRepository.findAll().stream()
                .filter(p -> p.getParent() != null && p.getParent().equals(post))
                .toList();
    }

}


