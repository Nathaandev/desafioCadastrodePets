package com.example.cadastropet.Repository;

import com.example.cadastropet.Model.HealthRecordsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordsRepository extends MongoRepository<HealthRecordsModel, String> {
}
