package com.alpha.controller;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RestController;

import com.alpha.constant.ResponseConst;
import com.alpha.entity.Employee;
import com.alpha.entity.EvaPurpose;
import com.alpha.entity.Evaluation;
import com.alpha.repository.EmployeeRepository;
import com.alpha.repository.EvaPurposeRepository;
import com.alpha.repository.EvaluationRepository;
import com.alpha.respond.BasePagingResponse;
import com.alpha.respond.BaseResponse;
import com.alpha.respond.ServerResponse;
import com.alpha.service.EvaPurposeService;
import com.alpha.service.EvaluationService;
import com.alpha.utility.AuthUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/EvaPurpose")
public class EvaPurposeController {
	
	@Autowired
	EvaPurposeService evaPurposeService;
	
    @Autowired
    AuthUtil authUtil;
    
    @Autowired
    EvaPurposeRepository evaPurposeRepository;
	
	
    @GetMapping("/all")
    public ResponseEntity<Object> getAllEva_Purpose(
    @RequestParam(required = false, defaultValue = "0") Integer page,
    @RequestParam(required = false, defaultValue = "10") Integer size,
    @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
    @RequestParam(required = false, defaultValue = "purpose") String sort,
    @RequestParam(required = false, defaultValue = "") String purpose)
    {
    Page<EvaPurpose> list = evaPurposeService.findAllByCriteria(page, size, order, sort , purpose);

    BasePagingResponse response = BasePagingResponse.builder().data(list.getContent()).page(list.getNumber())
    .totalSize(list.getTotalElements()).totalPage(list.getTotalPages()).build();

    return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }

	
    @PostMapping
    public String insert(@Valid @RequestBody EvaPurpose req) {
        req.setCreatedBy(authUtil.getUsernameFromSession());
        req.setPurpose(req.getPurpose());
	   
        
        
        evaPurposeRepository.save(req);
        
        return "Insert param success";
    }
    
	@PutMapping("/{id}")
	public ServerResponse updateParam(@PathVariable(value = "id") Long id, @RequestBody EvaPurpose req) {

		System.out.println("Req:" + req.getId());
		System.out.println("Req:" + req.getPurpose());
		

		try {

			req.setUpdatedBy(authUtil.getUsernameFromSession());
			req.setUpdatedDate(new Date());
			req.setPurpose(req.getPurpose());
			// Store

			evaPurposeRepository.save(req);

			return ServerResponse.getServerResponse(ResponseConst.SUCCESS, ResponseConst.RESPONSE_MSG_UPDATE_SUCCESS,
					req);
		} catch (Exception e) {
			return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST, ResponseConst.RESPONSE_MSG_FAIL, req);

		}

	}
	
    @DeleteMapping("/{evaPurposeId}")
    public String delete(@PathVariable("evaluationId") Long evaPurposeId) {
    	evaPurposeRepository.deleteById(evaPurposeId);
        log.info(" -=======lcbj;df====" + evaPurposeId);
        return "Delete param success";
    }
}