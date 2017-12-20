/*
 * Database.java
 *
 * 10/10/17
 * This class stores a database, which contains a linked list of
 * relations.
 */
import java.util.*;
public class Database {
	private LinkedList<Relation> relations;
   private LinkedList<Relation> tmpRelations;

	public Database() {
		this.relations = new LinkedList<Relation>();
      this.tmpRelations = new LinkedList<Relation>();
	}
	public LinkedList<Relation> getRelations() {
		return this.relations;
	}
   public LinkedList<Relation> getTmpRelations() {
		return this.tmpRelations;
	}
}