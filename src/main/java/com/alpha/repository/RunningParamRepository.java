package com.alpha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.entity.RunningParam;


public interface RunningParamRepository extends JpaRepository<RunningParam, String>{
	
	RunningParam findByParamCode(String paramCode);
}
