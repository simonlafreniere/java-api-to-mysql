package ApiToDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class dbInterface {
	private Connection connection;
	private String url,	username, password;
	private boolean connected;
	
	public dbInterface() {
		connected=false;
	}
	
	public void setConnectionString(String url, String username, String password) {
		this.url=url;
		this.username=username;
		this.password=password;
	}
	
	public void connect() throws IllegalStateException{ 
	try {
		connection = DriverManager.getConnection(url, username, password);
	    System.out.println("Database connected!");
	} catch (SQLException e) {
	    throw new IllegalStateException("Cannot connect the database!", e);
	}
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setConnected(boolean state) {
		connected=state;
	}
	public boolean isConnected() {
		return connected;
	}
	
	public void insert(News news) throws SQLException {
		connect();
		String queryString = "insert into news (id_news, title, description, url, publishedAt) VALUES (NULL, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = connection.prepareStatement(queryString);
		  preparedStmt.setString (1, news.getTitle());
	      preparedStmt.setString (2, news.getDescription());
	      preparedStmt.setString (3, news.getUrl());
	      preparedStmt.setInt (4, news.getPublishedAt());
	      
	      // execute the preparedstatement
	      preparedStmt.execute();
		disconnect();
	}
}