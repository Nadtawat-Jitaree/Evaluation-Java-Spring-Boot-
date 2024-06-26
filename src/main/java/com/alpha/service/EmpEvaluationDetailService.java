package com.alpha.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.UnexpectedTypeException;

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
import com.alpha.entity.Employee;
import com.alpha.model.CreateEmpEvaluationDetailResquest;
import com.alpha.model.EmpEvaluationDetailRequest;
import com.alpha.model.EmpEvaluationDetailResponse;
import com.alpha.model.EmpEvaluationHeadResponse;
import com.alpha.model.EvaluationResponse;
import com.alpha.repository.EmpEvaluationDetailRepository;
import com.alpha.repository.EmpEvaluationHeadRepository;
import com.alpha.repository.EmployeeRepository;
import com.alpha.repository.EvaluationRepository;
import com.alpha.utility.AuthUtil;
import com.alpha.entity.RunningParam;
import com.alpha.repository.RunningParamRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpEvaluationDetailService {
    @Autowired
    AuthUtil authUtil;

    @Autowired
    EmpEvaluationDetailRepository empEvaluationDetailRepository;

    @Autowired
    EmpEvaluationHeadRepository empEvaluationHeadRepository;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    RunningParamRepository runningParamRepository;
    
    public Page<EmpEvaluationDetail> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort,String createdBy){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return empEvaluationDetailRepository.findAllByCriteria(createdBy, pageable);
    }
    


    @Transactional
    public List<EmpEvaluationDetailResponse> addFieldDesc(List<EmpEvaluationDetail> contents) {
        List<EmpEvaluationDetailResponse> response = new ArrayList<>();
        for(EmpEvaluationDetail content:contents){
        	EmpEvaluationDetailResponse empEvaluationDetailResponse = EmpEvaluationDetailResponse.builder()
                    .id(content.getId())
                    .score(content.getScore())
                    .build();

            if (content.getEvaluation() != null){
            	Evaluation evaluation = evaluationRepository.findById(content.getEvaluation());
            	empEvaluationDetailResponse.setCriteriaName(evaluation.getCriteriaName());
            	empEvaluationDetailResponse.setWeight(evaluation.getWeight()); 	
            	empEvaluationDetailResponse.setEvaluation(evaluation.getId());
            }
            if (content.getEmpEvaluationHead() != null){
            	EmpEvaluationHead empEvaluationHead = empEvaluationHeadRepository.findById(content.getEmpEvaluationHead());
            	empEvaluationDetailResponse.setEvaDateFrom(empEvaluationHead.getEvaDateFrom());
            	empEvaluationDetailResponse.setEvaDateTo(empEvaluationHead.getEvaDateTo());
            	empEvaluationDetailResponse.setEmpEvaluationHeadID(empEvaluationHead.getEmpEvaluationHeadID());
            	empEvaluationDetailResponse.setTotalScore(empEvaluationHead.getTotalScore());
            	empEvaluationDetailResponse.setEmpEvaluationHead(empEvaluationHead.getId());
            }
            

            response.add(empEvaluationDetailResponse);
            
        }
        return  response;
    }
    
    @Transactional
    public void createEmpEvaluationDetail(CreateEmpEvaluationDetailResquest payload) {
    	EmpEvaluationDetail empEvaluationDetail= new EmpEvaluationDetail();
    	empEvaluationDetail.setCreatedBy(authUtil.getUsernameFromSession());
    	empEvaluationDetail.setScore(payload.getScore());
    	empEvaluationDetail.setEmpEvaluationHead(payload.getEmpEvaluationHead());
    	empEvaluationDetail.setEvaluation(payload.getEvaluation());

        
    	empEvaluationDetailRepository.save(empEvaluationDetail);
     }
    
	public void update(Long id, EmpEvaluationDetailRequest req) {
		EmpEvaluationDetail old = empEvaluationDetailRepository.findByAdjustDetailId(id);
		old.setUpdatedBy(authUtil.getUsernameFromSession());
		old.setUpdatedDate(new Date());
		old.setScore(req.getScore()); 

		empEvaluationDetailRepository.save(old);
	}
    

}