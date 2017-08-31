import java.util.*;
import java.io.*;


public class ReportEvaluator {
  protected static final String progName = "ReportEvaluator";
  protected static DataGenerator generator = new DataGenerator();
  protected static final PrintStream outStream = System.out;

  public static void main( String[] args ) {
    if ( args.length != 4 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }

    String scenario = args[0];
    int items = Integer.parseInt( args[1] );
    String implementationType = args[2];
    int runs = Integer.parseInt( args[3] );
    List<String> inputs = null;

    switch ( scenario ) {
      case "shoppingList":
        inputs = evaluateShoppingList( items );
        break;

      case "classList":
        inputs = evaluateClassList( items );
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
        return;
    }

    runScenario( multiset, scenario, runs, inputs );
  }

  public static void printUsage() {
    System.err.println( progName + ": <scenario> = <shoppingList | classList> <listMultiples> <implementation> <runs>" );
    System.exit( 1 );
  }

  public static List<String> evaluateShoppingList( int items ) {
    try {
      List<String> groceryList = generator.getShoppingList();
      List<String> list = new ArrayList<String>();
      Random random = new Random();

      for ( int i = 0; i < items; i++ ) {
        list.add( groceryList.get(random.nextInt(groceryList.size())));
      }

      Collections.shuffle( list );

      return list;
    } catch (Exception e) {
      System.out.println( e );
    }

    return new ArrayList<String>();
  }

  public static List<String> evaluateClassList( int items ) {
    try {
      List<String> classList = generator.getClassList();
      List<String> list = new ArrayList<String>();
      Random random = new Random();

      for ( int i = 0; i < items; i++ ) {
        list.add( classList.get(random.nextInt(classList.size())));
      }

      Collections.shuffle( list );

      return list;
    } catch (Exception e) {
      System.out.println( e );
    }

    return new ArrayList<String>();
  }

  public static void runScenario( Multiset<String> multiset, String scenario, int runs, List<String> inputs ) {
    System.out.println(scenario);
    System.out.println(runs);
    System.out.println(inputs.size());
  }
}
