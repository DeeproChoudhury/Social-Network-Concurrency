package lineardatastructures;

public interface NodeForSet<T> {

    T item();

    int key();

    NodeForSet<T> next();

    void setItem(T item);

    void setKey(int key);

    void setNext(NodeForSet<T> next);
  }

