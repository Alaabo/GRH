package com.alaabo.grh.Controllers;


import com.alaabo.grh.Model.User;
import com.alaabo.grh.reposotories.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable int id) {
        return userDao.findById(id).orElseThrow(() -> new RuntimeException("User not Found or doesn't exist"));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        if (!userDao.existsById(user.getId())) {
            userDao.save(user);
        } else {
            throw new RuntimeException("User already exists");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        if (userDao.existsById(id)) {
            user.setId(id); // Ensure the ID is set correctly
            userDao.save(user);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
        } else {
            throw new RuntimeException("User does not exist");
        }
    }
}
