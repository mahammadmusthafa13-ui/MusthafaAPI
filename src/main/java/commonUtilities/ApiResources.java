package commonUtilities;

public enum ApiResources 
{
	
	
	 AddPlaceAPI("/maps/api/place/add/json"),
	 GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	
	private String resource;
	ApiResources(String resource) {
		this.resource=resource;
	}
	
	public String getHTTPMethod()
	{
		return resource;
	}
}
