package com.springbootweek2.module.services;


import com.springbootweek2.module.dto.DepartmentDTO;
import com.springbootweek2.module.entities.DepartmentEntity;
import com.springbootweek2.module.exceptions.ResourceNotFoundException;
import com.springbootweek2.module.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper =  modelMapper;
    }

    public DepartmentDTO getDepartmentById(Long departmentId) {
        DepartmentEntity departmentEntity = departmentRepository
                .findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with id: " + departmentId));

        return modelMapper.map(departmentEntity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();

        return departmentEntities.stream()
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createNewDepartment(DepartmentDTO inputDepartment) {
        DepartmentEntity toSaveEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(toSaveEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long departmentId, DepartmentDTO departmentDTO) {
        isExists(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDTO, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity savedDepartmentEntity = departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartmentEntity, DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(Long departmentId) {
        isExists(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public void isExists(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);

        if (!exists) {
            throw new ResourceNotFoundException(
                    "Department not found with id: " + departmentId);
        }
    }
}
