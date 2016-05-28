package pc.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainController {
	@FXML
	private StackPane mainStackPane;
	
	@FXML
	public void initialize(){
		loadLoginScreen();
		
		
	}

	public void loadLoginScreen() {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoginForm.fxml"));	
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoginFormController loginFormController = loader.getController();
		loginFormController.setMainController(this);
		setScreen(pane);
	}

	public void setScreen(Pane pane) {
		mainStackPane.getChildren().clear();
		mainStackPane.getChildren().add(pane);
	}
	
	

}
