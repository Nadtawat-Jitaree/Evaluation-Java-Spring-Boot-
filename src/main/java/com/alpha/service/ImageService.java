package com.alpha.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alpha.constant.ResponseConst;
import com.alpha.respond.ServerResponse;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.io.Files;

@Service
public class ImageService {
	

    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("javaspringboot-b4c2b.appspot.com", fileName); // ใช้ bucker ใน firebase 
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        
        try (InputStream inputStream = ImageService.class.getClassLoader().getResourceAsStream("javaspringbootfirebase.json")) { // ใช้ .json ที่ generate มาจาก firebase มาไว้ที่โฟล์เดอร์ src/main/resources
            if (inputStream == null) {
                throw new IOException("Firebase credentials file not found.");
            }
            Credentials credentials = GoogleCredentials.fromStream(inputStream);
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            storage.create(blobInfo, Files.toByteArray(file));
        }
        
        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/javaspringboot-b4c2b.appspot.com/o/%s?alt=media";
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException { // เป็นเมธอดที่แปลง MultipartFile เป็น File และคืนค่า File ที่ถูกสร้างขึ้น
        File tempFile = new File(fileName); // กำหนดตัวแปรที่มีค่าเป็นFile รับค่า new File(ชื่อตัวแปร)
        try (FileOutputStream fos = new FileOutputStream(tempFile)) { // เอามา แปลง โดยใช้ FileOutputStream
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public String upload(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  
            File file = this.convertToFile(multipartFile, fileName);                      
            String URL = this.uploadFile(file, fileName);                                   
            file.delete();
            return URL;
        } catch (Exception e) {
            e.printStackTrace();
            return "Image couldn't upload, Something went wrong";
        }
    }
    
}
