package Cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that starts the ordering system for RUCafe
 * @author Jared Chiou, Sanika Palande
 */
public class Main extends Application 
{
	/**
	 * Start method that starts the ordering system GUI for RUCafe
	 * @param primaryStage - the stage the GUI runs on
	 */
	@Override
	public void start(Stage primaryStage) 
	{
		try 
		{
			Parent root = FXMLLoader.load(getClass().getResource("/Cafe/MainMenu.fxml"));
			primaryStage.setTitle("RU Cafe");
			primaryStage.setScene(new Scene(root, 600, 450));
			primaryStage.show();
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Main method of the driver class which starts the Application
	 * @param args - command line arguments (not necessary)
	 */
	public static void main(String[] args) 
	{
		launch(args);
	}
}