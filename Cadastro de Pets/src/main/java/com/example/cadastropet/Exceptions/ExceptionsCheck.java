package com.example.cadastropet.Exceptions;
import com.example.cadastropet.Enum.CatOrDog;
import com.example.cadastropet.Enum.MascOrFem;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.dtos.CadastroRecordDTO;


public class ExceptionsCheck {
    static final int MAX_FILTER_QUANTITY = 2;

    public ExceptionsCheck() {
    }

    public void CheckExceptionsSave(CadastroRecordDTO cadastroRecordDTO, CadastroModel cadastroModel) {
        if (!cadastroRecordDTO.age().trim().isEmpty()) {
            if (Integer.parseInt(cadastroRecordDTO.age()) >= 20 || Integer.parseInt(cadastroRecordDTO.age()) <= 0) {
                throw new AgeHigherThan19Exception("The pet can't be older than 19 or younger than 0 years old.");
            }
        }
        if (!cadastroRecordDTO.weight().trim().isEmpty()) {
            double weight = Double.parseDouble(cadastroRecordDTO.weight().replace(",", "."));
            if (weight > 60 || weight < 0.5) {
                throw new WeightOutOfBoundsException("The pet's weight HAS to be between 0.5 kg and 60kg.");
            }
        }
        if (cadastroRecordDTO.firstname() == null || !cadastroRecordDTO.firstname().matches("[a-zA-Z]+") || cadastroRecordDTO.firstname().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (cadastroRecordDTO.lastname() == null || !cadastroRecordDTO.lastname().matches("[a-zA-Z]+") || cadastroRecordDTO.lastname().trim().isEmpty()) {
            throw new IllegalArgumentException("The name cannot be empty or blank, and only alphabetic characters (A–Z or a–z) are allowed.");
        }
        if (cadastroRecordDTO.city() == null || cadastroRecordDTO.city().trim().isEmpty()) {
            throw new IllegalArgumentException("The city cannot be empty or blank.");
        }
        if (cadastroRecordDTO.street() == null || cadastroRecordDTO.street().trim().isEmpty()) {
            throw new IllegalArgumentException("The street cannot be empty or blank.");
        }
        if (!cadastroRecordDTO.race().matches("^[^\\d]*$")) {
            throw new IllegalArgumentException("The race can't have numbers.");
        }
        if (!cadastroRecordDTO.number().matches("[0-9 ]*")) {
            throw new IllegalArgumentException("The field number can't have letters or special characters");
        }
        if (!cadastroRecordDTO.city().matches("[\\p{L} ]*")) {
            throw new IllegalArgumentException("The fild city can't have numbers or special characters");
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