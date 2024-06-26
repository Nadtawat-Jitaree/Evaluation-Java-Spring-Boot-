package com.alpha.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.alpha.model.EvaluationResponse;
import com.alpha.entity.EmpEvaluationDetail;
import com.alpha.entity.Employee;
import com.alpha.entity.Evaluation;
import com.alpha.repository.EmpEvaluationDetailRepository;
import com.alpha.repository.EvaluationRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaluationService {

    @Autowired
    EvaluationRepository evaluationRepository;
    
    @Autowired
    EmpEvaluationDetailRepository empEvaluationDetailRepository;

    public Page<Evaluation> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort,String criteriaName){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return evaluationRepository.findAllByCriteria(criteriaName, pageable);
    }
    
    

    
    
}