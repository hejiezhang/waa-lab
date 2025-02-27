package edu.miu.waa_lab.service.impl;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.User;
import edu.miu.waa_lab.repository.UserRepo;
import edu.miu.waa_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repo;

    @Override
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repo.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return repo.save(user);
    }

    @Override
    public List<Post> getUserPosts(Long userId) {
        User user = repo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPosts();
    }
}
