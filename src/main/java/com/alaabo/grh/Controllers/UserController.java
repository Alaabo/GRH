package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.User;
import com.alaabo.grh.reposotories.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserDao userDao;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userDao.findAll();
        logger.debug("Number of users found: {}", users.size());
        return users;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable int id) {
        logger.info("Fetching user with ID: {}", id);
        return userDao.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new RuntimeException("User not Found or doesn't exist");
                });
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        logger.info("Creating new user with ID: {}", user.getId());
        if (!userDao.existsById(user.getId())) {
            userDao.save(user);
            logger.info("User created successfully with ID: {}", user.getId());
        } else {
            logger.warn("User already exists with ID: {}", user.getId());
            throw new RuntimeException("User already exists");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable int id, @RequestBody User user) {
        logger.info("Updating user with ID: {}", id);
        if (userDao.existsById(id)) {
            user.setId(id); // Ensure the ID is set correctly
            userDao.save(user);
            logger.info("User updated successfully with ID: {}", id);
        } else {
            logger.error("User does not exist with ID: {}", id);
            throw new RuntimeException("User does not exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        logger.info("Deleting user with ID: {}", id);
        if (userDao.existsById(id)) {
            userDao.deleteById(id);
            logger.info("User deleted successfully with ID: {}", id);
        } else {
            logger.error("User does not exist with ID: {}", id);
            throw new RuntimeException("User does not exist");
        }
    }
}
