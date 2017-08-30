import java.util.*;
import java.io.*;


public class DataGenerator {
  private String groceriesFileName = "groceries.txt";
  private String classListFileName = "class-list.txt";
  
  public DataGenerator() {}
  
  public List<String> getShoppingList() throws IOException {
    FileInputStream inputStream = new FileInputStream( this.groceriesFileName );
    BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ));
    List<String> shoppingList = new ArrayList<String>();
    
    try {
      String line;
      
      while (( line = br.readLine()) != null ) {
        shoppingList.add( line );
      }
    } finally {
      br.close();
    }
    
    return shoppingList;
  }
  
  public List<String> getClassList() throws IOException {
    FileInputStream inputStream = new FileInputStream( this.classListFileName );
    BufferedReader br = new BufferedReader( new InputStreamReader( inputStream ));
    List<String> classList = new ArrayList<String>();
    
    try {
      String line;
      
      while (( line = br.readLine()) != null ) {
        classList.add( line );
      }
    } finally {
      br.close();
    }
    
    return classList;
  }
}
