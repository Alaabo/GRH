package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserDAO extends JpaRepository<SuperUser, Integer> {
}
