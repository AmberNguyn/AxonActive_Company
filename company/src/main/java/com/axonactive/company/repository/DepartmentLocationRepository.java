package com.axonactive.company.repository;

import com.axonactive.company.entity.DepartmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Integer> {

    List<DepartmentLocation> findDepartmentLocationByDepartmentDepartmentId(Integer deptId);

    List<DepartmentLocation> findDepartmentLocationByLocation(String location);

    List<DepartmentLocation> findDepartmentLocationByLocationStartingWith(String text);

    List<DepartmentLocation> findDepartmentLocationByLocationNot(String text);

    List<DepartmentLocation> findDepartmentLocationByLocationOrderByDepartmentDepartmentIdDesc(String location);

}
