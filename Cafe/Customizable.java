package Cafe;
/**
 * Interface class for add and remove methods.
 * @author Jared Chiou, Sanika Palande
 */
public interface Customizable 
{
	/**
	 * To add an object
	 * @param obj to add
	 * @return true if object is added, false otherwise
	 */
	boolean add(Object obj);

	/**
	 * To remove an object
	 * @param obj to remove
	 * @return true if object is removed, false otherwise
	 */
	boolean remove(Object obj);
}
