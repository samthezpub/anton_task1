package com.example.anton_task1.Repository;

import com.example.anton_task1.Entity.CourseEntity;
import com.example.anton_task1.Entity.UserEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByUsername(String username);

  @Query("SELECT u.courses FROM UserEntity u WHERE u.id = :userId")
  Optional<List<CourseEntity>> findAllCoursesByUserId(@Param("userId") Long userId);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
