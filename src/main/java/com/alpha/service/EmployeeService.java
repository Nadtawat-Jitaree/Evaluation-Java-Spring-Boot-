package com.alpha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.alpha.entity.Employee;
import com.alpha.repository.EmployeeRepository;

@Slf4j
@Service
public class EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    public Page<Employee> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort, String employeeNo, String name, String surName ){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return employeeRepository.findAllByCriteria(employeeNo, name, surName , pageable);
    }
    
    public Page<Employee> findAllPage(Integer page, Integer size, Sort.Direction order, String sort){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return employeeRepository.findAllPage(pageable);
    }
    
}
