package com.axonactive.company.api;

import com.axonactive.company.entity.Project;
import com.axonactive.company.exception.ResourceNotFoundException;
import com.axonactive.company.repository.ProjectRepository;
import com.axonactive.company.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectResource {
    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    @GetMapping("/list")
    public List<Project> getAllProjects() {
        List<Project> projects = projectService.getAll();
        return projects;
    }

    //C - create
    @PostMapping("/add")
    public void addProject(@RequestBody Project project) {
        projectService.saveProject(project);
    }

    //R - retrieve
    @GetMapping("/get/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Project project = projectService.findProjectById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found: " + id)
                );
        return ResponseEntity.ok().body(project);
    }
    //U - update
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project newProject, @PathVariable(value = "id") Integer id) throws ResourceNotFoundException
    {
        Project project = projectService.findProjectById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Id not found" + id)
                );
        project.setProjectName(newProject.getProjectName());
        project.setDepartment(newProject.getDepartment());
        project.setArea(newProject.getArea());
        Project updatedProject = projectRepository.save(project);
        return ResponseEntity.ok(updatedProject);

    }

    //D - delete
    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteProjectById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Project project = projectService.findProjectById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found: " + id)
                );
        projectService.deleteProject(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return response;
    }
}
