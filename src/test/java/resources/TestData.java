package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.LocationList;

public class TestData {
	
	public AddPlacePojo addplacetestdata(String Name, String Phone_Number, String Address, String Website, String Language) {
		
		AddPlacePojo ap = new AddPlacePojo();
		
		ap.setAccuracy(50);
		ap.setName(Name);
		ap.setPhone_number(Phone_Number);
		ap.setAddress(Address);
		ap.setWebsite(Website);
		ap.setLanguage(Language);

		LocationList l = new LocationList();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		ap.setLocation(l);

		List<String> l2 = new ArrayList<String>();
		l2.add("shoe park");
		l2.add("shop");

		ap.setTypes(l2);
		
		return ap;

	}
	
	public String deletepayload(String place_ID) {

		String deletepayload = "{\r\n"
		 		+ "    \"place_id\":\""+place_ID+"\"\r\n"
		 		+ "}\r\n"
		 		+ "";
		return deletepayload;

	}

}
