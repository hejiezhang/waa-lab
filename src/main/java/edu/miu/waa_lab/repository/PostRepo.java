package edu.miu.waa_lab.repository;

import edu.miu.waa_lab.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepo {
    List<Post> findAll();

    Optional<Post> findById(long id);

    Post save(Post post);

    void deleteById(long id);

    List<Post> findByAuthor(String author);

    List<Post> findByAuthorContaining(String text);

}
