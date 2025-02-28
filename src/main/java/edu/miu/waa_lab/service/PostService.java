package edu.miu.waa_lab.service;

import edu.miu.waa_lab.entity.Comment;
import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(long id);
    PostDto createPost(PostDto postDto);
    PostDto updatePost(long id, PostDto postDto);
    boolean deletePost(long id);
    List<PostDto> getPostsByAuthor(String author);
    List<PostDto> getPostsByAuthorContaining(String text);

    Comment addCommentToPost(Long postId, Comment comment);
    List<Post> findByTitle(String title);
}
