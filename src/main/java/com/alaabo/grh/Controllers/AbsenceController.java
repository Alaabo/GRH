package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.Absence;
import com.alaabo.grh.reposotories.AbsenceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/absence")
public class AbsenceController {
    private final AbsenceDAO AD;
    private static final Logger logger = LoggerFactory.getLogger(AbsenceController.class);

    public AbsenceController(AbsenceDAO ad) {
        this.AD = ad;
    }

    @GetMapping("/getAll")
    public List<Absence> getAll() {
        logger.info("Fetching all absences");
        List<Absence> absences = AD.findAll();
        logger.debug("Number of absences found: {}", absences.size());
        return absences;
    }

    @GetMapping("/{id}")
    public Absence getById(@PathVariable int id) {
        logger.info("Fetching absence with ID: {}", id);
        return AD.findById(id)
                .orElseThrow(() -> {
                    logger.error("Absence not found with ID: {}", id);
                    return new RuntimeException("Absence not found or doesn't exist");
                });
    }

    @PostMapping("/newService")
    public void newService(@RequestBody Absence absence) {
        logger.info("Adding new absence with ID: {}", absence.getId());
        if (!AD.existsById(absence.getId())) {
            AD.save(absence);
            logger.info("Absence added successfully with ID: {}", absence.getId());
        } else {
            logger.warn("Absence already exists with ID: {}", absence.getId());
            throw new RuntimeException("Absence already exists");
        }
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody Absence absence) {
        logger.info("Updating absence with ID: {}", id);
        if (AD.existsById(id)) {
            absence.setId(id);
            AD.save(absence);
            logger.info("Absence updated successfully with ID: {}", id);
        } else {
            logger.error("Absence not found with ID: {}", id);
            throw new RuntimeException("Absence not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        logger.info("Deleting absence with ID: {}", id);
        if (AD.existsById(id)) {
            AD.deleteById(id);
            logger.info("Absence deleted successfully with ID: {}", id);
        } else {
            logger.error("Absence doesn't exist with ID: {}", id);
            throw new RuntimeException("Absence doesn't exist");
        }
    }
}
