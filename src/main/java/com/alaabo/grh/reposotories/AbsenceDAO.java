package com.alaabo.grh.reposotories;

import com.alaabo.grh.Model.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceDAO extends JpaRepository<Absence, Integer> {
}
