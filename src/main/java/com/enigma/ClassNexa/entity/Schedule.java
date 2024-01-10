package com.enigma.ClassNexa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_schedule")
@Entity
@Builder
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "meeting_link")
    private String meetingLink;

    @Column(name = "start_class")
    private Timestamp startClass;

    @Column(name = "end_class")
    private Timestamp endClass;

    @OneToMany
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    private Classes Class;
}
