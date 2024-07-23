package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.Service;
import com.alaabo.grh.reposotories.ServiceDAO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {
    private final ServiceDAO serviceDAO;

    public ServiceController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Service> getAllServices() {
        return serviceDAO.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service getService(@PathVariable int id) {
        return serviceDAO.findById(id).orElseThrow(() -> new RuntimeException("Service not Found or doesn't exist"));
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addService(@RequestBody Service service) {
        if (!serviceDAO.existsById(service.getId())) {
            serviceDAO.save(service);
        } else {
            throw new RuntimeException("Service already exists");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateService(@PathVariable int id, @RequestBody Service service) {
        if (serviceDAO.existsById(id)) {
            service.setId(id); // Ensure the ID is set correctly
            serviceDAO.save(service);
        } else {
            throw new RuntimeException("Service doesn't exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable int id) {
        if (serviceDAO.existsById(id)) {
            serviceDAO.deleteById(id);
        } else {
            throw new RuntimeException("Service doesn't exist");
        }
    }
}
