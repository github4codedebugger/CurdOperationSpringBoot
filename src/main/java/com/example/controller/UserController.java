package com.example.controller;

import com.example.model.User;
import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @PutMapping
    public User updateMobile(@RequestParam String uId, @RequestParam String mobile) {
        Optional<User> optUser = userRepo.findById(uId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setMobile(mobile);
            return userRepo.save(user);
        }
        throw new RuntimeException("UserId not exist!!");
    }

    @DeleteMapping
    public String deleteUser(@RequestParam String uId) {
        Optional<User> optUser = userRepo.findById(uId);
        if (optUser.isPresent()) {
            userRepo.delete(optUser.get());
            return "user deleted successfully!!";
        }
        throw new RuntimeException("UserId not exist!!");
    }
}
