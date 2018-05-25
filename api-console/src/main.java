import java.sql.SQLException;
import java.util.ArrayList;

public class main {

	
	
	public static void main (String args[]) {
	
		
		
		String url = "jdbc:mysql://localhost:3306/mynews";
		String username = "";
		String password = "";
		dbInterface mydb=new dbInterface(url,username,password);
		//mydb.connect();
		
		ArrayList<News> thenews = apiInterface.getJson("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=57f4696d8c28484f849bc09108670849");
		
		for(News news:thenews) {
			try {
				mydb.insert(news);
				//break;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//break;
			}
		}
		
		//mydb.disconnect();
		
		System.out.println("fin");
		
	}
	
}
