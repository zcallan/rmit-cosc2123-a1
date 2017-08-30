import java.util.*;


public class ReportEvaluator {
  protected static final String progName = "ReportEvaluator";
  protected static DataGenerator generator = new DataGenerator();
  
  public static void printUsage() {
    System.err.println( progName + ": <scenario> = <shoppingList | classList>" );
    System.exit( 1 );
  }
  
  public static void evaluateShoppingList( int multiplesOfArray ) {
    try {
      List<String> groceryList = generator.getShoppingList();
  
      for ( int i = 0; i < multiplesOfArray; i++ ) {
        groceryList.addAll( generator.getShoppingList() );
      }
      
      Collections.shuffle( groceryList );
      
      for ( String item : groceryList ) {
        System.out.println( item );
      }
    } catch (Exception e) {
      System.out.println( e );
    }
  }
  
  public static void evaluateClassList( int multiplesOfArray ) {
    try {
      List<String> classList = generator.g();
      
      for ( int i = 0; i < multiplesOfArray; i++ ) {
        classList.addAll( generator.getClassList() );
      }
  
      Collections.shuffle( classList );
  
      for ( String item : classList ) {
        System.out.println( item );
      }
    } catch (Exception e) {
      System.out.println( e );
    }
  }
  
  public static void main( String[] args ) {
    if ( args.length != 2 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }
    
    String scenario = args[0];
    int multiplesOfArray = Integer.parseInt( args[1] );
    
    switch ( scenario ) {
      case "shoppingList":
        evaluateShoppingList( multiplesOfArray );
        break;
  
      case "classList":
        evaluateClassList( multiplesOfArray );
        break;
      
      default:
        System.err.println( "Unknown scenario." );
        printUsage();
    }
  }
}
