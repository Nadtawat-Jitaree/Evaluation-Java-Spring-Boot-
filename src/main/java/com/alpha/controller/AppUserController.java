package com.alpha.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.entity.AppRole;
import com.alpha.entity.AppRoleUser;
import com.alpha.entity.AppUser;
import com.alpha.model.ChangePasswordModel;
import com.alpha.model.CreateUserModel;
import com.alpha.repository.AppRoleRepository;
import com.alpha.repository.AppRoleUserRepository;
import com.alpha.repository.AppUserRepository;
import com.alpha.respond.BasePagingResponse;
import com.alpha.respond.BaseResponse;
import com.alpha.service.AppUserService;
import com.alpha.utility.AuthUtil;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    AppUserService appUserService;

    @Autowired
    AppUserRepository appUserRepository;
    
    @Autowired
    AppRoleRepository appRoleRepository;
    
    @Autowired
    AppRoleUserRepository appRoleUserRepository;

    @Autowired
    AuthUtil authUtil;

    @GetMapping("/all")
    public ResponseEntity<Object> getAll(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
            @RequestParam(required = false, defaultValue = "userName") String sort,
            @RequestParam(required = false, defaultValue = "") String userName) {
        Page<AppUser> list = appUserService.findAllByCriteria(page, size, order, sort, userName);
        BasePagingResponse response = BasePagingResponse.builder().data(list.getContent()).page(list.getNumber()).totalSize(list.getTotalElements()).totalPage(list.getTotalPages()).build();
        return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam String userName) {
        BaseResponse response = BaseResponse.builder().data(appUserRepository.findByUserName(userName)).build();
        return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public String update(@PathVariable("userName") String userName, @Valid @RequestBody ChangePasswordModel req) {
        AppUser update = appUserRepository.findByUserName(userName);
        update.setUpdatedBy(authUtil.getUsernameFromSession());
        update.setPassword(authUtil.encryptPassword(req.getPassword()));
        appUserRepository.save(update);
        return "Update success";
    }

    @PostMapping
    public String insert(@Valid @RequestBody CreateUserModel req) {
        AppUser appUser = new AppUser();
        appUser.setUserName(req.getUserName());
        appUser.setPassword(authUtil.encryptPassword(req.getPassword()));
        appUser.setCreatedBy(authUtil.getUsernameFromSession());
        appUserRepository.save(appUser);             
        
        return "Insert param success";
    }

    @DeleteMapping("/{userName}")
    public String delete(@PathVariable("userName") String userName) {
        // TO DO : Confirm เมื่อ Role ถูก Assign ไปแล้ว จะลบออกไม่ได้
        appUserRepository.deleteByUserName(userName);
        return "Delete param success";
    }

}
