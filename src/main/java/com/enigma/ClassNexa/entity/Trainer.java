package com.enigma.ClassNexa.entity;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "m_trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String address;
    private String phone_number;
    private String gender;
    @ManyToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential user_credential;
}
=======
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "m_trainer")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String gender;

    @OneToOne
    @JoinColumn(name = "user_credential_id", referencedColumnName = "id")
    private UserCredential userCredential;

}
>>>>>>> master
