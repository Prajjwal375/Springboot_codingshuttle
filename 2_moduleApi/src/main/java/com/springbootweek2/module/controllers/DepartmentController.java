package com.springbootweek2.module.controllers;

import com.springbootweek2.module.dto.DepartmentDTO;
import com.springbootweek2.module.exceptions.ResourceNotFoundException;
import com.springbootweek2.module.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/{departmentId}")
    public ResponseEntity<DepartmentDTO> getEmployeeById(@PathVariable(name = "departmentId") Long id) {
        DepartmentDTO departmentDTO = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentDTO);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createNewDepartment(@RequestBody @Valid DepartmentDTO inputDepartment) {
        DepartmentDTO savedDepartment = departmentService.createNewDepartment(inputDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> updateDepartmentById(@PathVariable Long departmentId, @RequestBody @Valid DepartmentDTO departmentDTO) {
        return ResponseEntity.ok(departmentService.updateDepartmentById(departmentId, departmentDTO));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long departmentId) {
        boolean deleted = departmentService.deleteDepartmentById(departmentId);
        if (deleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

}
