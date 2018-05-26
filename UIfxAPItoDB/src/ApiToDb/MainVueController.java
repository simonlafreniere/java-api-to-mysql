package ApiToDb;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainVueController implements Initializable{	
	
	@FXML
		private Button btn_connect;
	
	@FXML
		private Button btn_loadAPI;
	
	@FXML
		private Button btn_insert;
	
	@FXML
		private Button btn_show;
	  
	 @FXML
	 	private TextArea txt_show;
	
	 private dbInterface mydb;
	 private ArrayList<News> thenews;
	 
	 @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
		 mydb=new dbInterface();
		 thenews=new ArrayList<News>();
	       // TODO (don't really need to do anything here).
	      
	   }
	 
	 //events
	 //connection à la bd
	 public void btn_connect_click(ActionEvent event) {
		 try{
	            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VueConnection.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            
	            ConnectionVueController controller = fxmlLoader.<ConnectionVueController>getController();
	            controller.setDB(mydb);
	            
	            Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);
	            stage.setTitle("Connection to db");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	          }
		 catch(Exception e) {
	  			e.printStackTrace();
	  		}
	 }
	 
	 //charger le json dans une liste d'objet news
	 public void btn_loadAPI_click(ActionEvent event) {
		 thenews = apiInterface.getJson("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=57f4696d8c28484f849bc09108670849");
		 if(!thenews.isEmpty()) {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("chargement reussi");
				alert.setHeaderText("succès du chagement du json");
				alert.setContentText("woohoo");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }
		 else {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("échec de chargement");
				alert.setHeaderText("échec du chagement du json");
				alert.setContentText("réessayer plus tard..");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }
	 }
	 
	 public void btn_insert_click(ActionEvent event) {
		 if(mydb.isConnected()) {
		 if(!thenews.isEmpty()) {
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
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("insertion reussi");
				alert.setHeaderText("succès de l'insertion");
				alert.setContentText("woohoo");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }
		 else {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("insertion annulée");
				alert.setHeaderText("aucune donnée");
				alert.setContentText("chargez l'api d'abord..");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }}
		 else {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("insertion annulée");
				alert.setHeaderText("db non connectée");
				alert.setContentText("connectez vous d'abord..");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }
	 }
	 public void btn_show_click(ActionEvent event) {
		 if(thenews.isEmpty()) {
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("aucune données");
				alert.setHeaderText("le json est vide");
				alert.setContentText("chargez l'api d'abord");
				alert.showAndWait().ifPresent(rs -> {
				    if (rs == ButtonType.OK) {
				        System.out.println("Pressed OK.");
				    }
				});
		 }
		 else {
			 String result="";
			 for(News news:thenews) {
				 result+=news+"\n";
			 }
			 
			 txt_show.setText(result);		
		 }
	 }
	
}
