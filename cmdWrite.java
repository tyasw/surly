/* cmdWrite.java
 *
 * Created by William Tyas
 * 11/20/17
 *
 * This command writes commands to a file.
 */
import java.util.*;
import java.io.*;

public class cmdWrite extends base {
	public cmdWrite(Scanner line, Database db, LinkedList<String> errorsList) {
		run(line, db, errorsList);
	}

	public void run(Scanner line, Database db, LinkedList<String> errorsList) {
		try {
			String fileName = nextSymbol(line);
			FileOutputStream outFile = new FileOutputStream(fileName, true);
			PrintStream output = new PrintStream(outFile);

			while (line.hasNext()) {
				output.print(line.next() + " ");
			}
			output.println();
			output.close();
			outFile.close();
		} catch (FileNotFoundException f) {
			errorsList.add("ERROR: FILE NOT FOUND");
		} catch (IOException io) {
			errorsList.add("ERROR: IO EXCEPTION");
		}
	}
}