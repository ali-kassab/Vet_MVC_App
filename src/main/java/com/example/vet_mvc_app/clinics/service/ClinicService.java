package com.example.vet_mvc_app.clinics.service;

import com.example.vet_mvc_app.clinics.dto.CreateClinicDto;
import com.example.vet_mvc_app.clinics.dto.ResponseClinicDto;
import com.example.vet_mvc_app.clinics.dto.UpdateClinicDto;
import com.example.vet_mvc_app.clinics.enitity.Clinic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClinicService {
    public Clinic createClinic(CreateClinicDto dto);

    public Page<ResponseClinicDto> getAll(Pageable pageable);

    public Clinic updateClinic(UpdateClinicDto dto,Long id);

}
