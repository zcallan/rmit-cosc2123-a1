import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
  private Vertex<T> rootVertex;
  
	public BstMultiset() {}

	public void add(T item) {
	  System.out.println( "addsad" );
	  if ( this.rootVertex == null ) {
      this.rootVertex = new Vertex( item );
      System.out.println( "Set root vertex" );
      return;
    }
    
    Vertex<T> vertex = new Vertex( item );
    
    if ( ((String) this.rootVertex.getData()).compareTo( (String) vertex.getData() ) > 0 ) {
      System.out.println( "Setting right vertex" );
      this.rootVertex.setRight( vertex );
    } else {
      System.out.println( "Setting left vertex" );
      this.rootVertex.setLeft( vertex );
    }
	}

	public int search(T item) {
		// Implement me!

		// default return, please override when you implement this method
		return 0;
	}


	public void removeOne(T item) {
		// Implement me!
	}
	
	
	public void removeAll(T item) {
		// Implement me!
	}


	public void print(PrintStream out) {
	  out.println( "Printing, " + ( this.rootVertex == null ) );
	  if ( this.rootVertex != null ) {
	    out.println( "shjdksjdfhslkdhfkjsf" );
    } else {
	    out.println( "No vertices to print." );
    }
	}

}
