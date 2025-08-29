package com.example.cadastropet.Exceptions;

import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.Repository.CadastroRepository;
import org.springframework.stereotype.Component;

@Component
public class HealthExceptionsCheck {
    public HealthExceptionsCheck(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    private final CadastroRepository cadastroRepository;


    public void checkExceptionsSave(HealthRecordsModel healthRecordsModel) {
        if (!cadastroRepository.existsById(healthRecordsModel.getPetId())) {
            throw new InvalidPetIDException("The pet ID does not exist.");
        }
        
        if (healthRecordsModel.getVisitDate() == null) {
            throw new IllegalArgumentException("The date can't be null.");
        }

        if (healthRecordsModel.getDiagnosis() == null || healthRecordsModel.getDiagnosis().trim().isEmpty() || !healthRecordsModel.getDiagnosis().matches("[a-zA-Z ]*")) {
            throw new IllegalArgumentException("The diagnosis can't be empty or have numbers or special characters.");
        }

        if (healthRecordsModel.getVeterinaryName() == null || healthRecordsModel.getVeterinaryName().trim().isEmpty() || !healthRecordsModel.getVeterinaryName().matches("[a-zA-Z ]*")) {
            throw new IllegalArgumentException("The veterinary name can't be empty or have numbers or special characters.");
        }

        if (healthRecordsModel.getVeterinaryClinic() == null || healthRecordsModel.getVeterinaryClinic().trim().isEmpty() || !healthRecordsModel.getVeterinaryClinic().matches("[a-zA-Z ]*")) {
            throw new IllegalArgumentException("The veterinary clinic can't be empty or have numbers or special characters.");
        }

        if (healthRecordsModel.getReasonForVisit() == null || healthRecordsModel.getReasonForVisit().trim().isEmpty() || !healthRecordsModel.getReasonForVisit().matches("[a-zA-Z\\d ]*")) {
            throw new IllegalArgumentException("The reason for visit can't be empty or have special characters.");
        }
    }
}
