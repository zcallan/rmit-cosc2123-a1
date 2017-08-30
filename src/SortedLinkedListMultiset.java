import java.io.PrintStream;
import java.util.*;

public class SortedLinkedListMultiset<T> extends LinkedListMultiset<T>
{
	public void add(T item) {
		/* Checks to see whether this is the first item in the list */
		if ( head == null ) {
			/* Creates a new node with no head or tail */
			Node<T> node = new Node<T>( item );
			/* Set the head and current node variables to the newly created node */
			this.head = node;
			this.tail = node;
			return;
		}

		/* Check whether this item already exists in the LinkedListMultiset */
		Node<T> existing = this.find( item );

		/* If it does exist increment the count */
		if ( existing != null ) {
			existing.incrementCount();
			return;
		}

		/* If this.tail is alphabetically before the new node then loop back through */
		Node<T> pos = this.head;
		Node<T> prev = null;

		if ( pos.getData() instanceof String ) {
			while ( pos != null && ((String) pos.getData()).compareTo( (String) item ) < 1 ) {
				prev = pos;
				pos = pos.getTail();
			}

			if ( pos == null ) {
				Node<T> node = new Node<T>( prev, pos, item );
				prev.setTail( node );
			} else {
				Node<T> node = new Node<T>( prev, pos, item );
				if ( pos.getHead() == null ) {
					this.head = node;
				}

				pos.setHead( node );

				if ( prev != null ) {
					prev.setTail( node );
				}
			}
		}
	}
}
