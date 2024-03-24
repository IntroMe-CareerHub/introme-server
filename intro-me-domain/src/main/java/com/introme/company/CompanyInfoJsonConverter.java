package com.introme.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.introme.company.entity.CompanyInfo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Converter
public class CompanyInfoJsonConverter implements AttributeConverter<CompanyInfo, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String convertToDatabaseColumn(CompanyInfo companyInfo) {
        // CompanyInfo을 Json으로 변환
        try {
            return objectMapper.writeValueAsString(companyInfo);
        }catch (JsonProcessingException e) {
            log.error("Fail to serialize as object into Json : {}", companyInfo, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompanyInfo convertToEntityAttribute(String json) {
        // Json을 CompanyInfo로 변환
        try {
            return objectMapper.readValue(json, CompanyInfo.class);
        } catch (IOException e) {
            log.error("Fail to serialize as object into Json : {}", json, e);
            throw new RuntimeException(e);
        }
    }
}
