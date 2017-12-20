// Tony Bhangal 
// Modified by William Tyas
// JOIN
import java.util.*;
public class cmdJoin extends base{
	public cmdJoin(Scanner line, Database db, LinkedList<String> errorsList){
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList){
		String relationAname = nextSymbol(line);
		String relationBname = nextSymbol(line);
		nextSymbol(line);
		String joinOn1 = nextSymbol(line);
		nextSymbol(line);
		String joinOn2 = nextSymbol(line);

		int rel1col = 0;
		int rel2col = 0;

		Relation joined = new Relation();
		int[] relOneIndex = findRelation(relationAname, db, true);
		int[] relTwoIndex = findRelation(relationBname, db, true);
		Relation rOne = new Relation();
		Relation rTwo = new Relation();
		if (relOneIndex[0] != -1 && relTwoIndex[0] != -1) {
			if (relOneIndex[1] == 1) {
				rOne = db.getRelations().get(relOneIndex[0]);
			} else {
				rOne = db.getTmpRelations().get(relOneIndex[0]);
			}

			if (relTwoIndex[1] == 1) {
				rTwo = db.getRelations().get(relTwoIndex[0]);
			} else {
				rTwo = db.getTmpRelations().get(relTwoIndex[0]);
			}

			for (int i = 0; i < rOne.getCategories().size(); i++) {
				joined.getCategories().add(rOne.getCategories().get(i));
				joined.getdataTypes().add(rOne.getdataTypes().get(i));
				joined.getmaxSize().add(rOne.getmaxSize().get(i));
				if (rOne.getCategories().get(i).equals(joinOn1)) {
					rel1col = i;
				}
			}
			for (int i = 0; i < rTwo.getCategories().size(); i++) {
				if (!rTwo.getCategories().get(i).equals(joinOn2)) {
					joined.getCategories().add(rTwo.getCategories().get(i));
					joined.getdataTypes().add(rTwo.getdataTypes().get(i));
					joined.getmaxSize().add(rTwo.getmaxSize().get(i));
				} else {
					rel2col = i;
				}
			}
		} else {
			errorsList.add("ERROR: JOIN ERROR. ONE OF THE TWO RELATIONS DOESN'T EXIST.");
		}

		if (relOneIndex[0] != -1 && relTwoIndex[0] != -1) {
			int firsttuples = rOne.getTuples().get(0).attribute().size();
			int secondtuples = rTwo.getTuples().get(0).attribute().size()-1;

			for(int a = 0; a < rOne.getTuples().size(); a++){
				for(int b = 0; b < rTwo.getTuples().size(); b++){
					String cat1 = rOne.getTuples().get(a).attribute().get(rel1col);
					String cat2 = rTwo.getTuples().get(b).attribute().get(rel2col);
					if(cat1.equals(cat2)){
						Tuple joinedtuple = new Tuple();
						for(int go = 0; go < firsttuples; go++){
							String attribute = rOne.getTuples().get(a).attribute().get(go);
							joinedtuple.attribute().add(attribute);
						}
						for(int go2 = 0; go2 <secondtuples; go2++){
							String attributes = rTwo.getTuples().get(b).attribute().get(go2);
							joinedtuple.attribute().add(attributes);
						}
						joined.getTuples().add(joinedtuple);
					}
				}
			}
			db.getTmpRelations().add(joined);
		}
		else{
			errorsList.add("ERROR: JOIN ERROR");
		}

	}
}
