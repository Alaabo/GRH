package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.superUser;
import com.alaabo.grh.services.superUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/superusers/")
public class superUserController {
    private final superUserService s;

    public superUserController(superUserService s) {
        this.s = s;
    }

    @GetMapping("/getAll")

    public @ResponseBody Iterable<superUser> getUser() {

        return s.getSU();
    }

}
