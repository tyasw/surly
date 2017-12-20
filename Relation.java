/*
 * Relation.java
 *
 * 10/10/17
 * This class stores a relation, which contains a linked list of tuples.
 */
import java.util.*;
public class Relation {
	private String title;
	private LinkedList<Tuple> tuples;
    private LinkedList<String> categories;
    private LinkedList<String> dataTypes;
    private LinkedList<String> maxSize;
	public String getTitle() {
		return this.title;
	}
   public void setTitle(String title) {
		this.title = title;
	}
   
   public LinkedList<String> getCategories(){
      return this.categories;
   }

   
	public LinkedList<Tuple> getTuples() {
		return this.tuples;
	}
   public LinkedList<String> getdataTypes() {
		return this.dataTypes;
	}
   public LinkedList<String> getmaxSize() {
      return this.maxSize;
   }

   
	/* Relation
	 *
	 * Sets the list of tuples to null.
	 */
	public Relation() {
		this.tuples = new LinkedList<Tuple>(); 
        this.categories = new LinkedList<String>();
        this.dataTypes = new LinkedList<String>();
        this.maxSize = new LinkedList<String>();
	}
   
}
