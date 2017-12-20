/* cmdDelete.java
 *
 * 11/18/17
 * This command deletes tuples from a relation
 */
import java.util.*;

public class cmdDelete extends base {
	public cmdDelete(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		String rName = nextSymbol(line);
		int[] rIndex = findRelation(rName, db, false);
		if (rIndex[0] != -1) {
			Relation r = db.getRelations().get(rIndex[0]);
			deleteTuples(r, db, line);
		}
	}

	public void deleteTuples(Relation r, Database db, Scanner line) {
		if (line.hasNext() && line.next().equals("WHERE")) {
			qualificationTest(r, db, line);
		}
	}

	public void qualificationTest(Relation r, Database db, Scanner line) {
		LinkedList<LinkedList<String>> qualifiers = createQualifiersList(line, r);
		boolean pass = false;
		for (int i = r.getTuples().size() - 1; i >= 0; i--) {
			Tuple t = r.getTuples().get(i);
			pass = runTest(r, t, qualifiers);
			if (pass) {
				r.getTuples().remove(i);
			}
		}
	}

	public LinkedList<LinkedList<String>> createQualifiersList(Scanner line, Relation r) {
		LinkedList<LinkedList<String>> qualifiers = new LinkedList<LinkedList<String>>();
		while (line.hasNext()) {
			qualifiers.add(getAndedGroup(line, r));
		}
		return qualifiers;
	}

	/* pass					The tuple passed the qualifying test
	 * currentConditionPass	The tuple passed the current condition
	 */
	public boolean runTest(Relation r, Tuple t, LinkedList<LinkedList<String>> qualifiers) {
		boolean pass = true;
		boolean currentConditionPass = true;

		for (int i = 0; i < qualifiers.size(); i++) {
			for (int j = 0; j < qualifiers.get(i).size(); j++) {
				String[] test = qualifiers.get(i).get(j).split(" ");
				if (test.length > 3) {
					for (int k = 3; k < test.length; k++) {
						test[2] += " " + test[k];
					}
				}
				int aPos = nextAttribute(r, test[0]);
				String attr = t.attribute().get(aPos);
				currentConditionPass = singleConditionTest(attr, test);
				if (!currentConditionPass) { // Stop looking at "AND" group and move on to next "AND" group
					pass = false;
					break;
				} else {
					pass = true;
				}
			}

			if (pass) {		// No need to keep looking thru "OR" groups
				break;
			}
		}

		return pass;
	}

	// Run the string or the integer version of the condition test
	public boolean singleConditionTest(String attr, String[] test) {
		boolean currentAndPass = true;
		try {
			int num = Integer.parseInt(attr);
			int value = Integer.parseInt(test[2]);
			currentAndPass = conditionTestInt(num, test[1], value);
		} catch (Exception NumberFormatException) {
			String value = test[2];
			currentAndPass = conditionTestStr(attr, test[1], value);
		}
		return currentAndPass;
	}

	// Check if value in tuple satifies current condition
	public boolean conditionTestInt(int num, String condition, int value) {
		boolean andConditionPass = false;
		switch (condition) {
			case "=":
				if (num == value) {
					andConditionPass = true;
				}
				break;
			case "<":
				if (num < value) {
					andConditionPass = true;
				}
				break;
			case "<=":
				if (num <= value) {
					andConditionPass = true;
				}
				break;
			case ">":
				if (num > value) {
					andConditionPass = true;
				}
				break;
			case ">=":
				if (num >= value) {
					andConditionPass = true;
				}
				break;
			case "!=":
				if (num != value) {
					andConditionPass = true;
				}
				break;
			default:
				break;
		}

		return andConditionPass;
	}

	// Check if value in tuple satifies current condition
	public boolean conditionTestStr(String attr, String condition, String value) {
		boolean andConditionPass = false;
		switch (condition) {
			case "=":
				if (attr.equals(value)) {
					andConditionPass = true;
				}
				break;
			case "!=":
				if (!attr.equals(value)) {
					andConditionPass = true;
				}
				break;
			default:
				break;
		}

		return andConditionPass;
	}

	// Return the index (position) of the attribute named query
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

	public LinkedList<String> getAndedGroup(Scanner line, Relation r) {
		LinkedList<String> anded = new LinkedList<String>();
		String and = "AND";
		while (line.hasNext() && and.equals("AND")) {
			String next = getNextAndedQualifier(line);
			if (typematch(next, r)) {
				anded.add(next);
			}
			if (line.hasNext()) {
				and = nextSymbol(line);
			}
		}
		return anded;
	}

	// DOESN'T DO ANYTHING YET
	public boolean typematch(String condition, Relation r) {
		boolean match = true;

		// Get the category corresponding to attribute name
		String[] tokens = condition.split(" ");
		String category = tokens[0];
		String value = tokens[2];

		int aPos = nextAttribute(r, category);

		return match;
	}

	public String getNextAndedQualifier(Scanner line) {
		String attr = nextSymbol(line);
		String relop = nextSymbol(line);
		String value = nextSymbol(line);
		return attr + " " + relop + " " + value;
	}
}
