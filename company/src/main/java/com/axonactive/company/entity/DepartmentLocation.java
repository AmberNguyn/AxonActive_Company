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
@Data
@Builder
public class DepartmentLocation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer locationId;

    @NotNull
    @Size(min = 1, max = 100)
    private String location;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
