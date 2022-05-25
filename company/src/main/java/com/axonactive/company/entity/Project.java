package com.axonactive.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Project {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer projectId;


    @Size(min = 0, max= 100)
    private String area;


    @Size(min = 0, max = 100)
    private String projectName;

    @ManyToOne
    @JoinColumn(name = "managed_department")
    private Department department;
}
