package com.example.db.Entity;

import com.example.db.Enum.ColorEnum;
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
