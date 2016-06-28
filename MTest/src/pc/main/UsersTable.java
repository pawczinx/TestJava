package pc.main;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsersTable {

	private  final SimpleIntegerProperty rID ;
	private  final SimpleStringProperty rName ;
	private  final SimpleStringProperty rSurname;
	private  final SimpleStringProperty rAge;
	private  final SimpleStringProperty rUsername;
	private  final SimpleStringProperty rPassword;
	
	public UsersTable(Integer sID,String sName,String sSurname, String sAge, String sUsername,String sPassword)
	{
		this.rID = new SimpleIntegerProperty(sID);
		this.rName = new SimpleStringProperty(sName);
		this.rSurname = new SimpleStringProperty(sSurname);
		this.rAge = new SimpleStringProperty(sAge);
		this.rUsername = new SimpleStringProperty(sUsername);
		this.rPassword = new SimpleStringProperty(sPassword);
	}
	
	public Integer getRID(){
		return rID.get();
	}
	
	public void setRID(Integer v){
		rID.set(v);
	}
	
	public String getRName(){
		return rName.get();
	}
	
	public void setRName(String v){
		rName.set(v);
	}
	
	public String getRSurname(){
		return rSurname.get();
	}
	
	public void setRSurname(String v){
		rSurname.set(v);
	}
	
	public String getRAge(){
		return rAge.get();
	}
	
	public void setRAge(String v){
		rAge.set(v);
	}
	
	public String getRUsername(){
		return rUsername.get();
	}
	
	public void setRUsername(String v){
		rUsername.set(v);
	}
	
	public String getRPassword(){
		return rPassword.get();
	}
	
	public void setRPassword(String v){
		rPassword.set(v);
	}
}
