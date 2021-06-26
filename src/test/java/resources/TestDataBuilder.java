package resources;


import java.util.ArrayList;

import pojo.AddPlace;

public class TestDataBuilder {

	public AddPlace addPlacePayload(String name, String phone_number, String address, String language)
	{
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setName(name);
		ap.setPhone_number(phone_number);
		ap.setAddress(address);
		ap.setWebsite("http://samplewebsite.com");
		ap.setLanguage(language);
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("Near to Indian Bank");
		myList.add("Bangalore");
		ap.setTypes(myList);
		
		pojo.Location l = new pojo.Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		return ap;
	}
	
	public String deletePlacePayload(String placeID)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+placeID+"\"\r\n"
				+ "}";
	}
	
	public String addBookPayload(String Name, String ISBN, String AISLE, String AuthorName)
	{
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\""+Name+"\",\r\n"
				+ "\"isbn\":\""+ISBN+"\",\r\n"
				+ "\"aisle\":\""+AISLE+"\",\r\n"
				+ "\"author\":\""+AuthorName+"\"\r\n"
				+ "}";
	}
	
	public String deleteBookPayload(String BookID)
	{
		return "{\r\n"
				+ "\"ID\" : \""+BookID+"\"\r\n"
				+ "}";
	}
}
