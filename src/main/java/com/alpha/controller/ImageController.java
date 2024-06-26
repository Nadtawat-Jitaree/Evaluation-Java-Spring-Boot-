package com.alpha.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.constant.ResponseConst;
import com.alpha.respond.ServerResponse;
import com.alpha.service.ImageService;
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse.File;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/img") // กำหนด URL
public class ImageController {

    private final ImageService imageService; // เรียกใช้ Service
    

    
    @PostMapping
    public String upload(@RequestParam("file") MultipartFile multipartFile) { // สร้าง method upload โดยRequestParam เป็น file โดยกำหนดตัวแปร multipartFile มากำหนด MultipartFile
        
        
        String fileName = multipartFile.getOriginalFilename(); // กำหนด ตัวแปรfilename = ตัวแปรmultipartFile.methodของspingframeworkที่ใช้เพื่อรับชื่อของไฟล์ที่อัพโหลดมา
        if (fileName != null && !fileName.toLowerCase().endsWith(".png") && !fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".jpeg")) {
            // โดยใช้ if มาเช็คค่าตัวแปร filename ว่ามีค่าว่าง หรือนามสกุลเป็น .jpg หรือ .png หรือไม่ โดยใช้ toLowerCase().endsWith หรือก็คือนามสกุลไฟล์ ว่า ไม่ใช่นามสกุลนี้ และ ไม่ใช่นามสกุลนี้ และก็ ไม่ใช่นามสกุลนี้หรือไม่ถ้าใช่ก็จะเข้าเงื่อนไข ถ้า ใช่ก็จะไปรีเทิร์นส่งค่าไปให้ service
        	return "มีค่าว่าง หรือ กรอกได้แค่นามสกุลไฟล์ .png และ .jpg เท่านั้น!!";
        	
        }
        return imageService.upload(multipartFile); // รีเทิร์น service.ชื่อmethod(ชื่อตัวแปร)
    }
    
}
