package pc.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class MenuController {
	private MainController mainController;

	
	
	
	@FXML
	public void onStart(){
		loadLoadScreen();
	}
	
	public void loadLoadScreen(){
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/LoadScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LoadScreenController loadQuestionsController = loader.getController();
		loadQuestionsController.setMainController(mainController);
		mainController.setScreen(pane);
	}
	
	
	@FXML
	public void onOptions(){
		
	}
	
	@FXML
	public void onExit(){
		Platform.exit();
	}
	
	@FXML
	public void logout(){
		mainController.loadLoginScreen();
	}	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
