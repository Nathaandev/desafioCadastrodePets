package com.example.cadastropet.Repository;

import com.example.cadastropet.Model.HealthRecords;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordsRepository extends MongoRepository<HealthRecords, String> {
}
