package com.axonactive.company.repository;

import com.axonactive.company.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findProjectByAreaOrderByProjectName(String area);

    Optional<Project> findProjectByProjectName(String projectName);

    List<Project> findProjectByDepartmentDepartmentId(Integer departmentId);

    List<Project> findProjectByDepartmentDepartmentName(String departmentName);

//    List<Project> findProjectByAreaContaining(String area);




}
