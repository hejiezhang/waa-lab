package edu.miu.waa_lab.service.impl;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.User;
import edu.miu.waa_lab.repository.UserRepo;
import edu.miu.waa_lab.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public List<Post> getUserPosts(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPosts();
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepo.existsById(id)) {
            throw new EntityNotFoundException("user with id " + id + " not found.");
        }
        userRepo.deleteById(id);
    }

    @Override
    public List<User> findUsersByPostTitle(String title) {
        return userRepo.findUsersByPostTitle(title);
    }

    @Override
    public List<User> findUsersWithMoreThanNPosts(int n) {
        return userRepo.findUsersWithMoreThanNPosts(n);
    }


}
