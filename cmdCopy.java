/* cmdCopy.java
 *
 * Created by William Tyas
 * 11/21/17
 *
 * This command creates a copy of its relational argument.
 */
import java.util.*;

public class cmdCopy extends base {
	public cmdCopy(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		String relexp = nextSymbol(line);
		int[] rIndex = findRelation(relexp, db, true);
		if (rIndex[0] != -1) {
			Relation r = db.getRelations().get(rIndex[0]);
			Relation tmp = createTmpRelation(r, db);
		}
	}

	public Relation createTmpRelation(Relation r, Database db) {
		Relation tmp = new Relation();

		for (int i = 0; i < r.getCategories().size(); i++) {
			tmp.getCategories().add(r.getCategories().get(i));
			tmp.getdataTypes().add(r.getdataTypes().get(i));
			tmp.getmaxSize().add(r.getmaxSize().get(i));
		}

		for (int i = 0; i < r.getTuples().size(); i++) {
			tmp.getTuples().add(r.getTuples().get(i));
		}

		db.getTmpRelations().add(tmp);
		return tmp;
	}
}