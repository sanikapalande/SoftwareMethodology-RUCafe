package Cafe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Defines a user interface class Order Controller for interacting with the users at the front end.
 * Deals with I/O and is a client which allows the user to navigate through various pages using a JavaFX GUI.
 * Here, the user can view current order detail, which includes all the menu items added to the order.
 * The user can review the order, remove a selected item and place the order.
 * @author Jared Chiou, Sanika Palande
 */
public class MyOrdersController
{

    private double subTotalOfOrder = 0;
    private double salesTax = 0;
    private double totalOfOrder;
    private double newSubTotal = 0;
    
    @FXML
    private ListView<String> myOrderListField;

    @FXML
    private TextField subTotalField, salesTaxField, totalField;

    @FXML
    private Button placeOrder, removeSelected;

    /**
     * Initializes the My Order Stage and displays necessary initial fields
     */
    @FXML
    public void initialize()
    {
        // setOrderList()
        setOrderList(References.order);

        // calculate subtotal and display
        subTotalOfOrder = References.order.calculateSubTotal();
        subTotalField.appendText(String.format("$%,.2f", subTotalOfOrder));

        //display sales tax
        salesTax = subTotalOfOrder * References.SALES_TAX;
        salesTaxField.appendText(String.format("$%,.2f", salesTax));

        //calculate total and display
        totalOfOrder = References.order.calculateTotal();
        totalField.appendText(String.format("$%,.2f", totalOfOrder));
    }

    /**
     * Method to set the ListView in My Order window to all the Menu Items added to order
     * @param order - added to order
     */
    private void setOrderList(Order order)
    {
        //iterate through MenuItems and add each to myOrderList
        for(int i = 0; i < order.size(); i++)
        {
        	myOrderListField.getItems().add(order.get(i).toString());
        }
    }

    /**
     * Removes the selected MenuItem from Order.
     * @param event - triggered by the GUI when MenuItem is selected and remove button is clicked
     */
    @FXML
    private void selectRemoveMenuItem(ActionEvent event)
    {
        //if no selection has been made -> cannot remove
        if(myOrderListField.getSelectionModel().getSelectedItem() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Nothing has been selected, cannot remove.");
            alert.showAndWait();
            return;
        }


        //obtain index of selected menuItem and remove the item at selected index in myOrderListField
        int index = myOrderListField.getSelectionModel().getSelectedIndex();
        myOrderListField.getItems().remove(index);

        //remove from corresponding MenuItem arraylist
        MenuItem currentItem = References.order.get(index);
        References.order.remove(currentItem);


        //calculate price of updated arrayList of menu Items
        newSubTotal = References.order.calculateSubTotal();

        //display new subtotal
        subTotalField.clear();
        subTotalField.appendText(String.format("$%,.2f", newSubTotal));

        //recalculate sales tax and display
        salesTaxField.clear();
        salesTax = newSubTotal * References.SALES_TAX;
        salesTaxField.appendText(String.format("$%,.2f", salesTax));

        //recalculate total and display
        totalField.clear();
        totalOfOrder = References.order.calculateTotal();
        totalField.appendText(String.format("$%,.2f", totalOfOrder));

    }

    /**
     * Adds the order to Store Order to place order.
     * @param event - triggered by the GUI when Place Order button is clicked
     */
    @FXML
    private void selectPlaceOrder(ActionEvent event)
    {
        //cannot place order if empty
        if(References.order.size() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setContentText("Cannot place to order. No menu items have been added.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Order has been placed.");
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
        alert.showAndWait();


        //add the order to store order
        References.storeOrder.add(References.order);

        clear();

        //reinitialize order
        References.order = new Order();
    }

    /**
     * Clears selected combo boxes and listview selections
     */
    private void clear()
    {
        subTotalField.clear();
        salesTaxField.clear();
        totalField.clear();

        //empty list of menu items
        myOrderListField.getItems().clear();
    }
}
