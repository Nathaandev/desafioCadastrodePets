package com.example.cadastropet.Repository;

import java.util.Optional;

import com.example.cadastropet.Model.HealthRecordsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordsRepository extends MongoRepository<HealthRecordsModel, String> {
    Optional<HealthRecordsModel> findByid(String id);
}
