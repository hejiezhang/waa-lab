package edu.miu.waa_lab.controller;

import edu.miu.waa_lab.aspect.annotation.ExecutionTime;
import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.User;
import edu.miu.waa_lab.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()  // localhost:8080/users
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ExecutionTime
    @GetMapping("/{id}")  // localhost:8080/users/100
    public ResponseEntity<User> getUserById(@PathVariable("id") long id){
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Long id) {
        return userService.getUserPosts(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("delete successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get users with more than 'n' posts
    //GET /api/users/with-more-than/{n}/posts
    @GetMapping("/with-more-than/{n}/posts")
    public List<User> getUsersWithMoreThanNPosts(@PathVariable int n) {
        return userService.findUsersWithMoreThanNPosts(n);
    }

    // Get users who made posts with a given title
    //GET /api/users/by-post-title?title={}
    @GetMapping("/by-post-title")
    public List<User> getUsersByPostTitle(@RequestParam String title) {
        return userService.findUsersByPostTitle(title);
    }
}
