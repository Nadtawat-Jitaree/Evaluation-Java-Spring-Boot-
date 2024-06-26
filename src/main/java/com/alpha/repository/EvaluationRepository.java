package com.alpha.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.alpha.entity.Evaluation;

@Transactional
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

	@Query("SELECT t FROM Evaluation t WHERE t.criteriaName LIKE %:criteriaName%")
	Page<Evaluation> findAllByCriteria(String criteriaName, Pageable pageable);

    

    
    void deleteById(Long id);





	Evaluation findById(Long evaluation);


}