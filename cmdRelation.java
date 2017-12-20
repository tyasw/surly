import java.util.*;

public class cmdRelation extends base{

   public cmdRelation(Scanner line, Database db, LinkedList<String> errorsList){
      run(line, db, errorsList);
   }
   
   public void run(Scanner line, Database db, LinkedList<String> errorsList){
      Relation a = new Relation();
      String check = nextSymbol(line);
      int[] rIndex = findRelation(check, db, false);
		if (rIndex[0] != -1) {
			errorsList.add("RELATION \"" + check + "\" ALREADY EXISTS");
		}
      else{
         a.setTitle(check);
         while (line.hasNext()) {
	       String cat = nextSymbol(line);
		    if (cat.equals("CHAR")) {
            String ctype = "";
            a.getdataTypes().add(ctype);
            String charsize = nextSymbol(line);
            a.getmaxSize().add(charsize);

         }
         else if (cat.equals("NUM")){
            String ntype = "1";
            a.getdataTypes().add(ntype);
            String numsize = nextSymbol(line);
            a.getmaxSize().add(numsize);
         }
         else{
         
         a.getCategories().add(cat);
         }
      }
      db.getRelations().add(a);
      }
  }
}
