package com.ninza.hrm.api.genericUtility;

import java.util.List;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtility 
{
	

public String getDataOnJsonPath(Response resp,String jsonPath)
{
	List<Object> list = JsonPath.read(resp.asString(),jsonPath);
	return list.get(0).toString();
}

public Object getDataOnXPath(Response resp,String xmlPath)
{
	return resp.xmlPath().get(xmlPath);
}

public boolean verifyDataOnJsonPath(Response resp,String jsonPath,String expectedData)
{
	List<String> list = JsonPath.read(resp.asString(),jsonPath);
	boolean flag=false;
	for(String str : list)
	{
		if(str.equals(expectedData))
		{
			System.out.println(expectedData + " is available==pass");
			flag=true;
		}
	}
	if(flag==false)
	{
		System.out.println(expectedData+" is not availbale==failed");
	}
	return flag;
}

public String getOauth2AccessToken()
{
		Response resp = given()
			.formParam("client_id", "ninza-client")
			.formParam("client_secret", "gPQBf1Yxew5OMccMhzos1GefIyiSnXzM")
			.formParam("grant_type", "client_credentials")
			.when()
				.post("http://49.249.28.218:8180/auth/realms/ninza/protocol/openid-connect/token");
		
		String token = resp.jsonPath().get("access_token");
		return token;
}

}
