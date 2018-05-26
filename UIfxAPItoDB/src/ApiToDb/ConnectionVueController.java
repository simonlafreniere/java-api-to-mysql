package ApiToDb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectionVueController {

	@FXML
	private Button btn_connect;

	@FXML
	private Button btn_cancel;

	@FXML
	private TextField txt_url;

	@FXML
	private TextField txt_username;
  
	@FXML
	private TextField txt_password;
	
	private dbInterface mydb;
	
	public void btn_cancel_click(ActionEvent event) {
		// get a handle to the stage
	    Stage stage = (Stage) btn_cancel.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	public void btn_connect_click(ActionEvent event) {
		mydb.setConnectionString(txt_url.getText(),txt_username.getText(),txt_password.getText());
		try {
		mydb.connect();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("connection reussi");
		alert.setHeaderText("succès de la connection");
		alert.setContentText("woohoo");
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
		mydb.setConnected(true);
		mydb.disconnect();
		
		// get a handle to the stage
	    Stage stage = (Stage) btn_connect.getScene().getWindow();
	    // do what you have to do
	    stage.close();
		
		}
		catch(IllegalStateException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Erreur de connection");
			alert.setHeaderText("La connection a échouée..");
			alert.setContentText("certaines informations sont peut-être erronnées ou la base de données est indisponible..");
			alert.showAndWait().ifPresent(rs -> {
			    if (rs == ButtonType.OK) {
			        System.out.println("Pressed OK.");
			    }
			});
		}
	}
	
	public void setDB(dbInterface db) {
		mydb=db;
	}
	

}
