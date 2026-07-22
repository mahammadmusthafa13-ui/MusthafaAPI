package stepDefination;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import baseUtils.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class Hooks 
{
	public Response response;
	@Before(order=0)
	public void requestSpecification() throws IOException
	{
		
		
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		BaseClass.req=new RequestSpecBuilder().setBaseUri(BaseClass.getPropertyValue("BaseURL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		
	}
	@Before(order=1)//@Before("@DeletePlace")
	public void beforeDeletePlace() throws IOException
	{
		if(StepDefination.placeid==null)
		{
			StepDefination step=new StepDefination();
			step.perapre_payload_with("mehnaz", "engish", "Gurramguda");
			step.user_call_and_with("AddPlaceAPI", "POST");
			step.verify_place_id_created_maps_to_using_and_with("mehnaz","GetPlaceAPI","GET");
		}
	}
	@After(order=1)//@After("@AddPlace")
	public void afterAddPlace() throws IOException
	{
		if((StepDefination.placeid!=null))
		{
			StepDefination step=new StepDefination();
			step.delete_payload();
			step.delete_place_using_and_with("DeletePlaceAPI","POST");
		}
	}
   @After(order=0)
   	public void afterScenario(Scenario scenario) throws IOException
   	{
	   if(scenario.isFailed())
	   {
		   scenario.attach(response.asPrettyString().getBytes(), "text/plain", "API Response");
	   }
	   
   	}
}
