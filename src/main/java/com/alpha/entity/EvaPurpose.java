package com.alpha.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Setter
@Getter
@Entity
@Table(name="EVAPURPOSE")
public class EvaPurpose extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="ID",unique = true)
    private Long id;
    
    @Column(name="PURPOSE",nullable = false)
    private String purpose;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name="EMP_EVALUATION_HEAD",nullable = false)
    private EmpEvaluationHead empEvaluationHead;

}