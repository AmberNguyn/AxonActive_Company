package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Department;
import com.axonactive.company.repository.DepartmentRepository;
import com.axonactive.company.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    //getAll
    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    //C - create
    @Override
    public void saveDepartment(Department department) {
        departmentRepository.save(department);
    }

    //R - retrieve
    @Override
    public Optional<Department> findDepartmentById(Integer id) {
        return departmentRepository.findById(id);
    }


    //U - update

    //D - delete
    @Override
    public void deleteDepartment(Integer id) {
        departmentRepository.deleteById(id);
    }

}
