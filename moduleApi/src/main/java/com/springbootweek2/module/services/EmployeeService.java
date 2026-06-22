package com.springbootweek2.module.services;

import com.springbootweek2.module.dto.EmployeeDTO;
import com.springbootweek2.module.entities.EmployeeEntity;
import com.springbootweek2.module.exceptions.ResourceNotFoundException;
import com.springbootweek2.module.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = new ModelMapper();
    }

   public EmployeeDTO getEmployeeById(Long employeeId) {
       EmployeeEntity employeeEntity = employeeRepository
               .findById(employeeId)
               .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
       return modelMapper.map(employeeEntity, EmployeeDTO.class);
   }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class); // dto to entity
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity); // save entity in repo
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class); // entity to dto

    }


    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        boolean exists = isExists(employeeId);
        if (!exists) throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExists(Long employeeId) {
        boolean exists = isExists(employeeId);
        if (!exists) throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Long employeeId) {
        boolean exists = isExists(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        boolean exists  = isExists(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: " + employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            if (fieldToBeUpdated == null) {
                return;
            }
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);

        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
