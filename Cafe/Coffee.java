package Cafe;

import java.util.ArrayList;
/**
 * Defines the abstract data type Coffee which is a subclass of MenuItem and encapsulates the data fields and methods of a
 * Coffee item on the menu.
 * Contains methods to add and remove various add-ins to the Coffee, calculate the price of the Coffee, return a String 
 * representation of the Coffee, and to retrieve private parameters of a Coffee MenuItem.
 * @author Jared Chiou, Sanika Palande
 */

public class Coffee extends MenuItem implements Customizable
{
	private ArrayList<String> addInList;
	private static final double SHORT = 1.99;
	private static final double TALL = 2.49;
	private static final double GRANDE = 2.99;
	private static final double VENTI = 3.49;
	private String size;

	/**
	 * Default constructor; creates a Coffee object and instantiates its list of add-ins.
	 */
	public Coffee()
	{
		super();
		addInList = new ArrayList<String>();
	}

	/**
	 * Adds an ingredient from the add-ins to the Coffee.
	 * @param obj - the object to be added.
	 * @return true if the add-in was added, false otherwise.
	 */
	
	@Override
	public boolean add(Object obj)
	{
		if(!(obj instanceof String))
		{
			return false;
		}
		
		String objString = (String)obj;
		
		if(addInList.contains(objString))
		{
			return false;
		}
		
		else
		{
			addInList.add(objString);
			return true;
		}
	}


	/**
	 * Removes an add-in from the Coffee.
	 * @param obj - the add-in to be removed.
	 * @return true if the add-in was removed, false otherwise.
	 */
	@Override
	public boolean remove(Object obj)
	{
		if(!(obj instanceof String))
		{
			return false;
		}
		
		String objString = (String)obj;
		
		if(!addInList.contains(objString))
		{
			return false;
		}
		
		else
		{
			addInList.remove(objString);
			return true;
		}
	}

	/**
	 * Converts the Coffee order details into a String of the form
	 * Coffee Size [{Add-Ins}]({Quantity})
	 * E.g. Coffee Venti [Cream, Milk,Syrup](4)
	 * @return String of the Coffee order.
	 */
	@Override
	public String toString()
	{
		String addInString = "[";
		
		for(int i = 0; i < addInList.size(); i++)
		{
			addInString += addInList.get(i);
			
			if(i != addInList.size() - 1)
			{
				addInString += ", ";
			}

			else
			{
				addInString += "]";
			}

		}
		if(addInList.size() == 0)
		{

			addInString += "No Add-Ins: Black Coffee]";
		}
		
		return super.toString() + "Coffee " + this.size + " " + addInString + "(" + super.getQuantity() + ")";
	}

	/**
	 * Calculates the price of the Coffee item and updates its price parameter.
	 */
	@Override
	public void itemPrice()
	{
		double addInsPrice = addInList.size() * 0.2;
		double sizePrice = 0;
		
		
		if(this.size != null)
		{
			if(this.size.equals("Short"))
			{
				sizePrice = SHORT;
			}
			
			else if(this.size.equals("Tall"))
			{
				sizePrice = TALL;
			}
			
			else if(this.size.equals("Grande"))
			{
				sizePrice = GRANDE;
			}
			
			else //the only possible remaining size is Venti
			{
				sizePrice = VENTI;
			}
		}
		
		super.setPrice((addInsPrice + sizePrice) * super.getQuantity());
	}
	
	/**
	 * Sets the size of the current Coffee MenuIem.
	 * @param size - a String containing the value of "Short", "Tall", "Grande", or "Venti."
	 */
	protected void setSize(String size)
	{
		this.size = size;
	}
}
