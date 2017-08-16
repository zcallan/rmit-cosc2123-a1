class Node<T> {
  private T data;
  private Node<T> head;
  private Node<T> tail;
  private int count = 1;

  public Node(T data) {
    this.data = data;
  }

  public Node(Node<T> head, Node<T> tail, T data) {
    this.head = head;
    this.tail = tail;
    this.data = data;
  }

  public Node<T> getHead() {
    return this.head;
  }

  public Node<T> getTail() {
    return this.tail;
  }

  public T getData() {
    return this.data;
  }

  public int getCount() {
    return this.count;
  }

  public void setHead(Node<T> node) {
    this.head = node;
  }

  public void setTail(Node<T> node) {
    this.tail = node;
  }

  public void incrementCount() {
    this.count++;
  }

  public void decrementCount() {
    if ( this.count > 0 ) {
      this.count--;
    }
  }
}
