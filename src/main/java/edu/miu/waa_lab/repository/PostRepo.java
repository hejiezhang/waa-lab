package edu.miu.waa_lab.repository;

import edu.miu.waa_lab.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAll();

    Optional<Post> findById(long id);

    Post save(Post post);

    void deleteById(long id);

    List<Post> findByAuthor(String author);

    List<Post> findByAuthorContaining(String text);

    // Query to find posts with a given title
    List<Post> findByTitle(String title);

//    @Query("SELECT p FROM Post p WHERE p.title = :title")
//    List<Post> findPostsByTitle(@Param("title") String title);

}
