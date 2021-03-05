package lineardatastructures;

import socialnetwork.Node;

public class SequentialNode<E> implements NodeForSet<E>{

  private E item;
  private int key;
  private NodeForSet<E> next;

  public SequentialNode(E item) {
    this(item, null);
  }

  public SequentialNode(E item, NodeForSet<E> next) {
    this(item, item.hashCode(), next);
  }

  protected SequentialNode(E item, int key, NodeForSet<E> next) {
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

  public NodeForSet<E> next() {
    return next;
  }

  public void setItem(E item) {
    this.item = item;
  }

  public void setKey(int key) {
    this.key = key;
  }

  public void setNext(NodeForSet<E> next) {
    this.next = next;
  }

}
