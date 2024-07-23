package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.Absence;
import com.alaabo.grh.reposotories.AbsenceDAO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/absence")
public class AbsenceController {
    private final AbsenceDAO AD;

    public AbsenceController(AbsenceDAO ad) {
        AD = ad;
    }

    @GetMapping("/getAll")
    public List<Absence> getAll() {
        return AD.findAll();
    }

    @GetMapping("/{id}")
    public Absence getById(@PathVariable int id) {
        return AD.findById(id).orElseThrow(() -> new RuntimeException("Service not Found or doesn't exist"));
    }

    @PostMapping("/newService")
    public void newService(@RequestBody Absence absence) {
        if (!AD.existsById(absence.getId())) {
            AD.save(absence);
        } else {
            throw new RuntimeException("Service already exists");
        }
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable int id, @RequestBody Absence absence) {
        if (AD.existsById(id)) {
            AD.save(absence);
        } else {
            throw new RuntimeException("Service already exists");
        }
    }

    @DeleteMapping("/dalete/{id}")
    public void delete(@PathVariable int id) {
        if (!AD.existsById(id)) {
            throw new RuntimeException("Absence doesn't exist");
        }
        AD.deleteById(id);
    }
}
