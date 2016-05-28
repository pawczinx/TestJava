package pc.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginFormController {
		private MainController mainController;
		private String password = "zgadnij";
		@FXML
		private TextField loginLabel;
		@FXML
		private Label labelInfo;
		@FXML
		private PasswordField passwordLabel;
		
	@FXML
	public void initialize(){
		
	}
	
	@FXML
	public void load(){
		labelInfo.setText("Wciœniêto Wczytaj");
		checkPassword();
	}
	
	public void checkPassword(){
		if(passwordLabel.getText().equals(password))loadMenu();
		else if(passwordLabel.getText().isEmpty()){
					labelInfo.setText("Wpisz has³o");
					passwordLabel.clear();
					}
		else {
			labelInfo.setText("B³êdne Has³o");		
			passwordLabel.clear();
		}
		
	}
	
	public void loadMenu(){
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/MenuScreen.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MenuController menuController = loader.getController();
		menuController.setMainController(mainController);
		mainController.setScreen(pane);
	}
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

}
