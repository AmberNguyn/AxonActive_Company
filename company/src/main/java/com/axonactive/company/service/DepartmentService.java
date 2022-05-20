package com.axonactive.company.service;

import com.axonactive.company.entity.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {
    List<Department> getAll();

    //C - create
    public void saveDepartment(Department department);

    //R - retrieve
    public Optional<Department> findDepartmentById(Integer id);


    //U - update


    //D - delete
    public void deleteDepartment(Integer id);
}
