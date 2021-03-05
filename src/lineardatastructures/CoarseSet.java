package lineardatastructures;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

public class CoarseSet<E> extends SequentialSet<E> {
  private Lock lock = new ReentrantLock();

  public CoarseSet(Function<E, Integer> idFunction) {
    super(idFunction);
  }

  @Override
  public List<E> getAllNodes() {
    lock.lock();
    try {
      return super.getAllNodes();
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean contains(E item) {
    lock.lock();
    try {
      return super.contains(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean add(E item) {
    lock.lock();
    try {
      return super.add(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public boolean remove(E item) {
    lock.lock();
    try {
      return super.remove(item);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public int size() {
    lock.lock();
    try {
      return super.size();
    } finally {
      lock.unlock();
    }
  }


}
