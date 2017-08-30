import java.util.*;
import java.io.*;


public class DataGenerator {
  private List<String> shoppingList = new ArrayList<String>();
  private String groceriesFileName = "groceries.txt";
  
  public DataGenerator() {}
  
  public List<String> getShoppingList() throws IOException {
    FileInputStream inputStream = new FileInputStream( this.groceriesFileName );
    BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ));
    
    try {
      String line;
      
      while (( line = br.readLine()) != null ) {
        this.shoppingList.add( line );
      }
    } finally {
      br.close();
    }
    
    Collections.shuffle( this.shoppingList );
    
    return this.shoppingList;
  }
}
