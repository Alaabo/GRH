package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.SuperUser;
import com.alaabo.grh.reposotories.SuperUserDAO;
import com.alaabo.grh.services.Encryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/superusers")
public class SuperUserController {

    private final SuperUserDAO superUserDAO;
    private static final Logger logger = LoggerFactory.getLogger(SuperUserController.class);


    public SuperUserController(SuperUserDAO superUserDAO) {
        this.superUserDAO = superUserDAO;
    }

    private SuperUser encryptPassword(SuperUser superUser) throws Exception {
        logger.debug("Encrypting password for user: {}", superUser.getUsername());
        SuperUser temp = new SuperUser();
        temp.setUsername(superUser.getUsername());
        temp.setPassword(Encryptor.encrypt(superUser.getPassword()));
        temp.setService(superUser.getService());
        return temp;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<SuperUser> getAllUsers() {
        logger.info("Fetching all superusers");
        return superUserDAO.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuperUser getUser(@PathVariable int id) {
        logger.info("Fetching superuser with ID: {}", id);
        return superUserDAO.findById(id)
                .orElseThrow(() -> {
                    logger.error("SuperUser not found with ID: {}", id);
                    return new RuntimeException("SuperUser not Found or doesn't exist");
                });
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody SuperUser superUser) {
        try {
            logger.info("Creating new superuser: {}", superUser.getUsername());
            superUserDAO.save(encryptPassword(superUser));
            logger.info("Superuser created successfully");
        } catch (Exception e) {
            logger.error("Error while registering new superuser", e);
            throw new RuntimeException("Error while registering new superuser", e);
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@PathVariable int id, @RequestBody SuperUser superUser) {
        logger.info("Updating superuser with ID: {}", id);
        if (superUserDAO.existsById(id)) {
            try {
                superUser.setId(id);
                superUserDAO.save(encryptPassword(superUser));
                logger.info("Superuser updated successfully");
            } catch (Exception e) {
                logger.error("Error while updating superuser", e);
                throw new RuntimeException("Error while updating superuser", e);
            }
        } else {
            logger.warn("SuperUser does not exist with ID: {}", id);
            throw new RuntimeException("SuperUser does not exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        logger.info("Deleting superuser with ID: {}", id);
        try {
            superUserDAO.deleteById(id);
            logger.info("Superuser deleted successfully");
        } catch (Exception e) {
            logger.error("Error while deleting superuser", e);
            throw new RuntimeException("Error while deleting superuser", e);
        }
    }
}
