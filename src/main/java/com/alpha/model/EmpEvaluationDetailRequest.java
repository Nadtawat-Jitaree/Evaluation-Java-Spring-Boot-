package com.alpha.model;

import com.alpha.entity.Evaluation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpEvaluationDetailRequest {
	private Long id;
	private Float Score;
	private Evaluation evaluation;
}
