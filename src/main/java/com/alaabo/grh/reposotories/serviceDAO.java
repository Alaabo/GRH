package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface serviceDAO extends JpaRepository<service, Integer> {
}
