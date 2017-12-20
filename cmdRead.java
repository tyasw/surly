/* cmdRead.java
 *
 * Created by William Tyas
 * 11/20/17
 *
 * This command reads commands from another file.
 */
import java.util.*;
import java.io.*;

public class cmdRead extends base {
	public cmdRead(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		surlyParse s = new surlyParse(db);
		String[] newArgs = {nextSymbol(line)};
		s.parse(newArgs, errorsList);
	}
}