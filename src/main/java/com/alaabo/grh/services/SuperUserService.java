package com.alaabo.grh.services;

import com.alaabo.grh.Model.SuperUser;
import com.alaabo.grh.reposotories.SuperUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperUserService {
    @Autowired
    private SuperUserDAO SUD;

    public SuperUserService(SuperUserDAO SUD) {
        this.SUD = SUD;
    }

    @Autowired
    public Iterable<SuperUser> getSU(){
        return SUD.findAll();
    }

//    public Boolean auth(SuperUser superuser) {
//        Encryptor enc = new Encryptor();
//        Optional<SuperUser> s = SUD.findById(superuser.getId());
//        if(s.isPresent()){
//            SuperUser User = s.get();
//            if(enc.crypt(User.getPassword() , superuser.getPassword())){
//                return true;
//            }else return false;
//        }else return false;
//
//
//    }


}
