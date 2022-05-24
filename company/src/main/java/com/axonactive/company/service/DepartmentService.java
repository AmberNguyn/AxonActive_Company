package com.axonactive.company.service;

import com.axonactive.company.entity.Department;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAll();

    //C - create
    public void saveDepartment(Department department);

    //R - retrieve
    public Optional<Department> findDepartmentById(Integer id);

    public List<Department> findDepartmentByDepartmentNameIs(String DepartmentName);

    public List<Department> findDepartmentByStartDateIsNotNull();


    //U - update


    //D - delete
    public void deleteDepartment(Integer id);
}
