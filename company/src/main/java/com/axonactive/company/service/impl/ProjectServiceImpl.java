package com.axonactive.company.service.impl;

import com.axonactive.company.entity.Project;
import com.axonactive.company.repository.ProjectRepository;
import com.axonactive.company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Override
    public List<Project> getAll()
    {
        return projectRepository.findAll();
    }

    //C - create
    @Override
    public void saveProject(Project project)
    {
        projectRepository.save(project);
    }

    //R - retrieve
    @Override
    public Optional<Project> findProjectById(Integer id)
    {
        return projectRepository.findById(id);
    }

    @Override
    public List<Project> findProjectByAreaOrderByProjectName(String area)
    {
        return projectRepository.findProjectByAreaOrderByProjectName(area);
    }

    @Override
    public Optional<Project> findProjectByProjectName(String projectName)
    {
        return projectRepository.findProjectByProjectName(projectName);
    }

    @Override
    public List<Project> findProjectByDepartmentDepartmentId(Integer departmentId)
    {
        return projectRepository.findProjectByDepartmentDepartmentId(departmentId);
    }

    @Override
    public List<Project> findProjectByDepartmentDepartmentName(String departmentName)
    {
        return projectRepository.findProjectByDepartmentDepartmentName(departmentName);
    }

//    List<Project> findProjectByAreaContaining(String area);




    //U - update

    //D - delete
    public void deleteProject(Integer id){
        projectRepository.deleteById(id);
    }
}
