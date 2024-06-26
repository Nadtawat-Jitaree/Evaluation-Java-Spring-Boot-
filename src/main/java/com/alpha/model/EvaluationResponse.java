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
public class EvaluationResponse {
    private Long id;
    private String criteriaName;
    private Float weight;
    private Long empEvaluationDetail;
    private Float score;
}
