package com.example.vet_mvc_app.clinics.enitity;

import com.example.vet_mvc_app.appointment.entity.Appointment;
import com.example.vet_mvc_app.users.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clinic", schema = "vetmvc")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 255)
    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Size(max = 11)
    @NotNull
    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vet_id", nullable = false)
    private User vet;

    @OneToMany(mappedBy = "clinic")
    private Set<Appointment> appointments = new LinkedHashSet<>();

}