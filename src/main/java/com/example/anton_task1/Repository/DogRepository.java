package com.example.anton_task1.Repository;

import com.example.anton_task1.Entity.DogEntity;
import com.example.anton_task1.Entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DogRepository extends JpaRepository<DogEntity, Long> {
  @Query("SELECT u FROM UserEntity u WHERE u.dog.id = :id")
  Optional<UserEntity> findUserByDogEntityId(@Param("id") Long id);
}
