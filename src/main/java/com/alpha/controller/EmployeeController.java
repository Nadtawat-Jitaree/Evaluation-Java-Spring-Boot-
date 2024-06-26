package com.alpha.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alpha.constant.ResponseConst;
import com.alpha.entity.AppUser;
import com.alpha.entity.Employee;
import com.alpha.repository.AppUserRepository;
import com.alpha.repository.EmployeeRepository;
import com.alpha.respond.BasePagingResponse;
import com.alpha.respond.BaseResponse;
import com.alpha.respond.ServerResponse;
import com.alpha.service.EmployeeService;
import com.alpha.utility.AuthUtil;

import javax.validation.Valid;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController // ประกาศให้รู้ว่าอันนี้คือ Controller
@RequestMapping("/Employee") // กำหนด Mapping
public class EmployeeController {

    @Autowired // เรียกหรือดึงข้อมูลจาก class employeeService มาใช้งานโดยอัตโนมัติ
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AuthUtil authUtil;
    
    

    @GetMapping("/all") //สร้าง Method เพื่อรับ HTTP GET request มาที่ URL นี้
    public ResponseEntity<Object> getAllEmployee( // public ResponseEntity<Object> ชื่อMethod
            @RequestParam(required = false, defaultValue = "0") Integer page, // การดึงค่าจาก Query 
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction order,
            @RequestParam(required = false, defaultValue = "employeeNo") String sort,
            @RequestParam(required = false, defaultValue = "") String employeeNo,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String surName
            ){
        Page<Employee> list = employeeService.findAllByCriteria(page, size, order, sort, employeeNo, name, surName); // Page คือ รายการย่อยของออบเจค โดยใช้ ตัวแปร list เก็บค่า service.เป็นเมธอดที่ใช้ในการค้นหาข้อมูลจากฐานข้อมูลโดยมีเงื่อนไขหลายๆประการ // findAllByCriteria ถูกเรียกใช้เพื่อค้นหาข้อมูลพนักงานจาก employeeService โดยมีการระบุหน้า (page), ขนาดหน้า (size), ลำดับการเรียง (order), ฟิลด์ที่ใช้เรียง (sort), และเงื่อนไขอื่น ๆ เช่น เลขพนักงาน (employeeNo), ชื่อ (name), และนามสกุล (surName) ที่เป็นตัวกรองในการค้นหา
    	
    	BasePagingResponse response = BasePagingResponse.builder().data(list.getContent()).page(list.getNumber()).totalSize(list.getTotalElements()).totalPage(list.getTotalPages()).build();
    	
    	return new ResponseEntity<Object>(response, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Object> get(@RequestParam String employeeNo) { // ใช้ method get @requestParam มาโดยดึงค่า ตัวแปร employeeNo เพื่อใช้ค้นหาด้วย employeeNo
        BaseResponse response = BaseResponse.builder().data(employeeRepository.findByEmployeeNo(employeeNo)).build(); // นำมาค้นหาโดยใช้ medtod findByEmployeeNo(ชื่อตัวแปร)ที่เชื่อมโยงไป Repository
        return new ResponseEntity<Object>(response, null, HttpStatus.OK); // รีเทิร์น Object มา HttpStatus.OK และแสดง alert เป็น 200
    }
    
    @GetMapping("/allid") //สร้าง Method เพื่อรับ HTTP GET request มาที่ URL นี้
    public ResponseEntity<Object> get(@RequestParam Long employeeId) { // โดย ใช้ method get @requestParam มาโดยดึงค่า ตัวแปร employeeId เพื่อค้นหาด้วย employeeId
        BaseResponse response = BaseResponse.builder().data(employeeRepository.findById(employeeId)).build(); // นำมาค้นหาโดยใช้ medtod findById(ชื่อตัวแปร) ที่เชื่อมโยงไป Repository
        return new ResponseEntity<Object>(response, null, HttpStatus.OK); // รีเทิร์น Object มา HttpStatus.OK และแสดง alert เป็น 200
    }
   
    @PutMapping("/{employeeId}") //สร้าง Method เพื่อรับ HTTP Put request มาที่ URL นี้
    public ServerResponse updateParam(@PathVariable(value = "employeeId") Long employeeId,@RequestBody Employee req) { // ใช้ method updateParam รับค่าพารามิเตอร์จาก URL ด้วย @PathVariable annotation ใน Spring Boot ซึ่งเมื่อมีการเรียก API ด้วย URL ที่มีรูปแบบเช่น /employees/{employeeId} จะส่งค่า employeeId มายังเมธอดนี้เพื่อใช้ในการอัพเดตข้อมูลของพนักงานที่มี
		 
    	System.out.println("Req:" + req.getId()); // ใช้ แดสงผลทาง log
    	System.out.println("Req:" + req.getEmployeeNo());
    	
    	try {
			    //Get Store
    		    //Get AppUser
    		   /*		   
		    	Ou ou = new Ou();
		    	ou = req.getOu();
		    	System.out.println("OU:"+ ou.getId());
		    	ItemMainType itemMainTypeUpdate = itemMainTypeRepository.findById(itemMainTypeId);
		        itemMainTypeUpdate.setItemTypeCD(req.getItemTypeCD());
		        itemMainTypeUpdate.setItemTypeDesc(req.getItemTypeDesc());
		        itemMainTypeUpdate.setStatus(req.getStatus());
		        itemMainTypeUpdate.setOu(ou);
		        itemMainTypeUpdate.setUpdatedBy(authUtil.getUsernameFromSession());
		        itemMainTypeUpdate.setUpdatedDate(new Date());
		        itemMainTypeRepository.save(itemMainTypeUpdate);
	*/
    			
    			
    			System.out.println("AppUer:" +  req.getAppUser());
    			
    			Employee updEmployee = employeeRepository.findById(req.getId());
    			
		    	updEmployee.setUpdatedBy(authUtil.getUsernameFromSession());
		        updEmployee.setUpdatedDate(new Date());
		        updEmployee.setName(req.getName());
		        updEmployee.setSurName(req.getSurName());
		        updEmployee.setDepartment(req.getDepartment());
		        updEmployee.setPosition(req.getPosition());
		        updEmployee.setEmail(req.getEmail());
		        //Store
		    
		        updEmployee.setVerify(req.getVerify());
		        updEmployee.setPrefix(req.getPrefix());
		        updEmployee.setAppUser(req.getAppUser());
		        employeeRepository.save(updEmployee);
		        
		        return ServerResponse.getServerResponse(ResponseConst.SUCCESS,ResponseConst.RESPONSE_MSG_UPDATE_SUCCESS,req);
		   } catch (Exception e) {
			   return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST,ResponseConst.RESPONSE_MSG_FAIL,req);
	    		
		   }
		        
    } 

    @PostMapping
    public ServerResponse insertParam(@Valid @RequestBody Employee req) {
		   try {
			   
//			    Optional<Ou> ou = ouRepository.findById(req.getOu().getId());
//			    System.out.println("OU"+ou.isPresent());
//			    req.setOu(ou.get());
		        req.setCreatedBy(authUtil.getUsernameFromSession());
		        req.setCreatedDate(new Date());
		      
			    //AppUser
			    AppUser appUser = appUserRepository.findById(req.getAppUser().getId());
			    req.setAppUser(appUser);
		        employeeRepository.save(req);
		        return ServerResponse.getServerResponse(ResponseConst.SUCCESS,ResponseConst.RESPONSE_MSG_CREATE_SUCCESS,req);
		   } catch (Exception e) {
//			   Optional<Ou> ou = ouRepository.findById(req.getOu().getId());
//			    System.out.println("OU"+ou.isPresent());
			    
			   return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST,ResponseConst.RESPONSE_MSG_FAIL,req);
	    		
		   }    
	}

    @DeleteMapping("/{employeeId}")
    public ServerResponse deleteParam(@PathVariable(value = "employeeId") Long employeeId) {
           try {
        	    Employee delEmployee = employeeRepository.findById(employeeId);
        	    delEmployee.setStatus("Deleted");
//		        employeeRepository.deleteById(employeeId);
        	    delEmployee.setUpdatedBy(authUtil.getUsernameFromSession());
        	    delEmployee.setUpdatedDate(new Date());
        	    employeeRepository.save(delEmployee);
        	return ServerResponse.getServerResponse(ResponseConst.SUCCESS,ResponseConst.RESPONSE_MSG_DELETE_SUCCESS,delEmployee);
        			   
//		        return ServerResponse.getServerResponse(ResponseConst.SUCCESS,ResponseConst.RESPONSE_MSG_DELETE_SUCCESS,employeeId);
		   } catch (Exception ex) {
			   String msg = ex.getMessage();
		        if (ex.getCause().getCause() instanceof SQLException) {
		            SQLException e = (SQLException) ex.getCause().getCause();

		            if (e.getMessage().contains("Key")) {
		                msg = e.getMessage().substring(e.getMessage().indexOf("Key"));
		            }
		        }
			   return ServerResponse.getServerResponse(ResponseConst.BAD_REQUEST,msg,employeeId);
	    		
		   }  
    }

}
