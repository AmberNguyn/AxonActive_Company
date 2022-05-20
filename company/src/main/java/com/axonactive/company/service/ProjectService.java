package com.axonactive.company.service;

import com.axonactive.company.entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    List<Project> getAll();

    //C - create
    public void saveProject(Project project);

    //R - retrieve
    public Optional<Project> findProjectById(Integer id);
    //U - update

    //D - delete
    public void deleteProject(Integer id);
}
