package edu.miu.waa_lab.service.impl;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.dto.PostDto;
import edu.miu.waa_lab.repository.PostRepo;
import edu.miu.waa_lab.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private ModelMapper modelMapper;

//    public List<Post> getAllPosts() {
//        return postRepo.findAll();
//    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepo.findAll().stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Optional<Post> post = postRepo.findById(id);
        return post.map(p -> modelMapper.map(p, PostDto.class)).orElse(null);
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);
        // Set id to 0 to trigger creation
        post.setId(0);
        Post savedPost = postRepo.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Optional<Post> existingPostOpt = postRepo.findById(id);
        if(existingPostOpt.isPresent()){
            Post existingPost = existingPostOpt.get();
            // Update fields
            existingPost.setTitle(postDto.getTitle());
            existingPost.setContent(postDto.getContent());
            existingPost.setAuthor(postDto.getAuthor());
            Post updatedPost = postRepo.save(existingPost);
            return modelMapper.map(updatedPost, PostDto.class);
        } else {
            return null; // Alternatively, throw an exception if not found.
        }
    }

    @Override
    public boolean deletePost(long id) {
        Optional<Post> existingPost = postRepo.findById(id);
        if(existingPost.isPresent()){
            postRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        return postRepo.findByAuthor(author).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByAuthorContaining(String text) {
        return postRepo.findByAuthorContaining(text).stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

}
