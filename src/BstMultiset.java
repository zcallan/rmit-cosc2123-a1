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
			int compareResult = ((String) pos.getData()).compareTo( (String) item );

			if (compareResult > 0) {
				/* Move to the left */
				pos = pos.getLeft();
				continue;
			}

			if (compareResult == 0) {
				/* Increase the count */
				pos.incrementCount();
				return;
			}

			if (compareResult < 0) {
				/* Move to the right */
				pos = pos.getRight();
				continue;
			}
		}

		/* Insert the vertex */
		if ( pos == null ) {
			int compareResult = ((String) parent.getData()).compareTo( (String) item );
			if (compareResult > 0) {
				/* Insert left */
				parent.setLeft( vertex );
				return;
			}

			if (compareResult < 0) {
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
			int compareResult = ((String) pos.getData()).compareTo( (String) item );

			if (compareResult > 0) {
				/* Move to the left */
				pos = pos.getLeft();
				continue;
			}

			if (compareResult == 0) {
				return pos;
			}

			if (compareResult < 0) {
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

		if ( vertex == null ) {
			return;
		}

		vertex.decrementCount();

		if ( vertex.getCount() == 0 ) {
			this.removeAll( item );
		}
	}


	public void removeAll(T item) {
		Vertex<T> vertex = this.find( item );

		if ( vertex == null ) {
			return;
		}

		this.removeVertex( vertex );
	}

	public void removeVertex(Vertex<T> vertex) {
		/* Check if the vertex has no childen */
		if ( vertex.numberOfChildren() == 0 ) {
				/* Check whether it is the root vertex */
				if ( vertex == this.rootVertex ) {
					this.rootVertex = null;
					return;
				}

				/* Not the node so delete from the parent */
				Vertex<T> parent = vertex.getParent();

				if ( parent.getLeft() == vertex ) {
					parent.setLeft(null);
					return;
				}

				if ( parent.getRight() == vertex ) {
					parent.setRight(null);
					return;
				}
		}

		/* Check if the vertex has one child */
		if ( vertex.numberOfChildren() == 1 ) {
			/* Get the single child */
			Vertex<T> child = null;

			if ( vertex.getLeft() != null ) {
				child = vertex.getLeft();
			} else {
				child = vertex.getRight();
			}

			/* Check if the vertex is the root vertex */
			if ( vertex == this.rootVertex ) {
				this.rootVertex = child;
				return;
			}

			/* Not the root vertex */
			Vertex<T> parent = vertex.getParent();

			if ( parent.getLeft() == vertex ) {
				parent.setLeft(child);
				return;
			}

			if ( parent.getRight() == vertex ) {
				parent.setRight(child);
				return;
			}
		}

		/* Check if the vertex has two children */
		if ( vertex.numberOfChildren() == 2 ) {
			/* Find the smallest value on the right hand side */
			Vertex<T> smallest = vertex.getRight().findSmallestChild();

			/* Delete smallest node */
			this.removeVertex( smallest );

			/* Swap the smallest and the vertex to be deleted */
			vertex.setData( smallest.getData());
			vertex.setCount( smallest.getCount());
		}
	}


	public void print(PrintStream out) {
		if ( this.rootVertex != null ) {
			this.rootVertex.print( out );
		}
	}

}
