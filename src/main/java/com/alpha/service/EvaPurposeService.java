package com.alpha.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alpha.entity.Employee;
import com.alpha.entity.EvaPurpose;
import com.alpha.entity.Evaluation;
import com.alpha.repository.EvaPurposeRepository;
import com.alpha.repository.EvaluationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EvaPurposeService {

    @Autowired
    EvaPurposeRepository evaPurposeRepository;

    public Page<EvaPurpose> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort,String purpose){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return evaPurposeRepository.findAllByCriteria(purpose, pageable);
    }


}