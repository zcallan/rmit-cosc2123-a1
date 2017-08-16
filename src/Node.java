class Node<T> {
    private T data;
    private Node<T> head;
    private Node<T> tail;

    public Node(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    public void setHead(Node<T> node) {
        this.head = node;
    }

    public void setTail(Node<T> node) {
        this.tail = node;
    }
}
