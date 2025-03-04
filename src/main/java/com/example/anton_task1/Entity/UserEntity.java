package com.example.anton_task1.Entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String username;
  private String password;

  private String authorities;

  private String email;
  private String phone;

  /* Предположим человек удаляется, но собака же никуда не денется, она может найти другого хозяина, по этому каскадное удаление недопустимо */
  @OneToOne(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
      fetch = FetchType.EAGER,
      orphanRemoval = false)
  private DogEntity dog;

  @OneToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},
      fetch = FetchType.LAZY,
      orphanRemoval = false)
  private List<CarEntity> cars;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "users_projects",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "project_id"))
  private List<ProjectEntity> projects;

  @ManyToMany(
      cascade = {CascadeType.PERSIST, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JoinTable(
      name = "users_courses",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<CourseEntity> courses;

  @Override
  public String toString() {
    return "UserEntity{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + Arrays.stream(password.split("")).map(s -> s = "*")
        + '\''
        + ", authorities='"
        + authorities
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", dog="
        + dog
        + '}';
  }
}
