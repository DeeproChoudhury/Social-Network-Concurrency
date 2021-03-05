package lineardatastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FineSequentialSet<E> {

  protected AtomicInteger size = new AtomicInteger(0);
  protected FineSequentialNode<E> head;
  protected FineSequentialNode<E> tail;
  private final Function<E, Integer> idFunction;

  public FineSequentialSet(Function<E, Integer> idFunction) {
    this.idFunction = idFunction;
    head = new FineSequentialNode<>(null, Integer.MIN_VALUE, null);
    tail = new FineSequentialNode<>(null, Integer.MAX_VALUE, null);
    head.setNext(tail);
  }

  private boolean valid(FineSequentialNode<E> pred, FineSequentialNode<E> curr) {
    return pred.isValid() && curr.isValid() && pred.getNext() == curr;
  }

  protected Position<E> find(FineSequentialNode<E> start, int key) {
    FineSequentialNode<E> pred;
    FineSequentialNode<E> curr;
    curr = start;
    do {
      pred = curr;
      curr = curr.getNext();
    } while (curr.key() < key);

    return new Position<E>(pred, curr);
  }


  public boolean add(E item) {
    FineSequentialNode<E> node = new FineSequentialNode<E>(item, idFunction.apply(item));
    do {
      Position<E> where = find(head, node.key());
      FineSequentialNode<E> pred = where.pred;
      FineSequentialNode<E> curr = where.curr;
      pred.lock();
      curr.lock();
      try {
        if (valid(pred, curr)) {
          if (where.curr.key() == node.key()) {
            return false;
          } else {
            node.setNext(where.curr);
            where.pred.setNext(node);
            size.incrementAndGet();
            return true;
          }
        }
      } finally {
        pred.unlock();
        curr.unlock();
      }
    } while (true);
  }

  public Optional<E> remove(E item) {
    FineSequentialNode<E> node = new FineSequentialNode<>(item, idFunction.apply(item));
    do {
      Position<E> where = find(head, node.key());
      FineSequentialNode<E> pred = where.pred;
      FineSequentialNode<E> curr = where.curr;
      pred.lock();
      curr.lock();
      try {
        if (valid(pred, curr)) {
          if (where.curr.key() > node.key()) {
            return Optional.empty();
          } else {
            curr.setInValid();
            E nodeItem = curr.item();
            where.pred.setNext(where.curr.getNext());
            size.decrementAndGet();
            return Optional.of(nodeItem);
          }
        }
      } finally {
        pred.unlock();
        curr.unlock();
      }
    } while (true);
  }

  public Optional<E> poll() {
    do {
      FineSequentialNode<E> pred = head;
      FineSequentialNode<E> curr = head.getNext();
      pred.lock();
      curr.lock();
      try {
        if (curr.key() == tail.key()) {
          return Optional.empty();
        }
        if (curr.isValid()) {
          curr.setInValid();
          E nodeItem = curr.item();
          pred.setNext(curr.getNext());
          size.decrementAndGet();
          return Optional.of(nodeItem);
        }
      } finally {
        pred.unlock();
        curr.unlock();
      }
    } while (true);
  }

  public boolean contains(E item) {
    FineSequentialNode<E> node = new FineSequentialNode<>(item, idFunction.apply(item));
    do {
      Position<E> where = find(head, node.key());
      FineSequentialNode<E> pred = where.pred;
      FineSequentialNode<E> curr = where.curr;
      pred.lock();
      curr.lock();
      try {
        if (valid(pred, curr)) {
          return where.curr.key() == node.key();
        }
      } finally {
        pred.unlock();
        curr.unlock();
      }
    } while(true);
  }

  public int size() {
    return size.get();
  }

  public List<E> possibleSnapshot() {
    List<FineSequentialNode<E>> snapshot = new ArrayList<>(size());
    FineSequentialNode<E> curr = head;
    while ((curr = curr.getNext()) != tail) {
      snapshot.add(curr);
    }
    return snapshot.stream()
        .filter(FineSequentialNode::isValid)
        .map(FineSequentialNode::item)
        .collect(Collectors.toList());
  }

  private static class Position<T> {
    public final FineSequentialNode<T> pred;
    public final FineSequentialNode<T> curr;

    public Position(FineSequentialNode<T> pred, FineSequentialNode<T> curr) {
      this.pred = pred;
      this.curr = curr;
    }
  }
}
