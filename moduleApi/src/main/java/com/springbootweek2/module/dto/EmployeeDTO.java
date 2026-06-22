package com.springbootweek2.module.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springbootweek2.module.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @Email(message = "email should be valid")
    private String email;

    @Max(value = 80)
    @Min(value = 17)
    private Integer age;

    @NotBlank(message = "role cant be blank")
//  @Pattern(regexp = "^(ADMIN|USER)$", message = "role can be USER and ADMIN only")
    @EmployeeRoleValidation
    private String role;

    @PastOrPresent(message = "date cant be of future")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;


    public EmployeeDTO() {

    }

    public EmployeeDTO(Long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.role = "USER";
        this.dateOfJoining = dateOfJoining;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
