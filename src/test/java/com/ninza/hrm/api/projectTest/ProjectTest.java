package com.ninza.hrm.api.projectTest;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;
import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.api.pojoClass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ProjectTest extends BaseApiClass {
	ProjectPojo pObj;
	@Test
	public void addSingleProjectWithCreatedTest() throws SQLException {
		
		String expSucMsg ="Successfully Added"; 
		String ProjectName = "Project_"+jLib.getRandomNumber();
		
		pObj = new ProjectPojo(ProjectName, "created", "Akil",0);
		
		//verify the projectName in API layer
	Response resp=	given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post(IEndPoint.ADDProj);
	resp.then()
		.spec(specResponseObj)
			.assertThat().statusCode(201)
			.assertThat().time(Matchers.lessThan(5000L))
			.log().all();
	
	String actMsg= resp.jsonPath().get("msg");
	Assert.assertEquals(expSucMsg, actMsg);
	
	//verify the projectName in DB layer
//	Boolean flag = false;
//	Driver driverRef =new Driver();
//	DriverManager.registerDriver(driverRef);
//	Connection con = DriverManager.getConnection("DB path","username","password");
//	ResultSet result = con.createStatement().executeQuery("select * from Project");
//	while(result.next()) {
//		if(result.getString("colomNum").equals(ProjectName));
//		flag = true;
//		break;
//	}
//	con.close();
//			Assert.assertTrue(flag, "Project in DB is not verified");
			
		
	}
	
	
	@Test (dependsOnMethods = "addSingleProjectWithCreatedTest")
	public void createDuplicateProjectTest() {
		given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post(IEndPoint.ADDProj)
		.then()
		 	.spec(specResponseObj)
			.assertThat().statusCode(409)
			.log().all();
	}

}

