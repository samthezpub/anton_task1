package com.example.anton_task1.Entity;

import com.example.anton_task1.Enum.ColorEnum;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ColorEnum color;
}
