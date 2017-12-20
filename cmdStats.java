/* cmdStats.java
 *
 * Created by William Tyas
 * 11/21/17
 *
 * This command prints statistics about the specified tuples and the
 * database.
 */
import java.util.*;

public class cmdStats extends base {
	public cmdStats(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		printDatabaseStats(db);
		while (line.hasNext()) {
			String rName = nextSymbol(line);
			int[] rIndex = findRelation(rName, db, true);
			if (rIndex[0] == -1) {
				System.out.println("*TEMP*");
			} else if (rIndex[1] == 1) {
				printStats(db.getRelations().get(rIndex[0]));
			} else {
				printStats(db.getTmpRelations().get(rIndex[0]));
			}
			System.out.println();
		}
	}

	public void printStats(Relation r) {
		System.out.println(r.getTitle());
		System.out.println("Number of tuples: " + r.getTuples().size());
		System.out.println("Number of attributes: " + r.getCategories().size());
		System.out.println();
	}

	public void printDatabaseStats(Database db) {
		System.out.println("STATISTICS");
		System.out.println("==========");
		int numRelations = db.getRelations().size();
		int numTmpRelations = db.getTmpRelations().size();
		System.out.println("Number of relations: " + numRelations);
		System.out.println("Number of temporary relations: " + numTmpRelations);
		System.out.println("Total number of relations: " + (numRelations + numTmpRelations));
		System.out.println();
	}
}