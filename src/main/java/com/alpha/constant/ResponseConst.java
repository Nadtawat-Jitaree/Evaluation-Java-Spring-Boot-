package com.alpha.constant;

public class ResponseConst {

    public static String RESPONSE_SUCCESS = "SUCCESS";
    public static String RESPONSE_FAIL = "FAIL";

    public static String DESC_INVALID_USERNAME_PASSWORD = "Invalid username or password";
    
    public static final String RESPONSE_CODE_SUCCESS = "200";
    public static final String RESPONSE_CODE_EMPLOYEE_NOT_FOUND = "DATA_NOT_FOUND";
    public static final String RESPONSE_MSG_UPDATE_SUCCESS = "แก้ไขข้อมูลสำเร็จ";
    public static final String RESPONSE_MSG_CREATE_SUCCESS = "สร้างข้อมูลสำเร็จ";
    public static final String RESPONSE_MSG_DELETE_SUCCESS = "ลบรายการสำเร็จ";
    public static final String RESPONSE_MSG_FAIL = "เกิดข้อขัดข้อง";
    public static final String RESPONSE_MSG_EXISTS_RECORD = "เกิดข้อขัดข้องพบข้อมูลซ้ำซ้อน";
    public static final String RESPONSE_MSG_NULL_FAIL = "เกิดข้อขัดข้องไม่พบข้อมูล";
    public static final String RESPONSE_MSG_QRY_SUCCESS ="เรียกดูข้อมูลสำเร็จ";
  //GENERIC ERROR
  	public static final int ERROR = 600;
  	
  	//SUCCESS CODES
  	public static final int SUCCESS = 200;
  	
  	//Application CODES

  	//BAD REQUEST
  	public static final int BAD_REQUEST = 400;
  	
  	//URL resource available
  	public static final int NOT_FOUND = 404;
  	
  	//Method Not Allowed
  	public static final int METHOD_NOT_ALLOW = 405;
  	
  	//UnSupport media
  	public static final int UNSUPPORT_MEDIA_TYPE = 415;
  	
  	//Internal Error
  	public static final int INTERNAL_ERROR = 500;
  	
  	
   	public static final int UPLOAD_FILE_ERR_SIZE = 401;
  	public static final int UPLOAD_FILE_ERR_CONTENT = 402;
  	public static final int UPLOAD_FILE_ERR_DATA = 403;
  	public static final int UPLOAD_FILE_ERR_EMPL = 404;
  	public static final int UPLOAD_FILE_ERR_FDATE = 405;
	public static final int UPLOAD_FILE_SUCCESS = 200;
	public static final int UPLOAD_FILE_NULL_FAIL = 406;
	public static final int UPLOAD_FILE_FAIL = 407;
	
  	public static final String MSG_UPLOAD_FILE_ERR_SIZE = "ขนาดไฟล์ไม่ถูกต้อง";
  	public static final String MSG_UPLOAD_FILE_ERR_CONTENT = "ประเภทไฟล์ไม่ถูกต้อง";
  	public static final String MSG_UPLOAD_FILE_ERR_DATA = "ไม่พบข้อมูลการอัพโหลด";
  	public static final String MSG_UPLOAD_FILE_ERR_EMPL = "ไม่พบข้อมูลพนักงานบางส่วน";
  	public static final String MSG_UPLOAD_FILE_ERR_FDATE = "รูปแบบวันที่ไม่ถูกต้องบางส่วน";
  	public static final String MSG_UPLOAD_FILE_SUCCESS = "อัพโหลดไฟล์สำเร็จ";
  	public static final String MSG_UPLOAD_NULL_FAIL = "เกิดข้อขัดข้องอัพโหลดไฟล์ไม่สำเร็จ";
  	public static final String MSG_UPLOAD_FILE_FAIL = "เกิดข้อผิดพลาดอัพโหลดไฟล์ไม่สำเร็จ";
  	public static final String MSG_UPLOAD_FILE_ERR = "มีค่าว่าง หรือ กรอกได้แค่นามสกุลไฟล์ .png และ .jpg เท่านั้น!!";
  	
}
