import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	private Node<T> head;
	private Node<T> current;

	public LinkedListMultiset() {}

	public void add(T item) {
		if ( head == null ) {
			Node<T> node = new Node( item );
			head = node;
			current = node;
			return;
		}

		Node<T> existing = this.find( item );

		if ( existing != null ) {
			existing.incrementCount();
			return;
		}

		Node<T> node = new Node( current, null, item );
		current.setTail( node );
		current = node;
	}


	public int search(T item) {
		Node<T> node = this.find( item );
		if (node != null ) {
			return node.getCount();
		}

		return 0;
	}

	public Node<T> find(T item) {
		Node<T> position = head;

		while ( position.getTail() != null ) {
			if ( position.getData() == item || position.getData().equals( item )) {
				return position;
			}

			position = position.getTail();
		}

		if ( position != null && ( position.getData() == item || position.getData().equals( item )) ) {
			return position;
		}

		return null;
	}


	public void removeOne(T item) {
		// Implement me!
	} // end of removeOne()


	public void removeAll(T item) {
		// Implement me!
	} // end of removeAll()


	public void print(PrintStream out) {
		Node<T> position = head;

		while ( position.getTail() != null ) {
			out.printf( "%s | %d\n", position.getData(), position.getCount());
			position = position.getTail();
		}

		if ( position != null ) {
			out.printf( "%s | %d\n", position.getData(), position.getCount());
		}
	} // end of print()

} // end of class LinkedListMultiset
