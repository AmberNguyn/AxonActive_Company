package com.axonactive.company.repository;

import com.axonactive.company.entity.DepartmentLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentLocationRepository extends JpaRepository<DepartmentLocation, Integer> {
}
