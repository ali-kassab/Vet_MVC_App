package com.example.vet_mvc_app.users.entity;

import com.example.vet_mvc_app.clinics.enitity.Clinic;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "vetmvc")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public enum Role {
        user,
        vet,
        admin
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.user; // ✅ DEFAULT HERE

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}