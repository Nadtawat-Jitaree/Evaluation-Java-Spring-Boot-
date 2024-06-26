package com.alpha.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.alpha.utility.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Setter
@Getter
@Entity
@Table(name="EMP_EVALUATION_HEAD")
public class EmpEvaluationHead extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="ID",unique = true)
    private Long id;
	
	@Column(name="EVA_DATE_FROM")
	private Date evaDateFrom ;
	
	@Column(name="EVA_DATE_TO")
	private Date evaDateTo ;
	
    @Column(name="EMP_EVALUATION_HEADID",nullable = false)
    private String empEvaluationHeadID;
    
    @Column(name="TOTAL_SCORE")
    private Float totalScore;
    
    //Employee
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="EMPLOYEE",nullable = false)
    private Employee employee;
    
	public Long getEmployeeId() {
		if(this.employee!=null){
			return  this.employee.getId();
		}else{
			return null;
		}
	}

    
}