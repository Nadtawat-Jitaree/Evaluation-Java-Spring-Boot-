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
@Table(name="EVALUATION")
public class Evaluation extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="ID",unique = true)
    private Long id;
    
    @Column(name="CRITERIA_NAME",nullable = false)
    private String criteriaName;
    
    @Column(name="WEIGHT",nullable = false)
    private Float weight;
   
    

}