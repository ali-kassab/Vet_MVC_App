package com.example.vet_mvc_app.clinics.Repository;

import com.example.vet_mvc_app.clinics.enitity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
    Optional<Clinic> findById(Long aLong);
}
