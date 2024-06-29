package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface userDao extends JpaRepository<user , Integer> {
    //TODO:must complete .env folder

}
