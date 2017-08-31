import java.util.*;
import java.io.*;


public class ReportEvaluator {
  protected static final String progName = "ReportEvaluator";
  protected static DataGenerator generator = new DataGenerator();
  protected static final PrintStream outStream = System.out;

  public static void printUsage() {
    System.err.println( progName + ": <scenario> = <shoppingList | classList>" );
    System.exit( 1 );
  }

  public static List<String> evaluateShoppingList( int multiplesOfArray ) {
    try {
      List<String> groceryList = generator.getShoppingList();

      for ( int i = 0; i < multiplesOfArray; i++ ) {
        groceryList.addAll( generator.getShoppingList() );
      }

      Collections.shuffle( groceryList );

      return groceryList;
    } catch (Exception e) {
      System.out.println( e );
    }

    return new ArrayList<String>();
  }

  public static List<String> evaluateClassList( int multiplesOfArray ) {
    try {
      List<String> classList = generator.getClassList();

      for ( int i = 0; i < multiplesOfArray; i++ ) {
        classList.addAll( generator.getClassList() );
      }

      Collections.shuffle( classList );

      return classList;
    } catch (Exception e) {
      System.out.println( e );
    }

    return new ArrayList<String>();
  }

  public static void main( String[] args ) {
    if ( args.length != 3 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }

    String scenario = args[0];
    int multiplesOfArray = Integer.parseInt( args[1] );
    String implementationType = args[2];
    List<String> inputs;

    switch ( scenario ) {
      case "shoppingList":
        inputs = evaluateShoppingList( multiplesOfArray );
        break;

      case "classList":
        inputs = evaluateClassList( multiplesOfArray );
        break;

      default:
        System.err.println( "Unknown scenario." );
        printUsage();
    }

    Multiset<String> multiset = null;
    switch ( implementationType ) {
      case "linkedlist":
        multiset = new LinkedListMultiset<String>();
        break;
      case "sortedlinkedlist":
        multiset = new SortedLinkedListMultiset<String>();
        break;
      case "bst":
        multiset = new BstMultiset<String>();
        break;
      case "hash":
        multiset = new HashMultiset<String>();
        break;
      case "baltree":
        multiset = new BalTreeMultiset<String>();
        break;
      default:
        System.err.println( "Unknown implmementation type." );
        printUsage();
    }
  }
}
