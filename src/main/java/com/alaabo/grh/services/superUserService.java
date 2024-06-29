package com.alaabo.grh.services;

import com.alaabo.grh.Model.superUser;
import com.alaabo.grh.reposotories.superUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class superUserService {
    @Autowired
    private superUserDAO SUD;

    public superUserService(superUserDAO SUD) {
        this.SUD = SUD;
    }

    @Autowired
    public Iterable<superUser> getSU(){
        return SUD.findAll();
    }
}
