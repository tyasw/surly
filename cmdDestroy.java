/*
 * cmdDestroy.java
 * 12/4/17
 * Created by William Tyas
 *
 * This command removes a relation from the database, effectively destroying
 * it.
 */
import java.util.*;

public class cmdDestroy extends base {
	public cmdDestroy(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		LinkedList<Relation> rls = db.getRelations();
		ListIterator<Relation> litr = rls.listIterator();
		Relation r = null;
		while (line.hasNext()) {
			String rValue = nextSymbol(line);
			int[] rIndex = findRelation(rValue, db, false);
			if (rIndex[0] != -1) {
				rls.remove(rIndex[0]);
			}
		}
	}
}