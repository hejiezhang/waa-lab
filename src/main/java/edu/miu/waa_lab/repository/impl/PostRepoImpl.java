package edu.miu.waa_lab.repository.impl;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.repository.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepoImpl implements PostRepo {

    private static List<Post> posts;
    private long idCounter = 200;

    static {
        posts = new ArrayList<Post>();
        Post post1 = new Post(101L, "AAA", "Hello", "Tom");
        Post post2 = new Post(102L, "BBB", "How are you", "John");
        Post post3 = new Post(103L, "CCC", "Good bye", "Jack");
        Post post4 = new Post(104L, "DDD", "What's up", "Tom");
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Optional<Post> findById(long id) {
        return posts.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public Post save(Post post) {
        if(post.getId() == 0) {
            // Creating new post
            post.setId(idCounter++);
            posts.add(post);
        } else {
            // Updating existing post: remove the old version and add the updated one
            posts.removeIf(p -> p.getId() == post.getId());
            posts.add(post);
        }
        return post;
    }

    @Override
    public void deleteById(long id) {
        posts.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Post> findByAuthor(String author) {
        List<Post> result = new ArrayList<>();
        for(Post post : posts) {
            if(post.getAuthor().equals(author)) {
                result.add(post);
            }
        }
        return result;
    }

    @Override
    public List<Post> findByAuthorContaining(String text) {
        List<Post> result = new ArrayList<>();
        for(Post post : posts) {
            if(post.getAuthor() != null && post.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                result.add(post);
            }
        }
        return result;
    }

}
