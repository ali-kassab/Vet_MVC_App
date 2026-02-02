package com.example.vet_mvc_app.clinics.service;

import com.example.vet_mvc_app.authentication.JWT.JwtService;
import com.example.vet_mvc_app.clinics.Repository.ClinicRepository;
import com.example.vet_mvc_app.clinics.dto.CreateClinicDto;
import com.example.vet_mvc_app.clinics.dto.ResponseClinicDto;
import com.example.vet_mvc_app.clinics.dto.UpdateClinicDto;
import com.example.vet_mvc_app.clinics.enitity.Clinic;
import com.example.vet_mvc_app.users.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {
    private final ClinicRepository clinicRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public ClinicServiceImpl(ClinicRepository clinicRepository, JwtService jwtService, UserRepository userRepository) {
        this.clinicRepository = clinicRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    public Clinic createClinic(CreateClinicDto dto) {
        Clinic clinic = new Clinic();
        clinic.setName(dto.getName());
        clinic.setAddress(dto.getAddress());
        clinic.setPhone(dto.getPhone());
        clinic.setVet(dto.getVetId());
        return clinicRepository.save(clinic);
    }

    public Page<ResponseClinicDto> getAll(Pageable pageable) {
        Page<Clinic> clinics = clinicRepository.findAll(pageable);
        return clinics.map(clinic -> {
            ResponseClinicDto dto = new ResponseClinicDto();
            dto.setId(clinic.getId());
            dto.setName(clinic.getName());
            dto.setAddress(clinic.getAddress());
            dto.setPhone(clinic.getPhone());
            dto.setVetName(clinic.getVet().getName());
            return dto;
        });
    }

    public Clinic updateClinic(UpdateClinicDto dto, Long id) {
        Boolean isAdmin = jwtService.isUserAdminOrOwner(dto.getVetId());

        if (!isAdmin) {
            throw new RuntimeException("not authorized");
        }

        Clinic clinic = clinicRepository.findById(id).orElseThrow(() ->
                new RuntimeException("clinic not found")
        );
        if (dto.getName() != null) {
            clinic.setName(dto.getName());
        }
        if (dto.getAddress() != null) clinic.setAddress(dto.getAddress());
        if (dto.getPhone() != null) clinic.setPhone(dto.getPhone());
        if (dto.getVetId() != null) {
            clinic.setVet(userRepository.findById(dto.getVetId()).orElseThrow(() ->
                    new RuntimeException("vet not found")
            ));

        }
        return clinic;
    }

}
