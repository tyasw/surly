import java.util.*;

public class cmdPrint extends base{
	public cmdPrint(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}
   
	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		while (line.hasNext()) {
			String aValue = nextSymbol(line);
			int[] rIndex = findRelation(aValue, db, true);
			if (rIndex[0] == -1) {
				System.out.println();
				System.out.println("*TEMPORARY*");
			} else if (rIndex[1] == 1) {
				print(db.getRelations().get(rIndex[0]));
			} else {
				print(db.getTmpRelations().get(rIndex[0]));
			}
			System.out.println();
		}
	}

	public void print(Relation r) {
		System.out.println();
		int[] width = printTop(r);
		System.out.println();
		  for (int i = 0; i < r.getTuples().size(); i++) {
			printTuple(i, r, width);
			System.out.print("\n");
		  }

	}

	public int[] printTop(Relation r) {
		printTitle(r.getTitle());
		int[] width = printCategories(r);
		int total = 0;
		for (int j = 0; j < width.length; j++) {
		  total = total + width[j];
		}
		for (int i = 0; i < total; i++) {
			System.out.print("=");
		}

		return width;
	}

	public void printTitle(String title) {
		System.out.println(title);
		for(int k = 0; k < title.length(); k++){
		  System.out.print("*");
		}
		System.out.println();
	}

	public int[] printCategories(Relation r) {
		int[] width = new int[r.getCategories().size()];
		for (int i = 0; i < r.getCategories().size(); i++) {
			 System.out.print(r.getCategories().get(i));
			 int size = 0;
			 int pad = 0;
			if(r.getCategories().get(i).length() > (Integer.parseInt(r.getmaxSize().get(i)))){
			 size = r.getCategories().get(i).length();
			 pad = 2;
			}
			else{
			 size = Integer.parseInt(r.getmaxSize().get(i));
			 pad = ((size+2) - (r.getCategories().get(i).length()));
			}
			width[i] = ((r.getCategories().get(i).length()) + pad);
			while(pad > 0){
			 System.out.print(" ");
			 pad--;
			}
		}
		System.out.println();

		return width;
	}

	public void printTuple(int x, Relation r, int[] width) {
		Tuple t = r.getTuples().get(x);
		for (int i = 0; i < t.attribute().size(); i++) {
			int size = width[i];
			int current = t.attribute().get(i).length();
			int pad = (size - current); 
			System.out.print(t.attribute().get(i));
			while (pad >= 0) {
				System.out.print(" ");
				pad--;
			}
		}
	}
}