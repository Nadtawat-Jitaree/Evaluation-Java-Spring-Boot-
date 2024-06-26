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
import com.alpha.model.EmpEvaluationDetailRequest;
import com.alpha.model.EmpEvaluationDetailResponse;
import com.alpha.model.EvaluationResponse;
import com.alpha.repository.EmpEvaluationDetailRepository;
import com.alpha.repository.EmpEvaluationHeadRepository;
import com.alpha.repository.EmployeeRepository;
import com.alpha.repository.EvaluationRepository;
import com.alpha.respond.BasePagingResponse;
import com.alpha.respond.BaseResponse;
import com.alpha.respond.ServerResponse;
import com.alpha.service.EmpEvaluationDetailService;
import com.alpha.service.EvaluationService;
import com.alpha.utility.AuthUtil;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/empevaluationdetail")
public class EmpEvaluationDetailController {
	
	@Autowired
	EmpEvaluationDetailService empEvaluationDetailService;
	
    @Autowired
    AuthUtil authUtil;
    
    @Autowired
    EmpEvaluationDetailRepository empEvaluationDetailRepository;
    
    @Autowired
    EmpEvaluationHeadRepository empEvaluationHeadRepository;
	
	
    @GetMapping("/all")
    public ResponseEntity<Object> getAllEmpEvaluationDetail(
    @RequestParam(required = false, defaultValue = "0") Integer page,
    @RequestParam(required = false, defaultValue = "10") Integer size,
    @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
    @RequestParam(required = false, defaultValue = "createdBy") String sort,
    @RequestParam(required = false, defaultValue = "") String createdBy)
    {
    Page<EmpEvaluationDetail> list = empEvaluationDetailService.findAllByCriteria(page, size, order, sort , createdBy);
    List<EmpEvaluationDetailResponse> contents = empEvaluationDetailService.addFieldDesc(list.getContent());
    BasePagingResponse response = BasePagingResponse.builder().data(contents).page(list.getNumber()).totalSize(list.getTotalElements()).totalPage(list.getTotalPages()).build();
    return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }
    
	
//    @PostMapping
//    public String insert(@Valid @RequestBody EmpEvaluationDetail req) {
//        req.setCreatedBy(authUtil.getUsernameFromSession());
//        req.setScore(req.getScore()); 
//        req.setEmpEvaluationDetailId(req.getEmpEvaluationDetailId()); 
//        
//        empEvaluationDetailRepository.save(req);
//        
//        return "Insert param success";
//    }
    
    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addEmpEvaluationDetail(@Valid @RequestBody CreateEmpEvaluationDetailResquest payload) {
        log.info("EmpEvaluationDetail::add Request : {}", payload);
        empEvaluationDetailService.createEmpEvaluationDetail(payload);
    }
    
//	@PutMapping("/{id}")
//	public ServerResponse updateParam(@PathVariable(value = "id") Long id, @RequestBody EmpEvaluationDetail req) {
//
//		System.out.println("Req:" + req.getId());
//		System.out.println("Req:" + req.getScore());
//		
//
//		try {
//
//			req.setUpdatedBy(authUtil.getUsernameFromSession());
//			req.setUpdatedDate(new Date());
//			req.setScore(req.getScore()); 
//			req.setEmpEvaluationDetailId(req.getEmpEvaluationDetailId()); 
//			// Store
//
//			empEvaluationDetailRepository.save(req);
//
//			return ServerResponse.getServerResponse(ResponseConst.SUCCESS, ResponseConst.RESPONSE_MSG_UPDATE_SUCCESS,
//					req);
//		} catch (Exception e) {
//			return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST, ResponseConst.RESPONSE_MSG_FAIL, req);
//
//		}
//
//	}
	
	@PutMapping("{id}")
	public ResponseEntity<Object> update(@PathVariable("id") Long id, @RequestBody EmpEvaluationDetailRequest req) {
		System.out.println("cal------------------------");
		try {
			empEvaluationDetailService.update(id, req);

			return new ResponseEntity<Object>(ResponseConst.RESPONSE_MSG_UPDATE_SUCCESS, null, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(ResponseConst.RESPONSE_MSG_FAIL, null, HttpStatus.BAD_REQUEST);
		}
	}
	
    @DeleteMapping("/{empEvaluationDetailId}")
    public String delete(@PathVariable("empEvaluationDetailId") Long empEvaluationDetailId) {
    	empEvaluationDetailRepository.deleteById(empEvaluationDetailId);
        log.info(" -=======lcbj;df====" + empEvaluationDetailId);
        return "Delete param success";
    }
}