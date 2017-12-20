/* cmdProject.java
 *
 * 11/17/17
 * This command projects specified attributes
 */
import java.util.*;

public class cmdProject extends base {
	public cmdProject(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		LinkedList<String> aList = new LinkedList<String>();
		LinkedList<String> renames = new LinkedList<String>();

		String rName = getAttributeNames(line, aList, renames);
		int[] rIndex = findRelation(rName, db, true);
		if (rIndex[0] != -1) {
			Relation r = new Relation();
			if (rIndex[1] == 1) {
				r = db.getRelations().get(rIndex[0]);
			} else {
				r = db.getTmpRelations().get(rIndex[0]);
			}
			int[] attributePos = findAttributes(aList, r, errorsList);
			createTmpRelation(attributePos, r, db, renames);
		} else {
			errorsList.add("RELATION \"" + rName + "\" DOES NOT EXIST.");
		}
    }

	public String getAttributeNames(Scanner line, LinkedList<String> aList, LinkedList<String> renames) {
		String current = nextSymbol(line);
		String next = null;
		while (!current.equals("FROM")) {
			if (next == null) {
				next = nextSymbol(line);
			}
			aList.add(current);
			if (next.equals("AS")) {
				String alias = nextSymbol(line);
				renames.add(alias);
				current = nextSymbol(line);
			} else {
				renames.add(null);
				current = next;
			}
			next = nextSymbol(line);
		}
		return next;
	}

	public int[] findAttributes(LinkedList<String> aList, Relation r, LinkedList<String> errorsList) {
		int[] position = new int[aList.size()];	// Indices of attributes we want
		for (int i = 0; i < aList.size(); i++) {
			int x = nextAttribute(r, aList.get(i));
			if (x == -1) {
				errorsList.add("ATTRIBUTE \"" + aList.get(i) +  "\" FROM RELATION \"" + r.getTitle() + "\" DOES NOT EXIST.");
			}
			position[i] = x;
		}
		return position;
	}

	public void createTmpRelation(int[] attributepos, Relation r, Database db, LinkedList<String> renames) {
		Relation tmp = new Relation();

		// Set up relation
		for (int i = 0; i < attributepos.length; i++) {
			int position = attributepos[i];
			if (position != -1) {
				if (renames.get(i) == null) {
					tmp.getCategories().add(r.getCategories().get(position));
				} else {
					tmp.getCategories().add(renames.get(i));
				}
				tmp.getdataTypes().add(r.getdataTypes().get(position));
				tmp.getmaxSize().add(r.getmaxSize().get(position));
			}
		}

		// Fill with tuples
		for (int i = 0; i < r.getTuples().size(); i++) {
			Tuple oldTuple = r.getTuples().get(i);
			Tuple newTuple = new Tuple();
			for (int j = 0; j < attributepos.length; j++) {
				int position = attributepos[j];
				if (position != -1) {
					newTuple.attribute().add(oldTuple.attribute().get(position));
				}
			}

			if (tmp.getTuples().size() > 0) {
				if (!isDuplicate(tmp, newTuple)) {
					tmp.getTuples().add(newTuple);
				}
			} else {
				tmp.getTuples().add(newTuple);	
			}
		}
		db.getTmpRelations().add(tmp);
	}

	public boolean isDuplicate(Relation tmp, Tuple newTuple) {
		boolean isEqual = false;
		for (int j = 0; j < tmp.getTuples().size(); j++) {
			for (int k = 0; k < tmp.getTuples().get(j).attribute().size(); k++) {
				String newTupleAttr = newTuple.attribute().get(k);
				String oldTupleAttr = tmp.getTuples().get(j).attribute().get(k);
				if (newTupleAttr.equals(oldTupleAttr)) {
					isEqual = true;
				} else {
					isEqual = false;
					break;
				}
			}

			if (isEqual) {
				break;
			}
		}
		return isEqual;
	}

	// Find the attribute named query
	public int nextAttribute(Relation r, String query) {
		int i = 0;
		boolean match = false;
		while (!match && i < r.getCategories().size()) {
			if (r.getCategories().get(i).equals(query)) {
				match = true;
			} else {
				i++;
			}
		}
		if (match) {
			return i;
		}
		return -1;
	}
}