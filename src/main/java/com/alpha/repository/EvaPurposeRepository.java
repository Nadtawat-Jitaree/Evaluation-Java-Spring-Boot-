package com.alpha.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alpha.entity.EvaPurpose;
import com.alpha.entity.Evaluation;

@Transactional
public interface EvaPurposeRepository extends JpaRepository<EvaPurpose, Integer> {

	@Query("SELECT t FROM EvaPurpose t WHERE t.purpose LIKE %:purpose%")
	Page<EvaPurpose> findAllByCriteria(String purpose, Pageable pageable);

    

    
    void deleteById(Long id);


}