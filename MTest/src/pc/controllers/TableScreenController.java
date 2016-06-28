package pc.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pc.main.SqliteConnection;
import pc.main.UsersTable;

public class TableScreenController implements Initializable{
	Connection connection;
	PreparedStatement pst = null;
	
	public TableScreenController() {
		connection = SqliteConnection.Connector();
		if(connection==null)System.exit(1);
		// TODO Auto-generated constructor stub
	}
	@FXML
	private TextField usernameTD;
	@FXML
	private Label cantDelete;
	//DEFINE TABLE
	@FXML
	TableView<UsersTable> tableID;
	@FXML
	TableColumn<UsersTable, Integer> iID;
	@FXML
	TableColumn<UsersTable, String> iName;
	@FXML
	TableColumn<UsersTable, String> iSurname;
	@FXML
	TableColumn<UsersTable, String> iAge;
	@FXML
	TableColumn<UsersTable, String> iUsername;
	@FXML
	TableColumn<UsersTable, String> iPassword;
	
	//DEFINE VARIABLES
	private Integer iNumber = 1;
	
	final ObservableList<UsersTable> dataUsers = FXCollections.observableArrayList(	);
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		iID.setCellValueFactory(new PropertyValueFactory<UsersTable,Integer>("rID"));
		iName.setCellValueFactory(new PropertyValueFactory<UsersTable,String>("rName"));
		iSurname.setCellValueFactory(new PropertyValueFactory<UsersTable,String>("rSurname"));
		iAge.setCellValueFactory(new PropertyValueFactory<UsersTable,String>("rAge"));
		iUsername.setCellValueFactory(new PropertyValueFactory<UsersTable,String>("rUsername"));
		iPassword.setCellValueFactory(new PropertyValueFactory<UsersTable,String>("rPassword"));
		
		try {
			readDB(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableID.setItems(dataUsers);
	}

	@FXML
	public void deleteUser(){
		try{
            
            String query = "DELETE FROM User WHERE id =? ";
            String query2 = "DELETE FROM Scores WHERE id =?";
                pst = connection.prepareStatement(query);
                pst.setString(1,usernameTD.getText());
                cantDelete.setText("");

                if(!(usernameTD.getText().equals("1"))) {
                	pst.execute();
                	pst = connection.prepareStatement(query2);
                	pst.setString(1, usernameTD.getText());
                	pst.execute();
                }
                else cantDelete.setText("Nie mo¿na usun¹æ");
                pst.close();
                usernameTD.clear();
                dataUsers.removeAll(dataUsers);
                readDB(1);
                
            } catch (Exception e6) {
                System.out.println(e6);
                System.err.println(e6);
            }
           
	}
	
	
	public void readDB(int ID) throws SQLException{
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		String query = "Select * from User";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				dataUsers.add(new UsersTable(resultSet.getInt(ID), resultSet.getString("name"),
						resultSet.getString("surname"), resultSet.getString("age"), 
						resultSet.getString("username"), resultSet.getString("password")));
			}
			
		} catch (Exception e) {
			
			// TODO: handle exception
		}finally{
			preparedStatement.close();
			resultSet.close();
		}
	}
	
	
}
