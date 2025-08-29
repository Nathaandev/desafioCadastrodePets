package com.example.cadastropet.Service;

import java.util.Optional;

import com.example.cadastropet.Exceptions.HealthExceptionsCheck;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.Repository.CadastroRepository;
import com.example.cadastropet.Repository.HealthRecordsRepository;
import com.example.cadastropet.dtos.HealthRecordsSaveDTO;
import com.example.cadastropet.dtos.HealthRecordsUpdateDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HealthRecordService {

    @Autowired
    HealthRecordsRepository healthRecordsRepository;
    CadastroRepository cadastroRepository;
    private final HealthExceptionsCheck healthExceptionsCheck;
    CadastroModel cadastroModel = new CadastroModel();
    @Autowired
    public HealthRecordService(HealthExceptionsCheck healthExceptionsCheck) {
        this.healthExceptionsCheck = healthExceptionsCheck;
    }

    public ResponseEntity<HealthRecordsModel> save(@RequestBody HealthRecordsSaveDTO healthRecordsSaveDTO){
        var healthRecordsModel = new HealthRecordsModel();
        BeanUtils.copyProperties(healthRecordsSaveDTO, healthRecordsModel);
        healthExceptionsCheck.checkExceptions(healthRecordsModel, healthRecordsModel.getPetId());
        return ResponseEntity.status(HttpStatus.CREATED).body(healthRecordsRepository.save(healthRecordsModel));
    }
    public ResponseEntity<HealthRecordsModel> Update(@PathVariable("id") String id, @RequestBody HealthRecordsUpdateDTO healthRecordsUpdateDTO){
        Optional<HealthRecordsModel> pet = healthRecordsRepository.findByid(id);
        var healthRecordsModel = new HealthRecordsModel();
        if(pet.isEmpty()) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        healthRecordsModel = pet.get();
        Long petId = healthRecordsModel.getPetId();
        BeanUtils.copyProperties(healthRecordsUpdateDTO, healthRecordsModel);
        healthExceptionsCheck.checkExceptions(healthRecordsModel, petId);
        return ResponseEntity.status(HttpStatus.OK).body(healthRecordsRepository.save(healthRecordsModel));
    }
}
