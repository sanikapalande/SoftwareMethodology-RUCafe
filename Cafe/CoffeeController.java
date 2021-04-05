package Cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Defines a user interface class Coffee Controller for interacting with the users at the front end.
 * Deals with I/O and is a client which handles an instance of the Coffee class through a JavaFX GUI.
 * @author Jared Chiou, Sanika Palande
 */
public class CoffeeController 
{
		private Coffee coffee;
	
	    @FXML
	    private CheckBox creamButton, syrupButton, caramelButton, milkButton, whippedCreamButton;

	    @FXML
	    private ComboBox<String> sizeField, quantityField;

	    @FXML
	    private TextField priceField;

	    @FXML
	    private Button addButton;

	    
	    /**
	     * Initializes the GUI, populating the Combo Boxes with their options, creating a new Coffee object, and displaying the price.
	     */
	    @FXML
	    public void initialize()
	    {	
	    	ObservableList<String> sizeItems = FXCollections.observableArrayList("Short", "Tall", "Grande", "Venti"); //initializes size options
	    	sizeField.setItems(sizeItems);

	    	ObservableList<String> quantityItems = FXCollections.observableArrayList("1", "2", "3", "4", "5"); //initializes quantity options
	    	quantityField.setItems(quantityItems);
	    	
	    	coffee = new Coffee();
	    	displayPrice();
	    }
	    
	    /**
	     * Displays the current price of the Coffee item in the text field of the GUI.
	     */
	    private void displayPrice()
	    {
	    	priceField.clear();
	    	coffee.itemPrice();
	    	priceField.appendText(String.format("$%,.2f", coffee.getPrice()));
	    }
	    
	    /**
	     * Adds the selected add ins to the Coffee item and removes the ones that aren't selected. Updates the price on the GUI.
	     * @param event - triggered on the GUI when a check box is selected
	     */
	    @FXML
	    void addAddIn(ActionEvent event) 
	    {
	    	if(creamButton.isSelected())
	    	{
	    		coffee.add("Cream");
	    	}
	    	
	    	else
	    	{
	    		coffee.remove("Cream");
	    	}
	    	
	    	if(syrupButton.isSelected())
	    	{
	    		coffee.add("Syrup");
	    	}
	    	
	    	else
	    	{
	    		coffee.remove("Syrup");
	    	}
	    	
	    	if(caramelButton.isSelected())
	    	{
	    		coffee.add("Caramel");
	    	}
	    	
	    	else
	    	{
	    		coffee.remove("Caramel");
	    	}
	    	
	    	if(milkButton.isSelected())
	    	{
	    		coffee.add("Milk");
	    	}
	    	
	    	else
	    	{
	    		coffee.remove("Milk");
	    	}
	    	
	    	if(whippedCreamButton.isSelected())
	    	{
	    		coffee.add("Whipped Cream");
	    	}
	    	
	    	else
	    	{
	    		coffee.remove("Whipped Cream");
	    	}
	    	
	    	displayPrice();
	    }

	    /**
	     * Adds the current Coffee item to the current Order and clears all filled out fields.
	     * @param event - triggered by the GUI when the "Add To Order" button is clicked
	     */
	    @FXML
	    void addToOrder(ActionEvent event) 
	    {
	    	if(quantityField.getValue() == null || sizeField.getValue()== null) //invalid input, displays error message
	    	{
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error Dialog");
				alert.setContentText("Cannot add to order. Please select quantity and size.");
				alert.showAndWait();
	    		return;
	    	}

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setContentText("Order has been added, confirmed.");
			alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
			alert.showAndWait();
	    	
	    	References.order.add(coffee);
	    	coffee = new Coffee();
	    	clear();
	    	displayPrice();
	    }
	    
	    /**
	     * Clears selected check boxes and combo boxes.
	     */
	    private void clear()
	    {
	    	creamButton.setSelected(false);
	    	syrupButton.setSelected(false);
	    	caramelButton.setSelected(false);
	    	milkButton.setSelected(false);
	    	whippedCreamButton.setSelected(false);
	    	sizeField.getSelectionModel().clearSelection();
	    	quantityField.getSelectionModel().clearSelection();
	    }

	    /**
	     * Sets the quantity parameter of Coffee to the quantity selected on the GUI.
	     * @param event - triggered by the GUI when a Quantity item is selected
	     */
	    @FXML
	    void selectQuantity(ActionEvent event) 
	    {
	    	if(!(quantityField.getValue() == null))
	    	{
	    		coffee.setQuantity(Integer.parseInt(quantityField.getSelectionModel().getSelectedItem()));
			    displayPrice();
	    	}
	    }

	    /**
	     * Sets the size parameter of Coffee to the size selected on the GUI.
	     * @param event - triggered by the GUI when a Size item is selected
	     */
	    @FXML
	    void selectSize(ActionEvent event) 
	    {
	    	if(!(sizeField.getValue() == null))
	    	{
	    		coffee.setSize(sizeField.getSelectionModel().getSelectedItem());
			    displayPrice();
	    	}	
	    }

}
