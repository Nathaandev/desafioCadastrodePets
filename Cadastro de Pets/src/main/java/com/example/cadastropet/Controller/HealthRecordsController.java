package com.example.cadastropet.Controller;

import com.example.cadastropet.Model.HealthRecordsModel;
import com.example.cadastropet.Service.HealthRecordService;
import com.example.cadastropet.dtos.HealthRecordsSaveDTO;
import com.example.cadastropet.dtos.HealthRecordsUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/records")
public class HealthRecordsController {
    @Autowired
    HealthRecordService healthRecordService;

    @PostMapping
    public ResponseEntity<HealthRecordsModel> save(@RequestBody HealthRecordsSaveDTO healthRecordsSaveDTO){
        return healthRecordService.save(healthRecordsSaveDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordsModel> Update(@PathVariable("id") String id, @RequestBody HealthRecordsUpdateDTO healthRecordsUpdateDTO){
        return healthRecordService.Update(id, healthRecordsUpdateDTO);
    }
}
