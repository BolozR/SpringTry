package ru.springtry.models;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "owner")
@Entity
@Table(name = "car_table")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}
