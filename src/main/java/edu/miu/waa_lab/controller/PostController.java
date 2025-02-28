package edu.miu.waa_lab.controller;

import edu.miu.waa_lab.entity.Comment;
import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.dto.PostDto;
import edu.miu.waa_lab.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping()  // localhost:8080/posts
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")  // localhost:8080/posts/100
    public PostDto getPostById(@PathVariable("id") long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto postDto){
        return postService.createPost(postDto);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
        return postService.updatePost(id, postDto);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable("id") long id){
        boolean deleted = postService.deletePost(id);
        return deleted ? "Post with id " + id + " deleted successfully" : "Post not found";
    }

    // GET -- localhost:8080/posts/filter?author=xxx OR localhost:8080/posts/filter?authorContains=xxx
    @GetMapping("/filter")
    public List<PostDto> filterPosts(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "authorContains", required = false) String authorContains) {
        if(author != null) {
            return postService.getPostsByAuthor(author);
        } else if(authorContains != null) {
            return postService.getPostsByAuthorContaining(authorContains);
        } else {
            return postService.getAllPosts();
        }
    }

    // localhost:8080/posts/101/comments
    @PostMapping("/{postId}/comments")
    public Comment addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) {
        return postService.addCommentToPost(postId, comment);
    }

    // Find posts by title
    //GET /api/posts/search?title={title}
    @GetMapping("/search")
    public List<Post> findPostsByTitle(@RequestParam String title) {
        return postService.findByTitle(title);
    }

}
