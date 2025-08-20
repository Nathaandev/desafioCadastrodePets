package com.example.cadastropet.Service;

import com.example.cadastropet.Exceptions.HealthExceptionsCheck;
import com.example.cadastropet.Model.CadastroModel;
import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.Repository.HealthRecordsRepository;
import com.example.cadastropet.dtos.HealthRecordsDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HealthRecordService {

    @Autowired
    HealthRecordsRepository healthRecordsRepository;
    private final HealthExceptionsCheck healthExceptionsCheck;
    CadastroModel cadastroModel = new CadastroModel();
    @Autowired
    public HealthRecordService(HealthExceptionsCheck healthExceptionsCheck) {
        this.healthExceptionsCheck = healthExceptionsCheck;
    }

    public ResponseEntity<HealthRecordsModel> save(@RequestBody HealthRecordsDTO healthRecordsDTO){
        var healthRecordsModel = new HealthRecordsModel();
        BeanUtils.copyProperties(healthRecordsDTO, healthRecordsModel);
        healthExceptionsCheck.CheckIfIdExists(healthRecordsModel.getPetId());
        return ResponseEntity.status(HttpStatus.CREATED).body(healthRecordsRepository.save(healthRecordsModel));

    }
}
