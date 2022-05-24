package com.axonactive.company.repository;

import com.axonactive.company.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findDepartmentByDepartmentNameIs(String DepartmentName);

    List<Department> findDepartmentByStartDateIsNotNull();
}
