/*
 * Tuple.java
 *
 * 10/10/17
 * This class stores a Tuple, containing a linked list of attributes.
 */
import java.util.*; 
 
public class Tuple {
	private LinkedList<String> attributes;

	public LinkedList<String> attribute() {
		return this.attributes;
   }   
   
	public Tuple() {
      attributes = new LinkedList<String>();	
	}
}
