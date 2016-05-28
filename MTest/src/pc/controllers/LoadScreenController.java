package pc.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class LoadScreenController {
	private MainController mainController;
	private Stage fileChooserStage = new Stage();
	private static List<String> lista = new ArrayList<String>();
	@FXML
	private Label filePathLabel;
	@FXML
	private Label labelNoQuestions;
	@FXML
	public void onMenu(){
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
	
	@FXML
	public void chooseFile(){
		labelNoQuestions.setText("");
		try{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Wybierz Plik");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Plik Tekstowy", "*.txt"));
		File selectedFile = fileChooser.showOpenDialog(fileChooserStage);
		filePathLabel.setText(selectedFile.getPath());
		lista.removeAll(lista);
		File testFile2 = new File(filePathLabel.getText());
		Scanner skaner = null;
		try {
			skaner = new Scanner(testFile2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while(skaner.hasNextLine()){
			String test;
			test=skaner.nextLine();
			if(test.isEmpty()==false)lista.add(test);
		}
		}
		catch(Exception e){
			System.out.println("Wyjatek");
		}
	}
	
	
	
	
	@FXML
	public void startTest(){
		if(lista.isEmpty()==false){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/Application.fxml"));
		Pane pane = null;
		try {
			pane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ApplicationController applicationController = loader.getController();
		applicationController.setMainController(mainController);
		mainController.setScreen(pane);
		}
		else labelNoQuestions.setText("NIE WYBRANO PLIKU!");
	}
	
	public static  List<String> getLista() {
		return lista;
	}




	public void setMainController(MainController mainController){
		this.mainController=mainController;
	}
}
