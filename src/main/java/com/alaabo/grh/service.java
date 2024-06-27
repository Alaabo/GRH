package com.alaabo.grh;

import com.alaabo.grh.Controller.userController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class service {
    @GetMapping("/user")
    public String getUser() {
        userController uc = new userController();
        return uc.createUser("this is the first integration");
    }
}
