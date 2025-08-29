package com.example.cadastropet.dtos;

import java.util.Date;

public record HealthRecordsSaveDTO(
        Long petId, Date visitDate, String reasonForVisit, String veterinaryClinic, String veterinaryName, String diagnosis) {
}
