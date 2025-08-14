package com.example.cadastropet.Service;

import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.dtos.HealthRecordsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HealthRecordService {
    HealthRecordsModel healthRecordsModel;
    HealthRecordsDTO healthRecordsDTO;

    public ResponseEntity<HealthRecordsModel> save(@RequestBody HealthRecordsDTO healthRecordsDTO){
        var Health
    }
}
