package com.alpha.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alpha.entity.EmpEvaluationDetail;
import com.alpha.entity.EmpEvaluationHead;
import com.alpha.entity.Evaluation;


@Transactional
public interface EmpEvaluationDetailRepository extends JpaRepository<EmpEvaluationDetail, Integer> {

    @Query("select t from EmpEvaluationDetail t where t.createdBy LIKE %:createdBy%")
    Page<EmpEvaluationDetail> findAllByCriteria(String createdBy,   Pageable pageable);
    
    void deleteById(Long id);



	@Query("select t from EmpEvaluationDetail t  where t.id = :id ")
	EmpEvaluationDetail findByAdjustDetailId(Long id);





}