package lineardatastructures;

import java.util.ArrayList;
import java.util.List;
import socialnetwork.Node;

public class SequentialSet<E> {

  int size = 0;
  private NodeForSet<E> head, tail;

  public SequentialSet() {
    head = new SequentialNode<>(null, Integer.MIN_VALUE, null);
    tail = new SequentialNode<>(null, Integer.MAX_VALUE, null);
    head.setNext(tail);
  }

  public E getFromPosition(int key) {
    NodeForSet<E> curr = head;
    while (curr != null && curr.key() != key) {
      curr = curr.next();
    }
    return curr.item();
  }


  public List<E> getAllNodes() {
    List<E> nodeList = new ArrayList<>();
    NodeForSet<E> curr = head;
    while (curr != null && curr.item()!= null) {
      nodeList.add(curr.item());
      System.out.println(curr.item());
      curr = curr.next();
    }
    return nodeList;
  }

  private Position<E> find(NodeForSet<E> start, int key) {
    NodeForSet<E> pred, curr;
    curr = start;
    do {
      pred = curr;
      curr = curr.next();
    } while (curr.key() < key);  // until curr.key >= key
    return new Position<E>(pred, curr);
  }

  public boolean contains(E item) {
    NodeForSet<E> node = new SequentialNode<>(item);
    Position<E> expectedPosition = find(head, node.key());

    return expectedPosition.curr.key() == node.key();
  }

  public boolean add(E item) {
    NodeForSet<E> node = new SequentialNode<>(item);
    Position<E> where = find(head, node.key());
    if (where.curr.key() == node.key()) {
      return false;
    } else {
      node.setNext(where.curr);
      where.pred.setNext(node);
      size += 1;
      return true;
    }
  }

  public boolean remove(E item) {
    NodeForSet<E> node = new SequentialNode<>(item);
    Position<E> where = find(head, node.key());
    if (where.curr.key() > node.key()) {
      return false;
    } else {
      where.pred.setNext(where.curr.next());
      size -= 1;
      return true;
    }
  }

  public int size() {
    return size;
  }


  private static class Position<T> {

    public final NodeForSet<T> pred, curr;

    public Position(NodeForSet<T> pred, NodeForSet<T> curr) {
      this.pred = pred;
      this.curr = curr;
    }
  }

}
