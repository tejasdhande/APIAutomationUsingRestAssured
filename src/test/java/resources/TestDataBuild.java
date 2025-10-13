package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddBook;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("+91 4541 5454 4555");
		p.setWebsite("www.kjclajda.com");
		p.setName(name);

		List<String> mylist = new ArrayList<String>();
		mylist.add("Shoe Park");
		mylist.add("Compnay");
		mylist.add("food court");
		mylist.add("mall");

		p.setType(mylist);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		p.setLocation(l);
		
		return p;

	}
	
	public String deletePlacePayload(String placeId){
		
		return "{\r\n\"place_id\": \""+placeId+"\"\r\n}";

	}
	
	public AddBook addBookPayload(String name,String isbn,String aisle,String author) {
		
		AddBook b = new AddBook();
		b.setName(name);
		b.setIsbn(isbn);
		b.setAisle(aisle);
		b.setAuthor(author);
		return b;
		
	}
}
