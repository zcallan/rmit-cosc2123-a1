import java.util.*;
import java.io.*;


public class ReportEvaluator {
  protected static final String progName = "ReportEvaluator";
  protected static DataGenerator generator = new DataGenerator();
  protected static final PrintStream outStream = System.out;

  public static void main( String[] args ) {
    if ( args.length != 6 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }

    String scenario = args[0];
    int items = Integer.parseInt( args[1] );
    String implementationType = args[2];
    int runs = Integer.parseInt( args[3] );
    int removes = Integer.parseInt( args[4] );
    int searches = Integer.parseInt( args[5] );
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

    switch ( implementationType ) {
      case "linkedlist":
        break;
      case "sortedlinkedlist":
        break;
      case "bst":
        break;
      case "hash":
        break;
      case "baltree":
        break;
      default:
        System.err.println( "Unknown implmementation type." );
        printUsage();
        return;
    }

    runScenario( implementationType, runs, inputs, removes, searches );
  }

  public static void printUsage() {
    System.err.println( progName + ": <scenario> = <shoppingList | classList> <items> <implementation> <runs> <removes> <searches>" );
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

  public static void runScenario( String implementationType, int runs, List<String> inputs, int removes, int searches ) {
    if ( removes > inputs.size() || searches > inputs.size() ) {
      System.err.println( "Number of removes and number of searches must be less than number of items" );
      System.exit( 1 );
    }

    long total = 0;

    for (int i = 0; i < runs; i++) {
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

      total += doRun( multiset, inputs, removes, searches );
    }

    System.out.println(( total / runs ));
  }

  public static long doRun( Multiset<String> multiset, List<String> inputs, int removes, int searches ) {
    long startTime = System.nanoTime();

    /* Do the adds */
    for ( int i = 0; i < inputs.size(); i++ ) {
      multiset.add(inputs.get(i));
    }

    /* Do the removes */
    Random random = new Random();
    for ( int i = 0; i < removes; i++ ) {
      multiset.removeOne(inputs.get(random.nextInt(inputs.size())));
    }

    /* Do the searches */
    for ( int i = 0; i < searches; i++ ) {
      multiset.search(inputs.get(random.nextInt(inputs.size())));
    }

    long endTime = System.nanoTime();
    return endTime - startTime;
  }
}
