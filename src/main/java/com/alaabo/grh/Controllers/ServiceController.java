package com.alaabo.grh.Controllers;

import com.alaabo.grh.Model.Service;
import com.alaabo.grh.reposotories.ServiceDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {
    private final ServiceDAO serviceDAO;
    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    public ServiceController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Service> getAllServices() {
        logger.info("Fetching all services");
        List<Service> services = serviceDAO.findAll();
        logger.debug("Number of services found: {}", services.size());
        return services;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Service getService(@PathVariable int id) {
        logger.info("Fetching service with ID: {}", id);
        return serviceDAO.findById(id)
                .orElseThrow(() -> {
                    logger.error("Service not found with ID: {}", id);
                    return new RuntimeException("Service not Found or doesn't exist");
                });
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addService(@RequestBody Service service) {
        logger.info("Adding new service with ID: {}", service.getId());
        if (!serviceDAO.existsById(service.getId())) {
            serviceDAO.save(service);
            logger.info("Service added successfully with ID: {}", service.getId());
        } else {
            logger.warn("Service already exists with ID: {}", service.getId());
            throw new RuntimeException("Service already exists");
        }
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateService(@PathVariable int id, @RequestBody Service service) {
        logger.info("Updating service with ID: {}", id);
        if (serviceDAO.existsById(id)) {
            service.setId(id); // Ensure the ID is set correctly
            serviceDAO.save(service);
            logger.info("Service updated successfully with ID: {}", id);
        } else {
            logger.error("Service doesn't exist with ID: {}", id);
            throw new RuntimeException("Service doesn't exist");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteService(@PathVariable int id) {
        logger.info("Deleting service with ID: {}", id);
        if (serviceDAO.existsById(id)) {
            serviceDAO.deleteById(id);
            logger.info("Service deleted successfully with ID: {}", id);
        } else {
            logger.error("Service doesn't exist with ID: {}", id);
            throw new RuntimeException("Service doesn't exist");
        }
    }
}
