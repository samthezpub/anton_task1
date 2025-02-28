package com.example.anton_task1.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ProjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
}
