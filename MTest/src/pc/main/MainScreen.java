package pc.main;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainScreen extends Application{

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/fxml/MainScreen.fxml"));
		StackPane stackPane = loader.load();
		Scene scene = new Scene(stackPane);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JAVA TEST");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
