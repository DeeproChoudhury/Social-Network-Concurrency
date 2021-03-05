package socialnetwork;

import com.sun.source.tree.NewArrayTree;
import java.util.function.Function;

public class CoarseLinkedList<E> {
  private Node<E> head;
  private Node<E> tail;
  private int size;
  private final Function<E, Integer> idFunction;

  public CoarseLinkedList(E data, Function<E, Integer> idFunction) {
    head = new Node<E>(null, data);
    this.idFunction = idFunction;
    size = 0;
  }

  public void setTail() {
    Node<E> currNode = head;
    while (currNode.next != null) {
      currNode = head.getNext();
    }
    tail = currNode;
  }

  public Node<E> getTail() {
    return tail;
  }

  public Node<E> getHead() {
    return head;
  }

  public Node<E> setHead(E data) {
    return new Node<>(head.getNext(), data);
  }

  public void incrementSize() {
    size++;
  }

  synchronized public void add(E input) {
    int id = idFunction.apply(input);
    Node<E> currNode = head.getNext();
    Node<E> prevNode = head;
    if (idFunction.apply(prevNode.getElem()) > id) {
      head = new Node<>(prevNode, input);
      return;
    }
    while (idFunction.apply(prevNode.getElem()) < id && prevNode.getNext() != null) {
      prevNode = prevNode.getNext();
      currNode = prevNode.getNext();
    }
    Node<E> newNode = new Node<>(currNode, input);
    prevNode.setNext(newNode);
  }

  synchronized public void remove(E input) {
    int id = idFunction.apply(input);
    Node<E> currNode = head.getNext();
    Node<E> prevNode = head;
    if (idFunction.apply(prevNode.getData()) == id) {
      head = prevNode.getNext();
    }
    while (idFunction.apply(prevNode.getData()) != id) {
      prevNode = prevNode.getNext();
      currNode = currNode.getNext();
    }
    prevNode.setNext(currNode.getNext());
  }

  private class Node<E> {

    private E elem;
    private Node<E> next;

    public Node(final E elem) {
      this(elem, null);
    }

    public Node(final E elem, final Node<E> next) {
      this.elem = elem;
      this.next = next;
    }

    public E getElem() {
      return elem;
    }

    public void setElem(final E elem) {
      this.elem = elem;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(final Node<E> next) {
      this.next = next;
    }
  }

}
