package com.example.db.Repository;

import com.example.db.Entity.CourseEntity;
import com.example.db.Entity.UserEntity;
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
