package org.laLiga.cuerpoMedico.application;

import org.laLiga.cuerpoMedico.infraestructure.MedicalStaffRepository;
import org.laLiga.cuerpoMedico.domain.entities.CuerpoMedico;

import java.util.List;
import java.util.Optional;

public class MedicalStaffService {
    private MedicalStaffRepository medicalStaffRepository;

    public MedicalStaffService(MedicalStaffRepository medicalStaffRepository) {
        this.medicalStaffRepository = medicalStaffRepository;
    }


    public void createMedicalStaff(CuerpoMedico cuerpoMedico){
        this.medicalStaffRepository.save(cuerpoMedico);
    }

    public void updateMedicalStaff(CuerpoMedico cuerpoMedico){
        this.medicalStaffRepository.update(cuerpoMedico);
    }

    public Optional<CuerpoMedico> getMedicalStaffById(int id){
        return this.medicalStaffRepository.findById(id);
    }

    public void deleteMedicalStaff(int id){
        this.medicalStaffRepository.delete(id);
    }

    public List<CuerpoMedico> getAllMedicalStaff(){
        return this.medicalStaffRepository.findAll();
    }
}
