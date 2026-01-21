package stepDefination;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks 
{
	@Before("@DeletePlace")
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
	@After("@AddPlace")
	public void afterAddPlace() throws IOException
	{
		if((StepDefination.placeid!=null))
		{
			StepDefination step=new StepDefination();
			step.delete_payload();
			step.delete_place_using_and_with("DeletePlaceAPI","POST");
		}
	}

}
