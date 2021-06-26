package resources;

public enum APIResourses {

	//Rahul-Shetty-Academy-Demo-Google-Maps_API 
	addPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json"),
	updatePlaceAPI("/maps/api/place/update/json");
	
	
	
	private String resource;
	
	APIResourses(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
