package edu.miu.waa_lab.service;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    List<Post> getUserPosts(Long userId);
}
