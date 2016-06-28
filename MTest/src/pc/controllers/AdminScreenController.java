package pc.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pc.main.SqliteConnection;

public class AdminScreenController implements Initializable{
	Connection connection;
	PreparedStatement pst = null;
	public AdminScreenController() {
		connection = SqliteConnection.Connector();
		if(connection==null)System.exit(1);
		// TODO Auto-generated constructor stub
	}
	@FXML
	private Label adminLbl;
	
	@FXML
	private TextField fid;
	@FXML
	private TextField fname;
	@FXML
	private TextField fsurname;
	@FXML
	private TextField fage;
	@FXML
	private TextField fusername;
	@FXML
	private PasswordField fpassword;
	
	@FXML
	public void onTableShow() throws IOException{
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/fxml/TableScreen.fxml").openStream());
		TableScreenController tableScreenController = (TableScreenController)loader.getController();
		Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("/pc/main/application.css").toExternalForm());
	primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	@FXML
	public void onAddElements(){
		try{
            
            String queryu = "INSERT INTO User (id,name,surname,age,username,password) VALUES(?,?,?,?,?,?)";
            String query2 = "INSERT INTO Scores(id)VALUES(?)";
                pst = connection.prepareStatement(queryu);
                
                pst.setString(1, fid.getText());
                pst.setString(2, fname.getText());
                pst.setString(3, fsurname.getText());
                pst.setString(4, fage.getText());
                pst.setString(5, fusername.getText());
                pst.setString(6, fpassword.getText());
                pst.execute();
                pst = connection.prepareStatement(query2);
                pst.setString(1, fid.getText());
                pst.execute();
                fid.clear();
                fname.clear();
                fsurname.clear();
                fage.clear();
                fusername.clear();
                fpassword.clear();
                pst.close();
                
                
            } catch (Exception e5) {
                System.out.println(e5);
                System.err.println(e5);
            }
        }    
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void getUser(String user) {
		adminLbl.setText(user);
		
	}
	
	public void signOut(ActionEvent event) {
		try {
			((Node)event.getSource()).getScene().getWindow().hide();
//			Stage primaryStage=new Stage();
//			main.start(primaryStage);
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/fxml/MainScreen.fxml").openStream());
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
public boolean isDBConnected(){
		
		try {
			return !connection.isClosed();
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
	}

}
