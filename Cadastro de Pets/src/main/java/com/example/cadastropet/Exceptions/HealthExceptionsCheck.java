package com.example.cadastropet.Exceptions;

import com.example.cadastropet.Repository.CadastroRepository;
import org.springframework.stereotype.Component;

@Component
public class HealthExceptionsCheck {
    public HealthExceptionsCheck(CadastroRepository cadastroRepository) {
        this.cadastroRepository = cadastroRepository;
    }

    private final CadastroRepository cadastroRepository;

    public void CheckIfIdExists(Long petId){
        var idExists = cadastroRepository.existsById(petId);
       if (!idExists) {
            throw new InvalidPetIDException("There is no pet with this ID in the database.");
       }
    }
}
