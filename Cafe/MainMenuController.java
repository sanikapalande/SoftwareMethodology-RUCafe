package Cafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Defines a user interface class Main Menu Controller for interacting with the users at the front end.
 * Deals with I/O and is a client which allows the user to navigate through various pages using a JavaFX GUI.
 * @author Jared Chiou, Sanika Palande
 */
public class MainMenuController
{
    @FXML
    Button orderDonuts, orderCoffee, myOrders, storeOrders;

    /**
     * Handles event when Order Donuts button is clicked.
     * @param event on clicking Order Donuts button, triggers opening of Ordering Donuts window
     */
    @FXML
    void handleButtonActionDonut(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingDonuts.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ordering Donuts Window");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Cannot open Ordering Donuts window.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Handles event when Order Donuts button is clicked.
     * @param event on clicking Order Coffee button, triggers opening of Ordering Coffee window
     */
    @FXML
    void handleButtonActionCoffee(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingCoffee.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Ordering Coffee Window");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Cannot open Ordering Coffee Window.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Handles event when myOrders button is clicked.
     * @param event on clicking My Order button, triggers opening of My Orders window
     */
    @FXML
    void handleButtonActionMyOrders(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyOrders.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("My Orders Window");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Cannot open My Orders window.");
            alert.showAndWait();
            return;
        }
    }

    /**
     * Handles event when Order Donuts button is clicked.
     * @param event on clicking Store Orders button, triggers opening of Store Orders window
     */
    @FXML
    void handleButtonActionStoreOrders(ActionEvent event)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreOrders.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Store Orders Window");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        
        catch(Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Cannot open Store Orders window.");
            alert.showAndWait();
            return;
        }
    }


}

