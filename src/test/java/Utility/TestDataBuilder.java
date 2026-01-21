package Utility;

import java.util.ArrayList;
import java.util.List;

import pojoClasses.Location1;
import pojoClasses.AddPlace;
import pojoClasses.DeletePlace;

public class TestDataBuilder 
{
	
	public AddPlace AddPlaceDataBuilder(String name, String language, String address)
	{
		AddPlace addplace=new AddPlace();
		addplace.setAccuracy(50);
		addplace.setAddress(address);;
		addplace.setLanguage(language);
		addplace.setName(name);
		addplace.setPhone_number("(+91)9838933937");
		addplace.setWebsite("http://google.com");
		
		Location1 lc=new Location1();
		lc.setLat(-38.383494);
		lc.setLng(33.427362);
		addplace.setLocation(lc);
		
		List<String>Types=new ArrayList<>();
		Types.add("shoe park");
		Types.add("shop");
		addplace.setTypes(Types);
		
		return addplace;
		
	}
	
	public DeletePlace deletePlaceDataBuilder(String placeid)
	{
		DeletePlace deleteplace =new DeletePlace();
		deleteplace.setPlaceid(placeid);
		return deleteplace;
	}

	public String deletePlaceBody(String placeid)
	{
	return "{\r\n"
			+ "    \"place_id\":\""+placeid+"\"\r\n"
			+ "}";
	}

}
