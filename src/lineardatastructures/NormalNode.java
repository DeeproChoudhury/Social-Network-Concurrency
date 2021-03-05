package lineardatastructures;

public class NormalNode<E> {
  private E item;
  private int key;
  private NormalNode<E> next;


  public NormalNode(E item, int key) {
    this(item, key, null);
  }

  public NormalNode(E item, int key, NormalNode<E> next) {
    this.item = item;
    this.key = key;
    this.next = next;
  }

  public E getItem() {
    return item;
  }

  public int getKey() {
    return key;
  }

  public NormalNode<E> getNext() {
    return next;
  }

  public void setNext(NormalNode<E> next) {
    this.next = next;
  }

}
