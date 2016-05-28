package pc.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;

public class ApplicationController {
private MainController mainController;

private static double progressBarCount=0.0;
@FXML
private ProgressBar progressBar;
private static List<String> wrongAnswers = new ArrayList<>();
private List<String> pytania = LoadScreenController.getLista();
@FXML
private RadioButton radioA,radioB,radioC,radioD;
@FXML
private Label labelInfoAnswer;
@FXML
private Label qNumber, answerA,answerB, answerC, answerD, correctAnswer;


@FXML
private Button nextButton,saveButton;
private static int points=0,countQuestions=0,countNumberOfQuestions=0;
private static Boolean ifCorrect=false;
@FXML
public void saveAnswer(){
	checkIfTrueAnswer();
	if(radioA.isSelected()||radioB.isSelected()||radioC.isSelected()||radioD.isSelected()){
		if(pytania.isEmpty()==true)nextButton.setText("ZakoÒcz");
		if(ifCorrect==true){
			borderCorrectAnswer();
			points+=1;	
		}
		else{
			getWrongQuestion();
			borderCorrectAnswer();
			borderSelectedRadio();
		}
		
		radioA.setDisable(true);radioB.setDisable(true);radioC.setDisable(true);radioD.setDisable(true);
		setProgressBar();
	++countQuestions;
	//System.out.println(checkIfTrueAnswer());
	//System.out.println(points);
	saveButton.setDisable(true);
	nextButton.setDisable(false);
	}
	else labelInfoAnswer.setText("WYBIERZ ODPOWIEDè!");
}

public static List<String> getWrongAnswers() {
	return wrongAnswers;
}

public void getWrongQuestion(){
	wrongAnswers.add(qNumber.getText());
	wrongAnswers.add(answerA.getText());
	wrongAnswers.add(answerB.getText());
	wrongAnswers.add(answerC.getText());
	wrongAnswers.add(answerD.getText());
	wrongAnswers.add(correctAnswer.getText());
}

@FXML
public void nextQuestion(){
	unborderCorrectAnswer();
	loadQuestions();
	saveButton.setDisable(false);
	nextButton.setDisable(true);
	radioA.setDisable(false);radioB.setDisable(false);radioC.setDisable(false);radioD.setDisable(false);
	if(nextButton.getText().equals("ZakoÒcz"))loadFinishScreen();
}


public Boolean checkIfTrueAnswer(){
	ifCorrect=false;
	if(correctAnswer.getText().equals("A")&&radioA.isSelected())ifCorrect=true;
	if(correctAnswer.getText().equals("B")&&radioB.isSelected())ifCorrect=true;
	if(correctAnswer.getText().equals("C")&&radioC.isSelected())ifCorrect=true;
	if(correctAnswer.getText().equals("D")&&radioD.isSelected())ifCorrect=true;
	return ifCorrect;
}

public void borderCorrectAnswer(){
	if(correctAnswer.getText().equals("A"))answerA.setStyle("-fx-border-color: green;");
	if(correctAnswer.getText().equals("B"))answerB.setStyle("-fx-border-color: green;");
	if(correctAnswer.getText().equals("C"))answerC.setStyle("-fx-border-color: green;");
	if(correctAnswer.getText().equals("D"))answerD.setStyle("-fx-border-color: green;");
}

public void unborderCorrectAnswer(){
	answerA.setStyle("-fx-border-color: LIGHTGRAY;");
	answerB.setStyle("-fx-border-color: LIGHTGRAY;");
	answerC.setStyle("-fx-border-color: LIGHTGRAY;");
	answerD.setStyle("-fx-border-color: LIGHTGRAY;");
}

public void borderSelectedRadio(){
	if(radioA.isSelected())answerA.setStyle("-fx-border-color: RED");
	if(radioB.isSelected())answerB.setStyle("-fx-border-color: RED");
	if(radioC.isSelected())answerC.setStyle("-fx-border-color: RED");
	if(radioD.isSelected())answerD.setStyle("-fx-border-color: RED");
}


public void loadFinishScreen(){
	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/FinishScreen.fxml"));
	Pane pane = null;
	try {
		pane = loader.load();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	FinishScreenController finishScreenController = loader.getController();
	finishScreenController.setMainController(mainController);
	mainController.setScreen(pane);
}


public void loadQuestions(){
	if(pytania.isEmpty()==false){
		qNumber.setText(pytania.get(0));
		pytania.remove(0);
		answerA.setText(pytania.get(0));
		pytania.remove(0);
		answerB.setText(pytania.get(0));
		pytania.remove(0);
		answerC.setText(pytania.get(0));
		pytania.remove(0);
		answerD.setText(pytania.get(0));
		pytania.remove(0);
		correctAnswer.setText(pytania.get(0));
		pytania.remove(0);
		qNumber.setWrapText(true);
		answerA.setWrapText(true);
		answerB.setWrapText(true);
		answerC.setWrapText(true);
		answerD.setWrapText(true);
		radioA.setSelected(false);
		radioB.setSelected(false);
		radioC.setSelected(false);
		radioD.setSelected(false);
	}
	else nextButton.setText("ZakoÒcz");
}

@FXML 
public void initialize(){
	countNumberOfQuestions=(pytania.size()/6);//rozmiar tablicy pytan i obliczenie iloúci pytaÒ
	progressBarCount=0.0;
	loadQuestions();
	nextButton.setDisable(true);
	correctAnswer.setVisible(false);
}

public void setProgressBar() {
	progressBarCount+=1;
	progressBar.setProgress(progressBarCount/countNumberOfQuestions);
}
@FXML
public void isSelectedA(){
	labelInfoAnswer.setText("");
radioB.setSelected(false);radioC.setSelected(false);radioD.setSelected(false);	
}

@FXML
public void isSelectedB(){
	labelInfoAnswer.setText("");
	radioA.setSelected(false);radioC.setSelected(false);radioD.setSelected(false);
}
@FXML
public void isSelectedC(){
	labelInfoAnswer.setText("");
	radioB.setSelected(false);radioA.setSelected(false);radioD.setSelected(false);
}
@FXML
public void isSelectedD(){
	labelInfoAnswer.setText("");
	radioB.setSelected(false);radioC.setSelected(false);radioA.setSelected(false);
}

public static int getPoints() {
	return points;
}
public static int getcountQuestions() {
	return countQuestions;
}

public void setMainController(MainController mainController){
	this.mainController=mainController;
}

}
