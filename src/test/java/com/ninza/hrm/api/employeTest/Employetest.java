package com.ninza.hrm.api.employeTest;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.ninza.hrm.api.baseClass.BaseApiClass;
import com.ninza.hrm.api.pojoClass.EmployePojo;
import com.ninza.hrm.api.pojoClass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IEndPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Employetest extends BaseApiClass{
	ProjectPojo pObj;
	EmployePojo empObj;
	String ProjectName = "Tesla"+ jLib.getRandomNumber();
	@Test
	public void addEmployeTest() {
		 pObj = new ProjectPojo(ProjectName, "created", "Akil", 0);
	Response resp =	given()
			.spec(specReqObj)
			.body(pObj)
		.when()
			.post(IEndPoint.ADDProj);
		resp.then()
			.spec(specResponseObj)
			.assertThat().statusCode(201)
			.assertThat().body("msg", Matchers.equalTo("Successfully Added"))
			.log().all();
		 
		 
				  empObj = new EmployePojo("Test_Eng", "2/12/1995", "Akil123@gmail.com", "Ganesh"+jLib.getRandomNumber(), 4 , "9879695949", ProjectName, "ROLE_EMPLOYEE", "Ganesh"+ jLib.getRandomNumber());
		given()
			.spec(specReqObj)
			.body(empObj)
		.when()
			.post(IEndPoint.AddEmp)
		.then()
			.spec(specResponseObj)
			.assertThat().contentType(ContentType.JSON)
			.assertThat().statusCode(201)
			.and()
			.time(Matchers.lessThan(5000L))
			.log().all();
		
	}
	@Test (dependsOnMethods = "addEmployeTest")
	public void addEmployeWithOutEmail() {
		ProjectPojo pObj = new ProjectPojo(ProjectName, "created", "Akil", 0);
		Response resp =	given()
				.spec(specReqObj)
				.body(pObj)
			.when()
				.post(IEndPoint.ADDProj);
			resp.then()
				.spec(specResponseObj)
				.assertThat().statusCode(201)
				.assertThat().body("msg", Matchers.equalTo("Successfully Added"))
				.log().all();
			 
			 
				 empObj= new EmployePojo("Test_Eng", "2/12/1995","", "Ganesh"+jLib.getRandomNumber(), 4 , "9879695949", ProjectName, "ROLE_EMPLOYEE", "Ganesh"+ jLib.getRandomNumber());
			given()
				.spec(specReqObj)
				.body(empObj)
			.when()
				.post(IEndPoint.AddEmp)
			.then()
				.spec(specResponseObj)
				.assertThat().statusCode(500)
				.log().all();
	}


}
