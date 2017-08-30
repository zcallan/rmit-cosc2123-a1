import java.io.PrintStream;
import java.util.*;

class Vertex<T> {
  private T data;
  private Vertex<T> parent;
  private Vertex<T> left;
  private Vertex<T> right;
  private int count = 1;

  public Vertex(T data) {
    this.data = data;
  }

  public Vertex(Vertex<T> left, Vertex<T> right, T data) {
    this.left = left;
    this.right = right;
    this.data = data;
  }

  public Vertex<T> getLeft() {
    return this.left;
  }

  public Vertex<T> getRight() {
    return this.right;
  }

  public Vertex<T> getParent() {
    return this.parent;
  }

  public T getData() {
    return this.data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public void setLeft(Vertex<T> vertex) {
    this.left = vertex;

    if ( this.left != null ) {
      this.left.setParent( this );
    }
  }

  public void setRight(Vertex<T> vertex) {
    this.right = vertex;

    if ( this.right != null ) {
      this.right.setParent( this );
    }
  }

  public void setParent(Vertex<T> vertex) {
    this.parent = vertex;
  }

  public void incrementCount() {
    this.count++;
  }

  public void decrementCount() {
    if ( this.count > 0 ) {
      this.count--;
    }
  }

  public void print(PrintStream out) {
    out.printf( "%s | %d\n", this.getData(), this.getCount());

    if ( this.getLeft() != null ) {
      this.getLeft().print(out);
    }

    if ( this.getRight() != null ) {
      this.getRight().print(out);
    }
  }

  public int numberOfChildren() {
    int count = 0;

    if ( this.getLeft() != null ) {
      count++;
    }

    if ( this.getRight() != null ) {
      count++;
    }

    return count;
  }

  public Vertex<T> findSmallestChild() {
    Vertex<T> parent = null;
    Vertex<T> pos = this;

    while ( pos != null ) {
      parent = pos;
      pos = pos.getLeft();
    }

    return parent;
  }
}
