import java.io.PrintStream;
import java.util.*;

public class BstMultiset<T> extends Multiset<T>
{
  private Vertex<T> rootVertex;

	public BstMultiset() {}

	public void add(T item) {
		Vertex<T> vertex = new Vertex<T>(item);

		if ( rootVertex == null ) {
			this.rootVertex = vertex;
			return;
		}

		Vertex<T> parent = null;
		Vertex<T> pos = this.rootVertex;

		/* Start the root vertex and insert */
		while ( pos != null ) {
			parent = pos;

			if (((String) pos.getData()).compareTo( (String) item ) > 0) {
				/* Move to the left */
				pos = pos.getLeft();
				continue;
			}

			if (((String) pos.getData()).compareTo( (String) item ) == 0) {
				/* Increase the count */
				pos.incrementCount();
				return;
			}

			if (((String) pos.getData()).compareTo( (String) item ) < 0) {
				/* Move to the right */
				pos = pos.getRight();
				continue;
			}
		}

		/* Insert the vertex */
		if ( pos == null ) {
			if (((String) parent.getData()).compareTo( (String) item ) > 0) {
				/* Insert left */
				parent.setLeft( vertex );
				return;
			}

			if (((String) parent.getData()).compareTo( (String) item ) < 0) {
				/* Insert right */
				parent.setRight( vertex );
				return;
			}
		}
	}

	public Vertex<T> find(T item) {
		Vertex<T> parent = null;
		Vertex<T> pos = this.rootVertex;

		while ( pos != null ) {
			parent = pos;

			if (((String) pos.getData()).compareTo( (String) item ) > 0) {
				/* Move to the left */
				pos = pos.getLeft();
				continue;
			}

			if (((String) pos.getData()).compareTo( (String) item ) == 0) {
				return pos;
			}

			if (((String) pos.getData()).compareTo( (String) item ) < 0) {
				/* Move to the right */
				pos = pos.getRight();
				continue;
			}
		}

		return null;
	}

	public int search(T item) {
		Vertex<T> vertex = this.find( item );

		if ( vertex != null ) {
			return vertex.getCount();
		}

		return 0;
	}


	public void removeOne(T item) {
		Vertex<T> vertex = this.find( item );
		if ( item != null ) {

		}
	}


	public void removeAll(T item) {

	}


	public void print(PrintStream out) {
		if ( this.rootVertex != null ) {
			this.rootVertex.print( out );
		}
	}

}
