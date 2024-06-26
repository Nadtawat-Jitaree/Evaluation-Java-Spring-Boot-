package com.alpha.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpEvaluationHeadResponse {
    private Long id;
    private String createdBy;
    private String updatedBy;
    private Date evaDateFrom ;
    private Date evaDateTo ;
    private String empEvaluationHeadID;
    private Float totalScore;
    private Long EmpEvaluationDetail;
    private Float Score;
    
    private Long employeeId;
    private String EmployeeNo;
    private String position;
    private String department;
    private String email;
    private String prefix;
    private String name;
    private String surName;
}
