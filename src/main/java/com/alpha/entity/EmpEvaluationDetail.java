package com.alpha.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Setter
@Getter
@Entity
@Table(name="EMP_EVALUATION_DETAIL")
public class EmpEvaluationDetail extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="ID",unique = true)
    private Long id;
    
    @Column(name="SCORE",nullable = false)
    private Float score;
    
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="EMP_EVALUATION_HEAD",nullable = false)
    private EmpEvaluationHead empEvaluationHead;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="EVALUATION",nullable = false)
    private Evaluation evaluation;
    

	public Long getEvaluation() {
		if(this.evaluation!=null){
			return  this.evaluation.getId();
		}else{
			return null;
		}
	}
	
	public Long getEmpEvaluationHead() {
		if(this.empEvaluationHead!=null){
			return  this.empEvaluationHead.getId();
		}else{
			return null;
		}
	}
	


}