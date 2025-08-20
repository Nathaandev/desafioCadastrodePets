package com.example.cadastropet.Controller;

import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.Service.HealthRecordService;
import com.example.cadastropet.dtos.HealthRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/records")
public class HealthRecordsController {
    @Autowired
    HealthRecordService healthRecordService;

    @PostMapping
    public ResponseEntity<HealthRecordsModel> save(@RequestBody HealthRecordsDTO healthRecordsDTO){
        return healthRecordService.save(healthRecordsDTO);
    }
}
