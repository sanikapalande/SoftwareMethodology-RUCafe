package Cafe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Defines a user interface class Donut Controller for interacting with the users at the front end.
 * Deals with I/O and is a client which handles an instance of the Donut class through a JavaFX GUI.
 * @author Jared Chiou, Sanika Palande
 */
public class DonutController
{
    private Donut donut;
    private Order order;
    private double price = 0;
    private double displayPrice = 0;
    private double newSubTotal = 0;
    private ObservableList<String> flavors;

    @FXML
    private ComboBox<String> typeField, quantityField;

    @FXML
    private TextField subTotalField, priceOfSelectedField;

    @FXML
    private Button addButton,removeButton, addToOrderButton;

    @FXML
    private ListView<String> flavorField, selectionsField;


    /**
     * Initializes the GUI, populating the Combo Boxes with their options, creating a new Donut object, and displaying the price.
     */
    @FXML
    public void initialize()
    {
        //Type of Donut
        ObservableList<String> typeItems = FXCollections.observableArrayList("Yeast Donut", "Cake Donut", "Donut Hole");
        typeField.setItems(typeItems);

        //Quantity of Donut
        ObservableList<String> quantityItems = FXCollections.observableArrayList("1", "2", "3", "4", "5");
        quantityField.setItems(quantityItems);

        //instance of Donut
        donut = new Donut();
        order = new Order();
        order.decrementID();

        //display initial prices = 0.00
        displayPrice();
        displaySubtotal();
    }


    /**
     * Displays the current price of the Donut item in the "price of selected" text field of the GUI.
     */
    private void displayPrice()
    {
        priceOfSelectedField.clear();
        donut.itemPrice();
        displayPrice = donut.getPrice();
        priceOfSelectedField.appendText(String.format("$%,.2f", displayPrice));
    }

    /**
     * Displays the subtotal of added Donut items in the subTotal text field of the GUI.
     */
    private void displaySubtotal()
    {
        subTotalField.clear();
        
        if(newSubTotal != 0)
        {
            newSubTotal += displayPrice;
            subTotalField.appendText(String.format("$%,.2f", newSubTotal));
        }
        
        else
        {
            price += displayPrice;
            subTotalField.appendText(String.format("$%,.2f", price));
        }
    }

    /**
     * Sets the type parameter of Donut to the type selected on the GUI.
     * @param event - triggered by the GUI when a Type item is selected
     */
    @FXML
    void selectType(ActionEvent event)
    {
        if(!(typeField.getValue() == null))
        {
            donut.setType(typeField.getSelectionModel().getSelectedItem());
            showAndSetFlavors();
            displayPrice();
        }
    }

    /**
     * Sets Flavors of Donuts Depending on which type is selected
     * Sets the flavor parameter of Donut to the flavor selected on the GUI
     */
    @FXML
    void showAndSetFlavors()
    {
        //Yeast Donut selected
        if(this.donut.getType() == "Yeast Donut")
        {
            flavors = FXCollections.observableArrayList("Plain Glazed",
                    "Chocolate Glazed", "Cinnamon Sugar", "Toasted Coconut", "Powdered", "Blueberry Glazed");
            flavorField.setItems(flavors);
        }

        //Cake Donut selected
        else if(this.donut.getType() == "Cake Donut")
        {
            flavors = FXCollections.observableArrayList("Chocolate Frosted", "Vanilla Frosted", "Chocolate Frosted with Sprinkles",
                    "Vanilla Frosted with Sprinkles", "Strawberry Frosted with Sprinkles",
                    "Red Velvet", "Apple Cider", "Jelly-Filled", "Matcha Topped",
                    "Lemon-Glazed", "Coffee Roll");
            flavorField.setItems(flavors);
        }
        
        //Donut Hole selected
        else
        {
            flavors = FXCollections.observableArrayList("Chocolate Glazed","Glazed","Powdered","Jelly-filled");
            flavorField.setItems(flavors);
        }

        flavorField.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            /**
             * Sets the flavor parameter of Donut to the flavor selected on the GUI
             * @param arg0 - triggered by the GUI when a flavor is selected
             */
            @Override
            public void handle(MouseEvent arg0)
            {

                if (!(flavorField.getSelectionModel().getSelectedItem() == null))
                {
                    donut.setFlavor(flavorField.getSelectionModel().getSelectedItem());
                    displayPrice();
                }
            }
        });
    }


    /**
     * Sets the quantity parameter of Donut to the quantity selected on the GUI.
     * @param event - triggered by the GUI when a Quantity item is selected
     */
    @FXML
    void selectQuantity(ActionEvent event)
    {
        if(!(quantityField.getValue() == null))
        {
            donut.setQuantity(Integer.parseInt(quantityField.getSelectionModel().getSelectedItem()));
            displayPrice();
        }
    }


    /**
     * Adds the current Donut item to the current Order and clears all filled out fields.
     * @param event - triggered by the GUI when the "Add To Order" button is clicked
     */
    @FXML
    void addToOrder(ActionEvent event)
    {
        // add alert for error message - "Cannot add unless quantity, type and flavor are selected"
        if(order.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Cannot add to order. Please add menu items.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Order has been added, confirmed.");
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        alert.showAndWait();



        //add to order in Main Menus
        donut = new Donut();

        //Clear everything and reinitialize
        clear();
        selectionsField.getItems().clear();
        subTotalField.clear();
        priceOfSelectedField.clear();


        //reinitialize
        price = 0;
        displayPrice = 0;
        newSubTotal = 0;

        // display displayPrice and displaySubtotal to $0.00
        displayPrice();
        displaySubtotal();

        //REFERENCES
        addToReferences();
        
        order = new Order();
        order.decrementID();

    }

    /**
     * Adds the current Donut item to List of Selections.
     * @param event - triggered by the GUI when donut item is selected and add button is clicked
     */
    @FXML
    void addDonutSelection(ActionEvent event)
    {
        // add alert for error message - "Cannot add unless quantity, type and flavor are selected"
        if(typeField.getValue() == null || flavorField.getSelectionModel().getSelectedItem() == null || quantityField.getValue()==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Cannot add unless, type, flavor, quantity are selected. Try again.");
            alert.showAndWait();
            return;
        }

        //add donut to donut list donutSelection
        order.add(donut);

        //correspondingly, add the string version of donut selectionsField.
        selectionsField.getItems().add(donut.toString());
        
        donut = new Donut();

        clear();
        displaySubtotal();
        displayPrice();
    }

    /**
     * Removes the current Donut item from List of Selections.
     * @param event - triggered by the GUI when donut item is selected and remove button is clicked
     */
    @FXML
    void removeDonutSelection(ActionEvent event)
    {
        //if List of Selections is empty -> cannot remove
        if(order.size()==0)
        {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("There are no menu items to remove.");
            alert.showAndWait();
            return;
        }
        
        //if no selection has been made -> cannot remove
        if(selectionsField.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Nothing has been selected, cannot remove.");
            alert.showAndWait();
            return;
        }

        //obtain index of selected donut and remove the item at selected index in selectionsField
        int index = selectionsField.getSelectionModel().getSelectedIndex();
        selectionsField.getItems().remove(index);

        //remove from corresponding Donut arraylist
        order.remove(order.get(index));

        //now iterate through your updated donut array list and calculate price based on the type and flavor
        newSubTotal = 0;
        for (int i = 0; i < order.size(); i++)
        {
            MenuItem currDonut = order.get(i);
            currDonut.itemPrice();
            newSubTotal += currDonut.getPrice();
        }

        //display new subtotal and reinitialize values
        price = 0;
        displayPrice = 0;
        subTotalField.clear();
        subTotalField.appendText(String.format("$%,.2f", newSubTotal));

        //clear everything except subtotal field
        typeField.getSelectionModel().clearSelection();
        flavorField.getSelectionModel().clearSelection();

        //empty observableList of flavors
        flavors = FXCollections.observableArrayList();
        flavorField.setItems(flavors);
        quantityField.getSelectionModel().clearSelection();
        priceOfSelectedField.clear();

        //new instance of Donut
        donut = new Donut();

        //displayPrice should display 0.00 when an item is removed
        displayPrice();
    }


    /**
     * Clears selected combo boxes and listview selections
     */
    private void clear()
    {
        typeField.getSelectionModel().clearSelection();
        flavorField.getSelectionModel().clearSelection();

        //empty flavors list
        flavors = FXCollections.observableArrayList();
        flavorField.setItems(flavors);

        quantityField.getSelectionModel().clearSelection();
    }

    /**
     * Method to add donutSelections to references
     */
    void addToReferences()
    {
        //add everything in donutSelections to references
        for (int i = 0; i < order.size(); i++)
        {
            References.order.add(order.get(i));
        }
    }
}

