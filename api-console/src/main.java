import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class main {

	
	
	public static void main (String args[]) {
	
	try {
	URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=57f4696d8c28484f849bc09108670849");
	HttpURLConnection request = (HttpURLConnection)url.openConnection();
    request.connect();    
    
    JsonParser jp = new JsonParser(); //from gson
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json elemen
    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
    
    JsonArray jsonArr = rootobj.getAsJsonArray("articles");
   System.out.println(jsonArr);
   
   ArrayList<News> thenews=new ArrayList<News>();
   
   String title, description, theurl;
   DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
   Date date;
   int n=jsonArr.size();
   for(int i=0;i<n;i++) {
	   title=((JsonObject)jsonArr.get(i)).get("title").getAsString();
	   description=((JsonObject)jsonArr.get(i)).get("description").getAsString();
	   theurl=((JsonObject)jsonArr.get(i)).get("url").getAsString();
	   date=format.parse(((JsonObject)jsonArr.get(i)).get("publishedAt").getAsString());
	   long unixtime= date.getTime()/1000;
	   int timefordb = (int) unixtime;
	
	   thenews.add(new News(title,description,theurl,timefordb));
	   
	   System.out.println(thenews.get(i));
   }
   
	}catch(MalformedURLException e) {
		System.err.println("MalformedURLException: " + e.getMessage());
	}
	catch(IOException e) {
		System.err.println("IOException: " + e.getMessage());
	}
	catch(ParseException e) {
		System.err.println("IOException: " + e.getMessage());
	}
	}
}
