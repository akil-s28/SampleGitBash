package com.ninza.hrm.api.baseClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.ExcelUtility;
import com.ninza.hrm.api.genericUtility.FileUtility;
import com.ninza.hrm.api.genericUtility.JavaUtils;

import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseApiClass {
	
  public JavaUtils jLib = new JavaUtils();
  public FileUtility fLib = new FileUtility();
  public DataBaseUtility dbLib  = new DataBaseUtility();
  public ExcelUtility eLib = new ExcelUtility();
  public static RequestSpecification specReqObj;
  public  static ResponseSpecification specResponseObj;
  
	@BeforeSuite
	public void configBS() throws Throwable, Exception {
//		dbLib.connectDataBase();
		System.out.println("================Connect To DB=========");
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setContentType(ContentType.JSON);
		reqBuilder.setBaseUri(fLib.getDataFromProperties("BaseUri"));
//		reqBuilder.setAuth(oauth2(DEFAULT_BODY_ROOT_PATH, null));
//		reqBuilder.addHeader("", "");
		specReqObj = reqBuilder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder(); 
		resBuilder.expectContentType(ContentType.JSON);
		specResponseObj = resBuilder.build();
	}
	
	@AfterSuite
	public void configAS() throws Throwable {
//		dbLib.closeDBConnection();
		System.out.println("==========Disconnect TO DB===========");
	}
}

