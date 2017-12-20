import java.util.*;

public abstract class base implements Icommand{

   public String nextSymbol(Scanner read){
      String fix = read.next();
      String better = "";
      boolean quote = false;
      for(int i =0; i < fix.length(); i++){
         char a = fix.charAt(i);
         if(a == '\''){
             quote = true;
         }
         else if(a == '('){
         }
         else if(a == ')'){
         }
         else if(a == ','){
         }
         else if(a == ';'){
         }
         else{
            better += a;
         }
      }
      while(quote == true){
         better += " ";
         fix = read.next();
         for(int j = 0; j < fix.length(); j++){
            char a = fix.charAt(j);
            if(a == '\''){
             quote = false;
            }
            else if(a == '('){
            }
            else if(a == ')'){
            }
            else if(a == ','){
            }
            else if(a == ';'){
            }
            else{
               better += a;
            }
         }
      }

            
      return better;
    }
    
    int[] findRelation(String name, Database db, boolean includeTmp) {
		int result = -1;
		int isTmpIndex = 1;
		LinkedList<Relation> rls = db.getRelations();
		ListIterator<Relation> litr = rls.listIterator();
		int elem = 0;
		while (litr.hasNext()) {
			String nextTitle = litr.next().getTitle();
			if (nextTitle != null && nextTitle.equals(name)) {
				result = elem;
			}
			elem++;
		}
		if (includeTmp && result < 0) {
			result = findTmpRelation(name, db);
			isTmpIndex = 0;
		}
		int[] resultArray = {result, isTmpIndex}; 
		return resultArray;
	}

	private int findTmpRelation(String name, Database db) {
		int result = -1;
		LinkedList<Relation> tmprls = db.getTmpRelations();
		ListIterator<Relation> tmpitr = tmprls.listIterator();
		int elem = 0;
		while (tmpitr.hasNext()) {
			String nextTitle = tmpitr.next().getTitle();
			if (nextTitle != null && nextTitle.equals(name)) {
				result = elem;
			}
			elem++;
		}
		return result;
	}
}