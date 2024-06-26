package com.alpha.controller;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alpha.constant.ResponseConst;
import com.alpha.entity.EmpEvaluationDetail;
import com.alpha.entity.EmpEvaluationHead;
import com.alpha.entity.Employee;
import com.alpha.entity.Evaluation;
import com.alpha.model.CreateEmpEvaluationDetailResquest;
import com.alpha.model.EmpEvaluationDetailResponse;
import com.alpha.model.EmpEvaluationHeadResponse;
import com.alpha.repository.EmpEvaluationDetailRepository;
import com.alpha.repository.EmpEvaluationHeadRepository;
import com.alpha.repository.EmployeeRepository;
import com.alpha.repository.EvaluationRepository;
import com.alpha.respond.BasePagingResponse;
import com.alpha.respond.BaseResponse;
import com.alpha.respond.ServerResponse;
import com.alpha.service.EmpEvaluationHeadService;
import com.alpha.service.EvaluationService;
import com.alpha.utility.AuthUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/empEvaluationHead")
public class EmpEvaluationHeadController {
	
	@Autowired
	EmpEvaluationHeadService empEvaluationHeadService;
	
    @Autowired
    AuthUtil authUtil;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    EmpEvaluationHeadRepository empEvaluationHeadRepository;
	
    @GetMapping("/all")
    public ResponseEntity<Object> getAllEmpEvaluationHead(
    	    @RequestParam(required = false, defaultValue = "0") Integer page,
    	    @RequestParam(required = false, defaultValue = "10") Integer size,
    	    @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
    	    @RequestParam(required = false, defaultValue = "createdBy") String sort,
    	    @RequestParam(required = false, defaultValue = "") String createdBy)
    	    {
    	    Page<EmpEvaluationHead> list = empEvaluationHeadService.findAllByCriteria(page, size, order, sort , createdBy);
    	    List<EmpEvaluationHeadResponse> contents = empEvaluationHeadService.addFieldDesc(list.getContent());
    	    BasePagingResponse response = BasePagingResponse.builder().data(contents).page(list.getNumber()).totalSize(list.getTotalElements()).totalPage(list.getTotalPages()).build();
    	    return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }

	
//    @PostMapping
//    public String insert(@Valid @RequestBody EmpEvaluationHead req) {
//        req.setCreatedBy(authUtil.getUsernameFromSession());
//        req.setEvaDateFrom(req.getEvaDateFrom());
//        req.setEvaDateTo(req.getEvaDateTo());
//        req.setEmpEvaluationID(req.getEmpEvaluationID());
//        req.setTotalScore(req.getTotalScore());
//        
//        
//        empEvaluationHeadRepository.save(req);
//        
//        return "Insert param success";
//    }
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addEmpEvaluationHead(@Valid @RequestBody EmpEvaluationHead payload) {
        log.info("EmpEvaluationHead::add Request : {}", payload);
        empEvaluationHeadService.createEmpEvaluationHead(payload);
    }
    
	@PutMapping("/{empheadid}")
	public ServerResponse updateParam(@PathVariable(value = "empheadid") Long empheadid,@RequestBody EmpEvaluationHead req) {

		System.out.println("Req:" + req.getId());
		System.out.println("Req:" + req.getEvaDateFrom());
		

		try {
			req.setCreatedBy(authUtil.getUsernameFromSession());
			req.setUpdatedBy(authUtil.getUsernameFromSession());
			req.setUpdatedDate(new Date());
			req.setEvaDateTo(new Date());
	        req.setTotalScore(req.getTotalScore());
			// Store

			empEvaluationHeadRepository.save(req);

			return ServerResponse.getServerResponse(ResponseConst.SUCCESS, ResponseConst.RESPONSE_MSG_UPDATE_SUCCESS,
					req);
		} catch (Exception e) {
			return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST, ResponseConst.RESPONSE_MSG_FAIL, req);

		}

	}
	
    @DeleteMapping("/{empEvaluationHeadId}")
    public String delete(@PathVariable("empEvaluationHeadId") Long empEvaluationHeadId) {
    	empEvaluationHeadRepository.deleteById(empEvaluationHeadId);
        log.info(" -=======lcbj;df====" + empEvaluationHeadId);
        return "Delete param success";
    }
}