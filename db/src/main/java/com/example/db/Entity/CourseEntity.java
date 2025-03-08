package com.example.db.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CourseEntity {
  @Id @GeneratedValue private Long id;

  private String name;
  private String description;
}
