package edu.miu.waa_lab.controller;

import edu.miu.waa_lab.entity.Post;
import edu.miu.waa_lab.entity.User;
import edu.miu.waa_lab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
