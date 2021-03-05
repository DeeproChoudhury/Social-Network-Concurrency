package socialnetwork;

public class Node<E> {
  int id;
  Node<E> next;
  E data;

  Node(Node<E> next, E data) {
    this.next = next;
    this.data = data;
  }

  public void setNext(Node<E> next) {
    this.next = next;
  }

  public Node<E> getNext() {
    return next;
  }

  public void setData(E data) {
    this.data = data;
  }

  public E getData() {
    return data;
  }

}
