package stepDefination;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import Utility.ApiResources;
import Utility.BaseClass;
import Utility.TestDataBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class StepDefination extends BaseClass
{
	RequestSpecification reqSpec;
	Response response;
	public static String placeid;
	TestDataBuilder data= new TestDataBuilder();
	
	@Given("Perapre payload with {string} {string} {string}")
	public void perapre_payload_with(String name, String language, String address) throws IOException {
		//TestDataBuilder data= new TestDataBuilder();
		reqSpec=given().spec(requestSpecification()).body(data.AddPlaceDataBuilder(name, language, address));  
	}


	@When("User Call {string} and with {string}")
	public void user_call_and_with(String AddPlaceAPI, String httpMethod) {
	
		ApiResources resources=ApiResources.valueOf(AddPlaceAPI);
		if(httpMethod.equalsIgnoreCase("POST")) {
		 response=reqSpec.when().post(resources.getHTTPMethod());
		}
		else if(httpMethod.equalsIgnoreCase("GET"))
		{
			 response=reqSpec.when().get(resources.getHTTPMethod());	
		}
	}

	@Then("the API call got success with status code {string}")
	public void the_api_call_got_success_with_status_code(String status) {
		
		int statcode=response.getStatusCode();
		assertEquals(statcode, 200);

	}

	@And("{string} in response is {string}")
	public void in_response_is(String status, String OK) {
		
	String statuskey=getJsonPathValue(response, status);
	assertEquals(statuskey,OK);
	
	}
	
	@And("{string} in response body is {string}")
	public void in_response_body_is(String scope, String app) {
		String scopekey=getJsonPathValue(response, scope);
		assertEquals(scopekey,app);
	    
	}
	@And("verify place_Id created maps to {string} using {string} and with {string}")
	public void verify_place_id_created_maps_to_using_and_with(String name, String getPlaceAPI, String HttpMethod) throws IOException {
		 placeid=getJsonPathValue(response, "place_id");
		System.out.println(response.asString());
		reqSpec=given().spec(requestSpecification()).queryParam("place_id", placeid);
		user_call_and_with(getPlaceAPI,HttpMethod);
		System.out.println("response1 :   "+response.asString());
		String namekey=getJsonPathValue(response, "name");
		assertEquals(name, namekey);
	}
	@Given("Delete Payload")
	public void delete_payload() throws IOException {
		reqSpec=given().spec(requestSpecification()).body(data.deletePlaceBody(placeid));
	    
	}
	@When("Delete place using {string} and with {string}")
	public void delete_place_using_and_with(String DeletePlaceAPI, String POST)  {
		user_call_and_with(DeletePlaceAPI,POST);
		
	}



}
