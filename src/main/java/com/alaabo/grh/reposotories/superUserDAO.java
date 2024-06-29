package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.superUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface superUserDAO extends JpaRepository<superUser , Integer> {
}
