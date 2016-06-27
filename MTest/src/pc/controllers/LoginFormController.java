package pc.controllers;

import java.io.IOException;

import pc.main.SqliteConnection;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginFormController {
		Connection connection;
		private MainController mainController;
		private String password = "zgadnij";
		@FXML
		private TextField loginLabel;
		@FXML
		private Label labelInfo;
		@FXML
		private PasswordField passwordLabel;
		
public LoginFormController() {
	connection = SqliteConnection.Connector();
	if(connection==null)System.exit(1);
}


public boolean isDBConnected(){
	
	try {
		return !connection.isClosed();
	} catch (Exception e) {
		return false;
		// TODO: handle exception
	}
}

public boolean isLogin(String user, String pass) throws SQLException{
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	String query = "Select * from User where username = ? and password = ?";
	
	try {
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, user);
		preparedStatement.setString(2, pass);
		
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()){
			return true;
		}
		else{
			return false;
		}
		
	} catch (Exception e) {
		return false;
		// TODO: handle exception
	}finally{
		preparedStatement.close();
		resultSet.close();
	}
}


	@FXML
	public void initialize(){
		if(isDBConnected()){
			labelInfo.setText("Po³¹czono z Baz¹ danych");
		}
		else{
			labelInfo.setText("Nie po³¹czono");
		}
	}
	
	@FXML
	public void load(){
		labelInfo.setText("Wciœniêto Wczytaj");
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
	public void Login (ActionEvent event){
		try {
			if(isLogin(loginLabel.getText(), passwordLabel.getText())){
				if(loginLabel.getText().equals("admin"))labelInfo.setText("admin");
				else {
					labelInfo.setText("zwykly user");
					loadMenu();
//					((Node)event.getSource()).getScene().getWindow().hide();
//					Stage primaryStage = new Stage();
//					FXMLLoader loader = new FXMLLoader();
//					Pane root = loader.load(getClass().getResource("/application/User.fxml").openStream());
//					UserController userController = (UserController)loader.getController();
//					userController.getUser(txtUsername.getText());
//					Scene scene = new Scene(root);
//					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//					primaryStage.setScene(scene);
//					primaryStage.show();
					
					
				}
			}
			else{
				labelInfo.setText("Nie udalo sie");
			}
		} catch (SQLException e) {
			labelInfo.setText("Nie udalo sie");
			// TODO Auto-generated catch block
			e.printStackTrace();
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
