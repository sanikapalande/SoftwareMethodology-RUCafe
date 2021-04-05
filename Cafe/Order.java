package Cafe;
import java.util.ArrayList;

/**
 * Defines the abstract data type Order, which encapsulates the data fields and methods of the Order.
 * Contains methods to find, add, remove, set hours, calculate payments, and print Employees as well as helper methods
 * to accomplish those tasks.
 * @author Jared Chiou, Sanika Palande
 */
public class Order implements Customizable
{
	private ArrayList<MenuItem> menuList;
	private int orderId;
	private static int numOrders = 1;
	private double subtotal = 0;

	/**
	 * Constructor for Order that creates an ArrayList of MenuItems in the order.
	 */
	public Order()
	{
		this.orderId = numOrders;
		numOrders++;
		menuList = new ArrayList<MenuItem>();
	}

	/**
	 * Decrements the orderID for an Order.
	 */
	protected void decrementID()
	{
		numOrders--;
	}

	/**
	 * Adds a MenuItem to the order.
	 * @param obj adds MenuItem object to be added to the order
	 * @return true if the MenuItem was added, false otherwise.
	 */
	@Override
	public boolean add(Object obj)
	{
		if(!(obj instanceof MenuItem))
		{
			return false;
		}
		
		MenuItem menuItem = (MenuItem)obj;
		
		if(menuList.contains(menuItem))
		{
			return false;
		}
		
		menuList.add(menuItem);
		return true;
	}

	/**
	 * Removes a MenuItem from the order.
	 * @param obj MenuItem object to be removed from the order
	 * @return true if the MenuItem was removed, false otherwise.
	 */
	@Override
	public boolean remove(Object obj)
	{
		if(!(obj instanceof MenuItem))
		{
			return false;
		}

		MenuItem menuItem = (MenuItem)obj;

		if(!menuList.contains(menuItem))
		{
			return false;
		}

		else
		{
			menuList.remove(menuItem);
			return true;
		}
	}
	
	/**
	 * Retrieves the MenuItem at the specified index.
	 * @param index - the index that contains the menuItem to be retrieved.
	 * @return the menuItem at the specified index.
	 */
	public MenuItem get(int index)
	{
		return menuList.get(index);
	}

	/**
	 * Converts the Order details (i.e., each MenuItem and its details) in the order
	 * into a String to be displayed to the user.
	 * @return String of the Order.
	 */
	@Override
	public String toString()
	{
		String result = "";
		
		for(int i = 0; i < menuList.size(); i++)
		{
			result += (menuList.get(i));
			
			if(i != menuList.size()-1)
			{
				result += "\n";
			}
		}
		
		return result;
	}

	/**
	 * Calculates the subtotal of an Order, before tax is applied.
	 * @return String - formatted String of the subtotal
	 */
	public double calculateSubTotal()
	{
		double total = 0;
		
		for(int i = 0; i < menuList.size(); i++)
		{
			menuList.get(i).itemPrice();
			total += menuList.get(i).getPrice();
		}
		this.subtotal = total;
		return total;
	}

	/**
	 * Getter method to get the OrderID of an order.
	 * @return int OrderID of an order.
	 */
	public int getOrderId()
	{
		return this.orderId;
	}

	/**
	 * Calculates the total of an Order, after tax is applied.
	 * @return String - formatted String of the total
	 */
	public double calculateTotal()
	{
		return this.subtotal * (1 + References.SALES_TAX);
	}
	
	/**
	 * Retrieves the size of the current Order.
	 * @return menuList.size() - the number of items in the Order.
	 */
	public int size()
	{
		return menuList.size();
	}
}
