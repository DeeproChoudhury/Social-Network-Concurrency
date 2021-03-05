package lineardatastructures;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FineSequentialNode<E> {
  private Lock lock = new ReentrantLock();
  private E item;
  private int key;
  private volatile FineSequentialNode<E> next;
  private volatile boolean valid;

  public FineSequentialNode(E item, int key) {
    this(item, key, null);
  }

  public FineSequentialNode(E item, int key, FineSequentialNode<E> next) {
    this.item = item;
    this.key = key;
    this.next = next;
    this.setValid();
  }

  public boolean isValid() {
    return valid;
  }

  public void setValid() {
    this.valid = true;
  }

  public void setInValid() {
    this.valid = false;
  }

  public void lock() {
    lock.lock();
  }

  public void unlock() {
    lock.unlock();
  }

  public E item() {
    return item;
  }

  public int key() {
    return key;
  }

  public FineSequentialNode<E> getNext() {
    return next;
  }

  public void setNext(FineSequentialNode<E> next) {
    this.next = next;
  }

}
