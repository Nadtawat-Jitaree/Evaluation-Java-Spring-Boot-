package com.alpha.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alpha.entity.AppRole;
import com.alpha.entity.AppRoleUser;
import com.alpha.repository.AppRoleRepository;
import com.alpha.repository.AppRoleUserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AppRoleUserService {

    @Autowired
    AppRoleUserRepository appRoleUserRepository;

    public Page<AppRoleUser> findAllByCriteria(Integer page, Integer size, Sort.Direction order, String sort, String roleName, String roleDesc,String userName){
        Pageable pageable = PageRequest.of(page, size, new Sort(order, sort));
        return appRoleUserRepository.findAllByCriteria(roleName, roleDesc,userName, pageable);
    }

}
