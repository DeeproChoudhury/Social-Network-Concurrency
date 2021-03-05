package lineardatastructures;

import socialnetwork.Node;

public class SequentialNode<E>{

  private E item;
  protected int key;
  protected SequentialNode<E> next;

  public SequentialNode(E item, int key) {
    this(item, key, null);
  }

  protected SequentialNode(E item, int key, SequentialNode<E> next) {
    this.item = item;
    this.key = key;
    this.next = next;
  }

  public E item() {
    return item;
  }

  public int key() {
    return key;
  }

  public SequentialNode<E> next() {
    return next;
  }

  public void setItem(E item) {
    this.item = item;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public void setNext(SequentialNode<E> next) {
    this.next = next;
  }

}
