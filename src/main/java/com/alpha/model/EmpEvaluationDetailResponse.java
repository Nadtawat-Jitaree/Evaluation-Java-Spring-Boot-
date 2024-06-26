package com.alpha.model;

import com.alpha.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpEvaluationDetailResponse {
    private Long id;
    private String empEvaluationDetailId;
    private Long empEvaluationHead;
    private Float score;
    private String verify;
    private Date evaDateFrom ;
    private Date evaDateTo ;
    private String empEvaluationHeadID;
    private Float totalScore;
    private Long employee;
    private String criteriaName;
    private Float weight;
    private Long evaluation;
    //
    private String email;
    private String prefix;
    private String name;
    private String surName;
}
