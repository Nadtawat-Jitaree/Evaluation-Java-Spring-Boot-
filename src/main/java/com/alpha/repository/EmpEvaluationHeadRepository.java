package com.alpha.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alpha.entity.EmpEvaluationDetail;
import com.alpha.entity.EmpEvaluationHead;
import com.alpha.entity.Employee;
import com.alpha.entity.Evaluation;

@Transactional
public interface EmpEvaluationHeadRepository extends JpaRepository<EmpEvaluationHead, Integer> {

    @Query("select t from EmpEvaluationHead t where t.createdBy LIKE %:createdBy%")
    Page<EmpEvaluationHead> findAllByCriteria(String createdBy,   Pageable pageable);
    
    void deleteById(Long id);

    @Query("select t from EmpEvaluationHead t where t.id=:empheadid ")
    EmpEvaluationHead  findById2(Long empheadid);
    
	EmpEvaluationHead findById(Long empEvaluationHead);





}