package ApiToDb;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MyController implements Initializable{	
	
	@FXML
		private Button btn_connect;
	
	@FXML
		private Button btn_loadAPI;
	
	@FXML
		private Button btn_insert;
	
	@FXML
		private Button btn_show;
	  
	 @FXML
	 	private Label lbl_show;
	
	 @Override
	   public void initialize(URL location, ResourceBundle resources) {
	 
	       // TODO (don't really need to do anything here).
	      
	   }
	 
	 //events
	 public void btn_connect_click(ActionEvent event) {}
	 public void btn_loadAPI_click(ActionEvent event) {}
	 public void btn_insert_click(ActionEvent event) {}
	 public void btn_show_click(ActionEvent event) {}
}
