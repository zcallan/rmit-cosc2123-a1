import java.util.*;


public class ReportEvaluator {
  protected static final String progName = "ReportEvaluator";
  
  public static void printUsage() {
    System.err.println( progName + ": <scenario> = <shoppingList | goFuckYourself>" );
    System.exit( 1 );
  }
  
  public static void evaluateShoppingList() {
    DataGenerator generator = new DataGenerator();
    
    try {
      List<String> groceryList = generator.getShoppingList();
      
      for ( String item : groceryList ) {
        System.out.println( item );
      }
    } catch (Exception e) {
      System.out.println( e );
    }
  }
  
  public static void main( String[] args ) {
    if ( args.length != 1 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }
    
    String scenario = args[0];
    
    switch ( scenario ) {
      case "shoppingList":
        evaluateShoppingList();
        break;
      
      default:
        System.err.println( "Unknown scenario." );
        printUsage();
    }
  }
}
