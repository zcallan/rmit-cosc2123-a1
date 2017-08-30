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

  public T getData() {
    return this.data;
  }

  public int getCount() {
    return this.count;
  }

  public void setLeft(Vertex<T> vertex) {
    this.left = vertex;
  }

  public void setRight(Vertex<T> vertex) {
    this.right = vertex;
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
    out.println( "test" );
    if ( this.left != null ) {
      out.printf( "Left: %s", (String) this.left.getData() );
    }
    
    if ( this.right != null ) {
      out.printf( "Right: %s", (String) this.right.getData() );
    }
  }
}
