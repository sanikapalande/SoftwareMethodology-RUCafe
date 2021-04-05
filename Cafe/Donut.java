package Cafe;

/**
 * Defines the abstract data type Donut which is a subclass of MenuItem and encapsulates the data fields and methods of a
 * Donut item on the menu.
 * Contains methods to calculate the price of the Coffee, return a String 
 * representation of the Donut, and to retrieve private parameters of a Donut MenuItem.
 * @author Jared Chiou, Sanika Palande
 */
public class Donut extends MenuItem
{

	private static final double YEAST = 1.39;
	private static final double CAKE = 1.59;
	private static final double DONUT_HOLE = 0.33;
	private String type;
	private String flavor;

	/**
	 * Default constructor; creates a Donut object.
	 */
	public Donut()
	{
		super();
	}

	/**
	 * Calculates the price of the Donut item and updates its price parameter.
	 */
	@Override
	public void itemPrice()
	{
		if(this.type != null)
		{

			if (this.type.equals("Yeast Donut"))
			{
				super.setPrice(YEAST * super.getQuantity());
			}
			else if (this.type.equals("Cake Donut"))
			{
				super.setPrice(CAKE * super.getQuantity());
			}
			else //the only possible remaining type is "Donut Hole"
			{
				super.setPrice(DONUT_HOLE * super.getQuantity());
			}
		}
	}
	
	/**
	 * Sets the type of the current Donut MenuItem.
	 * @param type - a String containing the value of "Yeast Donut", "Cake Donut", or "Donut Hole."
	 */
	protected void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Retrieves the type of the Donut MenuItem.
	 * @return type - type of the Donut
	 */
	protected String getType()
	{
		return type;
	}

	/**
	 * Sets the flavor of the current Donut MenuItem.
	 * @param flavor - the flavor of the Donut.
	 */
	public void setFlavor(String flavor)
	{
		this.flavor = flavor;
	}

	/**
	 * Converts the Donut order details into a String of the form
	 * {Flavor} flavored {Type} Donut ({Quantity})
	 * E.g. Powdered flavored Cake Donut(1)
	 * @return String of the Donut order.
	 */
	@Override
	public String toString()
	{
		return super.toString() + this.type + " " + this.flavor + " flavored " + "(" + super.getQuantity() + ")";
	}
}