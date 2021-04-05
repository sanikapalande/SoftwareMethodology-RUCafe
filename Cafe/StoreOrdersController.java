package Cafe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Defines a user interface class Store Order Controller for interacting with the users at the front end.
 * Deals with I/O and is a client which allows the user to navigate through various pages using a JavaFX GUI.
 * Here, the user can select an order and cancel the order. The user can also save/export the store orders
 * to a text file, which shall include the details of every order.
 * @author Jared Chiou, Sanika Palande
 */
public class StoreOrdersController
{
    private ObservableList<String> orderIDList;
    private ObservableList<String> currentOrderItems;

    @FXML
    private ListView<String> thisOrderContentField;

    @FXML
    private ComboBox<String> orderID;

    @FXML
    private TextField orderTotalField, totalField;

    @FXML
    private Button removeThisOrder, exportOrder;

    /**
     * Initializes the Store Order Stage and displays necessary initial fields.
     */
    @FXML
    void initialize()
    {
        //Initialize combo box with number of orders dynamically
        setComboBox();
        displayPrice();

    	orderTotalField.appendText("$0.00");
    }
    
    /**
     * Displays the current total price of the StoreOrder.
     */
    private void displayPrice()
    {
    	totalField.clear();
    	totalField.appendText(String.format("$%,.2f", References.storeOrder.calculateStoreTotal()));
    }

    /**
     * Populates the combobox with the orderID's present in the StoreOrder.
     */
    private void setComboBox()
    {
        orderIDList = FXCollections.observableArrayList();

        // Allow the user to update the items in the list
        for(int i = 0; i < References.storeOrder.size(); i++)
        {
            orderIDList.add(Integer.toString(References.storeOrder.get(i).getOrderId()));
        }
        
        orderID.setItems(orderIDList);
    }

    
    /**
     * Updates the GUI based on the order number selected.
     * @param event - triggered by the GUI when an Order ID is selected from the Combo Box.
     */
    @FXML
    void selectOrderNumber(ActionEvent event) 
    {
    	if(!(orderID.getValue() == null))
    	{
    		int currentOrderID = orderID.getSelectionModel().getSelectedIndex();
    		
    		if(currentOrderID == -1)
    		{
    			return;
    		}
    		
        	setThisOrderContentField(currentOrderID);
    	}
    }

    /**
     * Populates the List View with the current order when an Order ID is selected.
     * @param currentOrderID - the index of the current order.
     */
    private void setThisOrderContentField(int currentOrderID)
    {
    	currentOrderItems = FXCollections.observableArrayList();
    	
    	thisOrderContentField.setItems(currentOrderItems);
    	
    	currentOrderItems.add(References.storeOrder.get(currentOrderID).toString());
    	
    	orderTotalField.clear();
    	orderTotalField.appendText(String.format("$%,.2f", References.storeOrder.get(currentOrderID).calculateTotal()));

    }

    /**
     * Removes the current order from list of orders.
     * @param event - triggered by the GUI when order is selected and remove button is clicked
     */
    @FXML
    void selectRemoveThisOrder(ActionEvent event)
    {
    	 if(orderID.getValue() == null)
         {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error Dialog");
             alert.setContentText("Nothing has been selected, cannot remove.");
             alert.showAndWait();
             return;
         }

    	 clear();
    }

    /**
     * Exports the current order.
     * @param event - triggered by the GUI when Export Order button is clicked.
     */
    @FXML
    void selectExportThisOrder(ActionEvent event)
    {
        //if there are no orders in store order
        if(References.storeOrder.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("No orders to export.");
            alert.showAndWait();
            return;
        }

    	FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Target File for the Export");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
				new ExtensionFilter("All Files", "*.*"));
		Stage stage = new Stage();
		File targetFile = chooser.showSaveDialog(stage);
		
		try(FileWriter writer = new FileWriter(targetFile);)
		{
			writer.write(References.storeOrder.toString());
		}
		
		catch(FileNotFoundException e)
		{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("File not found.");
            alert.showAndWait();
            return;
		}
		
		catch(IOException e)
		{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Error reading file.");
            alert.showAndWait();
            return;
		}
		
		catch(NullPointerException e)
		{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Closed window before selecting file.");
            alert.showAndWait();
            return;
		}
		
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("File exported.");
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        alert.showAndWait();
    }

    /**
     * Method to clear all fields after an order is removed.
     */
    private void clear()
    {
    	orderTotalField.clear();
    	orderTotalField.appendText("$0.00");
    	
    	int currentOrderID = Integer.parseInt(orderID.getSelectionModel().getSelectedItem());
    	References.storeOrder.remove(References.storeOrder.getByID(currentOrderID));
    	
    	thisOrderContentField.getItems().clear();
    	orderID.getItems().clear();
    	
    	setComboBox();
    	displayPrice();
    }


}
