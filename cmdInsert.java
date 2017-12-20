import java.util.*;

public class cmdInsert extends base{

	public cmdInsert(Scanner line, Database db, LinkedList<String> errorsList){
		run(line, db, errorsList);
	}

	public boolean typematch(String a, LinkedList<String> dataTypes, int i){
		boolean match = true;
		try{
			int title = Integer.parseInt(dataTypes.get(i));
			try{
				int x = Integer.parseInt(a);
			}
			catch(Exception NumberFormatException){
				match = false;
			}
		}
		catch(Exception NumberFormatException){
		}
		return match;
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList){
		boolean errors = false;
		String rValue = nextSymbol(line);
		int rIndex[] = findRelation(rValue, db, false);
		if (rIndex[0] == -1) {
			errorsList.add("ERROR: RELATION \"" + rValue + "\" DOESN'T EXIST");
		} else {
			Relation r = db.getRelations().get(rIndex[0]);
			Tuple newTuple = new Tuple();
			int i = 0;
			while (line.hasNext()) {
				String nextAttr = nextSymbol(line);
				if (!typematch(nextAttr, r.getdataTypes(), i)) {
					errorsList.add("ERROR: TYPES DON'T MATCH");
					errors = true;
					break;
				}
				if (i >= r.getmaxSize().size()) {
					errorsList.add("ERROR: TOO MANY ATTRIBUTES FOR RELATION \"" + rValue + "\"");
					errors = true;
					break;
				}
				String better = format(nextAttr, r.getmaxSize().get(i));
				newTuple.attribute().add(better);
				i++;
			}
			if (newTuple.attribute().size() != r.getCategories().size()) {
				errorsList.add("ERROR: TOO FEW ATTRIBUTES FOR RELATION \"" + rValue + "\"");
				errors = true;
			}
			if (!errors) {
				r.getTuples().add(newTuple);
			}
		}
	}

	public String format(String a, String line){
		String b = "";
		try{
			int x = Integer.parseInt(line);
			for(int j = 0; j < a.length(); j++){
				if(j < x){
					b += a.charAt(j);
				}
				else{
					b = b.substring(1);
					b += a.charAt(j);
				}
			}
		}
		catch(Exception NumberFormatException){
			int top = Integer.parseInt(line);
			for(int i = 0; i < a.length(); i++){
				if( i < top ){
					b += a.charAt(i);
				}
			}
		}
		return b;
	}
}
