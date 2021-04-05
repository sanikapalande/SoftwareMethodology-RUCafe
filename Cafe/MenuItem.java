package Cafe;
/**
 * Defines the abstract data type MenuItem, which encapsulates the data fields and methods of a MenuItem.
 * Contains methods to test return a String representation of the MenuItem, calculate the price of a MenuItem,
 * and to retrieve private parameters of a MenuItem.
 * @author Jared Chiou, Sanika Palande
 */
public class MenuItem 
{
	private double price;
	private int quantity;

	/**
	 * Constructor for superclass MenuItem that takes the quantity of desire MenuItem subtype.
	 */
	public MenuItem()
	{
		this.price = 0;
		this.quantity = 0;
	}

	/**
	 * Converts MenuItem details into a certain format depending on MenuItem subtype.
	 * @return an empty String that will be built on in subclasses.
	 */
	@Override
	public String toString()
	{
		return "";
	}

	/**
	 * Method used to calculate price of MenuItem based on MenuItem type and properties.
	 * Implemented in subclasses Donut and Coffee.
	 */
	public void itemPrice()
	{

	}

	/**
	 * Retrieves the private parameter price.
	 * @return price - the price of the MenuItem.
	 */
	public double getPrice()
	{
		return this.price;
	}

	/**
	 * Retrieves the private parameter quantity.
	 * @return quantity - the quantity of the MenuItem.
	 */
	public int getQuantity()
	{
		return this.quantity;
	}

	/**
	 * Sets the private parameter quantity to a specified value.
	 * @param quantity - the quantity of the MenuItem.
	 */
	protected void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	/**
	 * Sets the private parameter price to a specified value.
	 * @param price - the price of the MenuItem.
	 */
	protected void setPrice(double price)
	{
		this.price = price;
	}


}
