import java.io.PrintStream;
import java.util.*;

public class LinkedListMultiset<T> extends Multiset<T>
{
	/* Define variables to store references to the head node and tail node */
	public Node<T> head;
	public Node<T> tail;

	public LinkedListMultiset() {}

	/* Adds an item to the LinkedListMultiset */
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

		/**
		 * Item doesn't exist in the LinkedListMultiset, create a new node with
		 * this item, set the tail of the current tail node to reference the newly
		 * created node and set the tail of the LinkedListMultiset to point to it.
		 */
		Node<T> node = new Node<T>( this.tail, null, item );
		this.tail.setTail( node );
		this.tail = node;
	}


	/* Returns the number of the provided item in the LinkedListMultiset */
	public int search(T item) {
		/* Attempt to find the node which contains this item in the list */
		Node<T> node = this.find( item );

		/* If the node exists return the count, otherwise return 0 */
		if (node != null ) {
			return node.getCount();
		}

		return 0;
	}

	/* Attempts to find the node which contains a particular item, otherwise returns null */
	public Node<T> find(T item) {
		/**
		 * Create a variable to store the current position as we search and set it
		 * to be equal to current head of the LinkedListMultiset
		 */
		Node<T> position = this.head;

		/* Continue iterating through the list until their is no more nodes to access */
		while ( position != null ) {
			/* Check whether this node contains the item, if it does return the node */
			if ( position.getData() == item || position.getData().equals( item )) {
				return position;
			}

			/* This node doesn't contain the item, move onto the next node */
			position = position.getTail();
		}

		/* We weren't able to find the node that contains the item, return null */
		return null;
	}

	/* Removes one of the items from the list if it exists */
	public void removeOne(T item) {
		/* Attempt to find the node that contains this item */
		Node<T> node = this.find( item );

		/* If the node exists, decrement the count and remove the node if the count = 0 */
		if (node != null ) {
			node.decrementCount();

			if ( node.getCount() == 0 ) {
				this.removeAll( item );
			}
		}
	}


	/* Removes all of the items that are equal to the provided item */
	public void removeAll(T item) {
		/* Attempt to find the node that contains instances of this item */
		Node<T> node = this.find( item );

		/* If this node doesn't exist return null */
		if ( node == null ) {
			return;
		}

		/* Get the head and the tail of the node associated with this item */
		Node<T> head = node.getHead();
		Node<T> tail = node.getTail();

		/* Update the head of the tail node to be the head of the node associated with this item */
		if ( tail != null ) {
			tail.setHead( head );
		}

		/* Update the tail of the head node to be the tail of the node associated with this item */
		if ( head != null ) {
			head.setTail( tail );
		}

		/**
		 * If this node is the first node in the list (the head) update the head
		 * of the list to be the tail of the node associated with this item
		 */
		if ( node == this.head ) {
			this.head = tail;
		}

		/**
		 * If this node is the last node in the list (the tail) update the tail
		 * of the list to be the head of the node associated with this item
		 */
		if ( node == this.tail ) {
			this.tail = head;
		}
	}

	/* Prints a list of items in the LinkedListMultiset and their counts */
	public void print(PrintStream out) {
		/**
		 * Create a variable to store the current position as we search and set it
		 * to be equal to current head of the LinkedListMultiset
		 */
		Node<T> position = head;

		/* Continue iterating through the list until their is no more nodes to access */
		while ( position != null ) {
			/* Print the node's item and the associated count */
			out.printf( "%s | %d\n", position.getData(), position.getCount());

			/* Move onto the next item */
			position = position.getTail();
		}
	}

}
