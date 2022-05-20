package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Assignment;
import com.axonactive.company.repository.AssignmentRepository;
import com.axonactive.company.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;

    @Override
    public List<Assignment> getAll()
    {
        return assignmentRepository.findAll();
    }

    //C - create
    @Override
    public void saveAssignment(Assignment assignment)
    {
        assignmentRepository.save(assignment);
    }

    //R - retrieve
    @Override
    public Optional<Assignment> findAssignmentById(Integer id)
    {
        return assignmentRepository.findById(id);
    }
    //U - update

    //D - delete
    @Override
    public void deleteAssignment(Integer id)
    {
        assignmentRepository.deleteById(id);
    }
}
