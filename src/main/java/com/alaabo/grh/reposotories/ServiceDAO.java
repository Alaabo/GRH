package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDAO extends JpaRepository<Service, Integer> {
}
