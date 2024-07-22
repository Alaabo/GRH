package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.SuperUser;
import com.alaabo.grh.reposotories.SuperUserDAO;
import com.alaabo.grh.services.Encryptor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/superusers/")
public class SuperUserController {

    private final SuperUserDAO SUR;

    public SuperUserController(SuperUserDAO SUR) {
        this.SUR = SUR;
    }

    private SuperUser _temp(SuperUser superuser) throws Exception {
        SuperUser temp = new SuperUser();
        temp.setUsername(superuser.getUsername());
        temp.setPassword(Encryptor.encrypt(superuser.getPassword()));
        temp.setService(superuser.getService());
        return temp;
    }

    @GetMapping("/getAll")

    public @ResponseBody Iterable<SuperUser> getUsers() {

        return SUR.findAll();
    }
    @GetMapping("/{id}")

    public @ResponseBody Optional<SuperUser> getUser(@PathVariable int id) {

        return SUR.findById(id);
    }
    @PostMapping("/regNew")
    public void authenticate(@RequestBody SuperUser superuser){
        try{
            SUR.save(_temp(superuser));
        }catch(Exception e){
            throw new RuntimeException("Error while registering new superuser", e);
        }
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable int id){
        try{
            SUR.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Error while deleting password", e);
        }
    }
    @PostMapping("/update")
    public void update (@RequestBody SuperUser superuser) throws Exception {
       if(SUR.existsById(superuser.getId())){
           SUR.save(_temp(superuser));
       }else{
           throw new RuntimeException("No Such S.User Exist");
       }
    }

}
