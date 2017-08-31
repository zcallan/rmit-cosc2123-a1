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
  
  public static void processOperations(BufferedReader inReader, Multiset<String> multiset) throws IOException {
    String line;
    int lineNum = 1;
    boolean bQuit = false;
    
    // continue reading in commands until we either receive the quit signal or there are no more input commands
    while (!bQuit && (line = inReader.readLine()) != null) {
      String[] tokens = line.split(" ");
      
      // check if there is at least an operation command
      if (tokens.length < 1) {
        System.err.println(lineNum + ": not enough tokens.");
        lineNum++;
        continue;
      }
      
      String command = tokens[0];
      // determine which operation to execute
      switch (command.toUpperCase()) {
        // add
        case "A":
          if (tokens.length == 2) {
            multiset.add(tokens[1]);
          }
          else {
            System.err.println(lineNum + ": not enough tokens.");
          }
          break;
        // search
        case "S":
          if (tokens.length == 2) {
            int foundNumber = multiset.search(tokens[1]);
          }
          else {
            System.err.println(lineNum + ": not enough tokens.");
          }
          break;
        // remove one instance
        case "RO":
          if (tokens.length == 2) {
            multiset.removeOne(tokens[1]);
          }
          else {
            System.err.println(lineNum + ": not enough tokens.");
          }
          break;
        // remove all instances
        case "RA":
          if (tokens.length == 2) {
            multiset.removeAll(tokens[1]);
          }
          else {
            System.err.println(lineNum + ": not enough tokens.");
          }
          break;
        // print
        case "P":
          multiset.print(outStream);
          break;
        // quit
        case "Q":
          bQuit = true;
          break;
        default:
          System.err.println(lineNum + ": Unknown command.");
      }
      
      lineNum++;
    }
  }
  
  public static void main( String[] args ) {
    if ( args.length != 3 ) {
      System.err.println( "Incorrect number of arguments." );
      printUsage();
    }
    
    String scenario = args[0];
    int multiplesOfArray = Integer.parseInt( args[1] );
    String implementationType = args[2];
    
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
  
    // construct in and output streams/writers/readers, then process each operation.
    try {
      BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
      // process the operations
      processOperations(inReader, multiset);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
