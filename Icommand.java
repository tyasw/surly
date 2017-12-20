import java.util.*;

public interface Icommand{

   public void run(Scanner line, Database db, LinkedList<String> errorsList);
} 