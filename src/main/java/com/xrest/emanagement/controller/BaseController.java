package com.xrest.emanagement.controller;

import com.xrest.emanagement.dto.RestDto;
import com.xrest.emanagement.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class BaseController<E,P> {

    private final BaseService<E,P> baseService;


    public BaseController(BaseService<E, P> baseService) {
        this.baseService = baseService;
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody  E obj) {
        return new RestDto().successModel(baseService.save(obj));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new RestDto().successModel(baseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") P id) {
        return new RestDto().successModel(baseService.findOne(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") P id) {
        try {
            baseService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new RestDto().successModel("Deleted");
    }
    @PostMapping("/saveAll")
    public ResponseEntity<?> saveAll(@RequestBody List<E> obj) {
        return new RestDto().successModel(baseService.saveAll(obj));
    }
}
