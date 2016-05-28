package pc.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class FinishScreenController {
@FXML
private Label finalScore;
private ApplicationController applicationController;
private MainController mainController;


@FXML
private TextArea tekscik;

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


@SuppressWarnings("static-access")
@FXML
public void initialize(){
	int x= applicationController.getcountQuestions();
	finalScore.setText(applicationController.getPoints()+"/"+x+"");
	tekscik.setWrapText(true);
	tekscik.setText("");
	writeWrongAnswers();
	tekscik.setWrapText(true);
	tekscik.setEditable(false);
}

public void writeWrongAnswers(){
	while(applicationController.getWrongAnswers().isEmpty()==false){
	for(int i=0;i<5;i++){
		tekscik.setText(tekscik.getText()+applicationController.getWrongAnswers().get(i)+"\n");
	}
	for(int i=0;i<6;i++){
		applicationController.getWrongAnswers().remove(0);
		}
	
	tekscik.setText(tekscik.getText()+"\n");
	}
	
}

public void setMainController(MainController mainController){
	this.mainController=mainController;
}
}
