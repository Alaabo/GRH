package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface absenceDAO extends JpaRepository<absence, Integer> {
}
