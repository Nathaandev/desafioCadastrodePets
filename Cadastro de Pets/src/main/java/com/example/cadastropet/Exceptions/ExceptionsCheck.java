package com.example.cadastropet.Exceptions;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.Service.CadastroService;
import com.example.cadastropet.dtos.CadastroRecordDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class ExceptionsCheck {
    static final int MAX_FILTER_QUANTITY = 2;
    CadastroService cadastroService;
    CadastroRecordDTO cadastroRecordDTO;
    CadastroRepository repository;

    public ExceptionsCheck() {
    }

    CadastroModel cadastro;

    public void CheckExceptionsSave(CadastroRecordDTO cadastroRecordDTO, CadastroModel cadastroModel) {
        if (!cadastroModel.getAge().trim().isEmpty()) {
            if (Integer.parseInt(cadastroModel.getAge()) >= 20) {

                throw new AgeHigherThan19Exception("The pet can't be older than 19.");
            }
        }
        if (!cadastroRecordDTO.weight().trim().isEmpty()) {
            if (Integer.parseInt(cadastroModel.getWeight()) > 60 || Integer.parseInt(cadastroModel.getWeight()) < 0.5) {
                throw new WeightOutOfBoundsException("The pet's weight HAS to be between 0.5 kg and 60kg.");
            }
        }
        if (cadastroModel.getFirstname() == null || !cadastroModel.getFirstname().matches("[a-zA-Z]+") || cadastroModel.getFirstname().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (cadastroModel.getLastname() == null || !cadastroModel.getLastname().matches("[a-zA-Z]+") || cadastroModel.getLastname().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (cadastroModel.getCity() == null || cadastroModel.getCity().trim().isEmpty()) {
            throw new IllegalArgumentException("The city cannot be empty or blank.");
        }
        if (cadastroModel.getStreet() == null || cadastroModel.getStreet().trim().isEmpty()) {
            throw new IllegalArgumentException("The street cannot be empty or blank.");
        }
        if (cadastroModel.getGender() == null) {
            throw new IllegalArgumentException("The gender cannot be empty or blank.");
        }
        if (!cadastroModel.getRace().matches("[\\p{L} ]*")) {
            throw new IllegalArgumentException("The race can't have numbers or special characters");
        }
        if (!cadastroModel.getNumber().matches("[0-9 ]*")) {
            throw new IllegalArgumentException("This field can't have letters or special characters");
        }
        if (!cadastroModel.getCity().matches("[\\p{L} ]*")) {
            throw new IllegalArgumentException("This field can't have numbers or special characters");
        }
    }

    public void CheckExceptionsGet(int active_filters) {
        if (active_filters > MAX_FILTER_QUANTITY) {
            throw new FilterMaxQuantityException("You can only have up to 2 filters active at the same time.");
        }
    }

    public void CheckPutMethodException(CatOrDog type, MascOrFem Gender, CadastroRecordDTO cadastroRecordDTO) {
        if (cadastroRecordDTO.type() != type || cadastroRecordDTO.gender() != Gender) {
            throw new InvalidPutMethodException("The type and sex of the pet can't be changed.");
        }
    }
}