package com.alpha.model;

import com.alpha.entity.EmpEvaluationHead;
import com.alpha.entity.Employee;
import com.alpha.entity.Evaluation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateEmpEvaluationDetailResquest {
    private Long id;
    private String empEvaluationDetailId;
    private String createBy;
    private EmpEvaluationHead empEvaluationHead;
    private Float score;
    private String verify;
    private Date evaDateFrom ;
    private Date evaDateTo ;
    private String empEvaluationID;
    private Float totalScore;
    private Employee employee;
    private Evaluation evaluation;
    private String criteriaName;
    private Float weight;
    //
    private String email;
    private String prefix;
    private String name;
    private String surName;
}
