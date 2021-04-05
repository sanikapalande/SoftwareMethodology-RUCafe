package Cafe;
import java.util.ArrayList;

/**
 * StoreOrder class that keeps the list of orders placed by the users.
 * @author Jared Chiou, Sanika Palande
 */

public class StoreOrders implements Customizable
{
	private ArrayList<Order> orderList;

	/**
	 * Constructor for Store Orders that creates an ArrayList of Orders in the order.
	 */
	public StoreOrders()
	{
		orderList = new ArrayList<Order>();
	}


	/**
	 * Adds an Order to the orderList.
	 * @param obj Order object to be added to the Store Order
	 * @return true if the Order was added, false otherwise.
	 */
	@Override
	public boolean add(Object obj)
	{
		if(!(obj instanceof Order))
		{
			return false;
		}
		
		Order order = (Order)obj;
		
		if(orderList.contains(order))
		{
			return false;
		}
		
		orderList.add(order);
		return true;
	}

	/**
	 * Removes an Order from the orderList.
	 * @param obj Order object to be removed from Store Order
	 * @return true if the Order was removed, false otherwise.
	 */
	@Override
	public boolean remove(Object obj)
	{
		if(!(obj instanceof Order))
		{
			return false;
		}
		
		Order order = (Order)obj;
		
		if(!orderList.contains(order))
		{
			return false;
		}
		
		else
		{
			orderList.remove(order);
			return true;
		}
	}

	/**
	 * Retrieves the Order at the specified index.
	 * @param index - the index that contains the Order to be retrieved.
	 * @return the Order at the specified index.
	 */
	public Order get(int index)
	{
		return orderList.get(index);
	}
	
	/**
	 * Retrieves the Order with the specified ID.
	 * @param ID - the order ID
	 * @return the Order with the specified ID.
	 */
	public Order getByID(int ID)
	{
		for(Order order: orderList)
		{
			if(order.getOrderId() == ID)
			{
				return order;
			}
		}
		
		return null;
	}
	
	/**
	 * Calculates the total of all Store Orders.
	 * @return String - formatted String of the total.
	 */
	public double calculateStoreTotal()
	{
		double total = 0;
		
		for(int i = 0; i < orderList.size(); i++)
		{
			total += orderList.get(i).calculateTotal();
		}
		
		return total;
	}

	/**
	 * Converts the StoreOrder details (i.e., each Order in the orderList and its details)
	 * into a String to be displayed to the user.
	 * @return String of the StoreOrder.
	 */
	public String toString()
	{
		String result = "";

		for(int i = 0; i < orderList.size(); i++)
		{
			result += "Order #" + orderList.get(i).getOrderId() + "\n";
			result += (orderList.get(i).toString() + "\n");
			result += String.format("Total: $%,.2f \n\n", orderList.get(i).calculateTotal());
		}

		return result;
	}
	
	/**
	 * Retrieves the size of the current StoreOrder.
	 * @return orderList.size() - the number of Orders in the StoreOrder.
	 */
	public int size()
	{
		return orderList.size();
	}
}
