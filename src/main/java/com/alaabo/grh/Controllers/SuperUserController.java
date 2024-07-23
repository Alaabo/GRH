package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.SuperUser;
import com.alaabo.grh.reposotories.SuperUserDAO;
import com.alaabo.grh.services.Encryptor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superusers")
public class SuperUserController {

    private final SuperUserDAO superUserDAO;

    public SuperUserController(SuperUserDAO superUserDAO) {
        this.superUserDAO = superUserDAO;
    }

    private SuperUser encryptPassword(SuperUser superUser) throws Exception {
        SuperUser temp = new SuperUser();
        temp.setUsername(superUser.getUsername());
        temp.setPassword(Encryptor.encrypt(superUser.getPassword()));
        temp.setService(superUser.getService());
        return temp;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SuperUser> getAllUsers() {
        return superUserDAO.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuperUser getUser(@PathVariable int id) {
        return superUserDAO.findById(id).orElseThrow(() -> new RuntimeException("SuperUser not Found or doesn't exist"));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody SuperUser superUser) {
        try {
            superUserDAO.save(encryptPassword(superUser));
        } catch (Exception e) {
            throw new RuntimeException("Error while registering new superuser", e);
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable int id, @RequestBody SuperUser superUser) {
        if (superUserDAO.existsById(id)) {
            try {
                superUser.setId(id);
                superUserDAO.save(encryptPassword(superUser));
            } catch (Exception e) {
                throw new RuntimeException("Error while updating superuser", e);
            }
        } else {
            throw new RuntimeException("SuperUser does not exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        try {
            superUserDAO.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting superuser", e);
        }
    }
}
