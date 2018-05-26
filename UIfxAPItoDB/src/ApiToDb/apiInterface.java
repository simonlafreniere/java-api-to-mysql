package ApiToDb;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class apiInterface {

	public static ArrayList<News> getJson(String myurl) {
		ArrayList<News> thenews=new ArrayList<News>();
		try {
			URL url = new URL(myurl);
			HttpURLConnection request = (HttpURLConnection)url.openConnection();
		    request.connect();    
		    
		    JsonParser jp = new JsonParser(); //from gson
		    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json elemen
		    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 
		    
		    JsonArray jsonArr = rootobj.getAsJsonArray("articles");
		   System.out.println(jsonArr);
		   
		   String title, description, theurl;
		   DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		   Date date;
		   long unixtime;
		   int timefordb;
		   int n=jsonArr.size();
		   for(int i=0;i<n;i++) {
			   try {
			   title=((JsonObject)jsonArr.get(i)).get("title").getAsString();}
			   catch(java.lang.UnsupportedOperationException e) { //catch null exception
				   title="no title";
				   //System.out.println(e);
			   }
			   try {
			   description=((JsonObject)jsonArr.get(i)).get("description").getAsString();}
			   catch(java.lang.UnsupportedOperationException e) {
				   description="";
				   //System.out.println(e);
			   }
			   try {
			   theurl=((JsonObject)jsonArr.get(i)).get("url").getAsString();}
			   catch(java.lang.UnsupportedOperationException e) {
				   theurl="";
				   //System.out.println(e);
			   }
			   try {
			   date=format.parse(((JsonObject)jsonArr.get(i)).get("publishedAt").getAsString());
			   unixtime= date.getTime()/1000;
			   timefordb = (int) unixtime;}
			   catch(java.lang.UnsupportedOperationException e) {
				   timefordb=0;
				   //System.out.println(e);
			   }
			
			   thenews.add(new News(title,description,theurl,timefordb));
			   
			   //System.out.println(thenews.get(i));
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
			finally {
				return thenews;
			}
	}
}