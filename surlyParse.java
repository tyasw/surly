//Surly ArgParse

// Tony Bhangal
// 10/16/2017

import java.util.*;
import java.io.*;
import java.util.Scanner;

public class surlyParse {
   public Scanner line;
   public Database db;
   public surlyParse(Database db){
      this.db = db;
   }
   
   public void parse(String args[], LinkedList<String> errorsList){
	   try{
		  File file = new File (args[0]);
		  Scanner input = new Scanner(file);
			while (input.hasNextLine()) {
				line = new Scanner(input.nextLine());
				if (line.hasNext()) {
				   String word = nextSymbol(line);
				   switch(word){
					  case "RELATION":
						cmdRelation r = new cmdRelation(line, db, errorsList);
						break;
					  case "INSERT":
						cmdInsert i = new cmdInsert(line, db, errorsList);                  
						break;
					  case "PRINT":
						cmdPrint p = new cmdPrint(line, db, errorsList);
						 break;
					  case "DESTROY":
						cmdDestroy dstr = new cmdDestroy(line, db, errorsList);
						break;
					case "DELETE":
						cmdDelete del = new cmdDelete(line, db, errorsList); 
						break;
					case "READ":
						cmdRead read = new cmdRead(line, db, errorsList);
						break;
					case "WRITE":
						cmdWrite write = new cmdWrite(line, db, errorsList);
						break;
					case "STATISTICS":
						cmdStats s = new cmdStats(line, db, errorsList);
						break;
					  default:
						relExp(word, line, errorsList);
						break;
					}
				}
			}
			input.close();
		} catch (ArrayIndexOutOfBoundsException a) {
			System.err.println("ERROR: NO ARGUMENTS SPECIFIED");
		} catch (FileNotFoundException e) {
			System.err.println("ERROR: FILE NOT FOUND");
		}
    }

	public boolean relExp(String name, Scanner line, LinkedList<String> errorsList) {
		boolean recognizeCmd = false;
		String nextWord = nextSymbol(line);
		if (nextWord.equals("=")) {
			String cmd = nextSymbol(line);
			switch(cmd) {
				case "PROJECT":
					recognizeCmd = true;
					cmdProject pro = new cmdProject(line, db, errorsList);
					break;
				case "SELECT":
					recognizeCmd = true;
					cmdSelect s = new cmdSelect(line, db, errorsList);
					break;
				case "JOIN":
					recognizeCmd = true;
					cmdJoin j = new cmdJoin(line, db, errorsList);
					break;
				case "COPY":
					recognizeCmd = true;
					cmdCopy c = new cmdCopy(line, db, errorsList);
					break;
			}
			if (recognizeCmd) {
            db.getTmpRelations().getLast().setTitle(name);
			} else {
				errorsList.add("UNRECOGNIZED COMMAND \"" + cmd + "\"");
			}
		} else {
			errorsList.add("UNRECOGNIZED COMMAND \"" + name + "\"");
		}

		return recognizeCmd;
	}

    public String nextSymbol(Scanner read) {
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
		else if (a == '<' || a == '>' || a == '~') {
         better += a;
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
			else if (a == '<' || a == '>' || a == '~') {
            better += a;
			}
            else{
               better += a;
            }
         }
      }

            
      return better;
    }
}