package com.axonactive.company.repository;

import com.axonactive.company.entity.Relatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives, Integer> {
}
