package com.example.cadastropet.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Health_Records")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class HealthRecords {

    @Id
    private String id;
    private Long petId;
    private Date visitDate;
    private String reasonForVisit;
    private String veterinaryClinic;
    private String veterinaryName;
    private String diagnosis;
}
