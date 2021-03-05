package lineardatastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SequentialSet<E> {

  protected AtomicInteger size = new AtomicInteger(0);
  private SequentialNode<E> head;
  private SequentialNode<E> tail;
  Function<E, Integer> idFunction;

  public SequentialSet(Function<E, Integer> idFunction) {
    this.idFunction = idFunction;
    head = new SequentialNode<>(null, Integer.MIN_VALUE, null);
    tail = new SequentialNode<>(null, Integer.MAX_VALUE, null);
    head.setNext(tail);
  }

  public Optional<E> poll() {
    if(this.size() == 0) {
      return Optional.empty();
    }

    SequentialNode<E> pred = head;
    SequentialNode<E> curr = head.next();

    if (head.key() == tail.key()) {
      return Optional.empty();
    }
    E oldValue = curr.item();
    pred.setNext(curr.next());
    size.decrementAndGet();
    return Optional.of(oldValue);
  }


  public List<E> getAllNodes() {
    List<E> nodeList = new ArrayList<>();
    SequentialNode<E> curr = head;
    while (curr != null && curr.item()!= null) {
      nodeList.add(curr.item());
      System.out.println(curr.item());
      curr = curr.next();
    }
    return nodeList;
  }

  private Position<E> find(SequentialNode<E> start, int key) {
    SequentialNode<E> pred, curr;
    pred = start;
    curr = start;
    while(curr.key() < key) {
      pred = curr;
      curr = curr.next();
    }
    if (pred == curr) {
      curr = curr.next();
    }
    return new Position<E>(pred, curr);
  }

  public boolean contains(E item) {
    SequentialNode<E> node = new SequentialNode<>(item, idFunction.apply(item));
    Position<E> expectedPosition = find(head, node.key());

    return expectedPosition.curr.key() == node.key();
  }

  public boolean add(E item) {
    SequentialNode<E> node = new SequentialNode<>(item, idFunction.apply(item));
    Position<E> where = find(head, node.key());
    System.out.println(where.pred.key);
    if (where.curr.key() == node.key()) {
      return false;
    } else {
      node.setNext(where.curr);
      where.pred.setNext(node);
      size.incrementAndGet();
      return true;
    }
  }

  public boolean remove(E item) {
    SequentialNode<E> node = new SequentialNode<>(item, idFunction.apply(item));
    Position<E> where = find(head, node.key());
    if (where.curr.key() > node.key()) {
      return false;
    } else {
      where.pred.setNext(where.curr.next());
      size.decrementAndGet();
      return true;
    }
  }

  public int size() {
    return size.get();
  }

  public List<E> possibleSnapshot() {
    List<SequentialNode<E>> snapshot = new ArrayList<>(size());
    SequentialNode<E> curr = head;
    while ((curr = curr.next()) != tail) {
      snapshot.add(curr);
    }
    return snapshot.stream()
        .map(SequentialNode::item)
        .collect(Collectors.toList());
  }


  private static class Position<T> {

    public final SequentialNode<T> pred, curr;

    public Position(SequentialNode<T> pred, SequentialNode<T> curr) {
      this.pred = pred;
      this.curr = curr;
    }
  }

}
