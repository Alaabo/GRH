package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.User;
import com.alaabo.grh.reposotories.UserDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserDao UD;

    public UserController(UserDao UD) {
        this.UD = UD;
    }

    @GetMapping("/GetAll")
    public @ResponseBody List<User> getAllUsers() {
        return UD.findAll();
    }
    @GetMapping("/{id}")
    public @ResponseBody Optional<User> getUser(@PathVariable int id) {
        return UD.findById(id);
    }
    @PostMapping("/NewUser")
    public void createUser(@RequestBody User user) {
        if (!UD.existsById(user.getId())) {
            UD.save(user);
        }else {
            throw new RuntimeException("User already exists");
        }


    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        if (UD.existsById(id)) {
            UD.deleteById(id);
        }else {
            throw new RuntimeException("User does not exist");
        }
    }
    @PostMapping("/update")
    public void updateUser(@RequestBody User user) {
        if (UD.existsById(user.getId())) {
            UD.save(user);
        }else{
            throw new RuntimeException("User does not exist");
        }
    }
}
