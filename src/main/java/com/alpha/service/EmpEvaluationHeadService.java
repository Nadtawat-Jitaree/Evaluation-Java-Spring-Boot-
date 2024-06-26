package com.alpha.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alpha.entity.EmpEvaluationDetail;
import com.alpha.entity.EmpEvaluationHead;
import com.alpha.entity.Employee;
import com.alpha.entity.Evaluation;
import com.alpha.entity.RunningParam;
import com.alpha.model.CreateEmpEvaluationDetailResquest;
import com.alpha.model.EmpEvaluationDetailResponse;
import com.alpha.model.EmpEvaluationHeadResponse;
import com.alpha.repository.EmpEvaluationDetailRepository;
import com.alpha.repository.EmpEvaluationHeadRepository;
import com.alpha.repository.EmployeeRepository;
import com.alpha.repository.EvaluationRepository;
import com.alpha.repository.RunningParamRepository;
import com.alpha.utility.AuthUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpEvaluationHeadService {

    @Autowired
    AuthUtil authUtil;
    
    @Autowired
    EmpEvaluationHeadRepository empEvaluationHeadRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    RunningParamRepository runningParamRepository;
    

    public Page<EmpEvaluationHead> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort,String createdBy){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return empEvaluationHeadRepository.findAllByCriteria(createdBy, pageable);
    }
    
    @Transactional
    public List<EmpEvaluationHeadResponse> addFieldDesc(List<EmpEvaluationHead> contents) {
        List<EmpEvaluationHeadResponse> response = new ArrayList<>();
        for(EmpEvaluationHead content:contents){
        	EmpEvaluationHeadResponse empEvaluationHeadResponse = EmpEvaluationHeadResponse.builder()
                    .id(content.getId())
                    .createdBy(authUtil.getUsernameFromSession())
                    .updatedBy(authUtil.getUsernameFromSession())
                    .evaDateFrom(content.getEvaDateFrom())
                    .evaDateTo(content.getEvaDateTo())
                    .empEvaluationHeadID(content.getEmpEvaluationHeadID())
                    .totalScore(content.getTotalScore())
                    .build();

            if (content.getEmployeeId() != null){
            	Employee employee = employeeRepository.findById(content.getEmployeeId());
            	empEvaluationHeadResponse.setEmail(employee.getEmail());
            	empEvaluationHeadResponse.setEmployeeNo(employee.getEmployeeNo());
            	empEvaluationHeadResponse.setPosition(employee.getPosition());
            	empEvaluationHeadResponse.setDepartment(employee.getDepartment());
            	empEvaluationHeadResponse.setPrefix(employee.getPrefix());
            	empEvaluationHeadResponse.setName(employee.getName());
            	empEvaluationHeadResponse.setSurName(employee.getSurName());
            	empEvaluationHeadResponse.setEmployeeId(employee.getId());
            }

            response.add(empEvaluationHeadResponse);
        }
        return  response;
    }
    
    @Transactional
    public void createEmpEvaluationHead(EmpEvaluationHead payload) {
    	EmpEvaluationHead empEvaluationHead= new EmpEvaluationHead();
    	empEvaluationHead.setCreatedBy(authUtil.getUsernameFromSession());
    	empEvaluationHead.setEvaDateFrom(new Date());
    	empEvaluationHead.setEmployee(payload.getEmployee());
        RunningParam runningParam = runningParamRepository.findByParamCode("empEvaluationHead");
        if(runningParam == null) { 	
            runningParam = new RunningParam();
            runningParam.setParamCode("empEvaluationHead");
            runningParam.setParamValue(0);
            runningParamRepository.save(runningParam);
        }
        runningParam.setParamValue(runningParam.getParamValue() + 1);
        runningParamRepository.save(runningParam);
        empEvaluationHead.setEmpEvaluationHeadID("EVH"+String.format("%04d", runningParam.getParamValue()));

        
        empEvaluationHeadRepository.save(empEvaluationHead);
     }

}