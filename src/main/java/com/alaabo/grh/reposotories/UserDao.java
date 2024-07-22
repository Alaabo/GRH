package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    //TODO:must complete .env folder

}
