package lineardatastructures;

import java.util.stream.IntStream;

public class SequentialArrayList<E> {

  public static final int DEFAULT_INITIAL_SIZE = 100;
  private int size;
  private E[] elements;

  public SequentialArrayList(int initialSize) {
    this.elements = (E[]) new Object[initialSize];
    this.size = 0;
  }

  public SequentialArrayList() {
    this(SequentialArrayList.DEFAULT_INITIAL_SIZE);
  }

  public void add(E element) {
    //if(size<elements.length)
    elements[size] = element;
    size = size + 1;
  }

  public void add(E element, int position) {
    //size<elements.length
    if (position >= 0 && position <= size) {
      shiftSubArrayRight(position);
      elements[position] = element;
      size = size + 1;
    } else {
      // we shall do something here...
    }
  }

  public E get(int position) {
    if (position > 0 && position < size) {
      return elements[position];
    } else {
      return null;
    }
  }


  public E remove(int position) {
    if (position > 0 && position < size) {
      E oldElement = elements[position];
      shiftSubArrayLeft(position);
      size = size - 1;
      return oldElement;
    } else {
      return null;
    }
  }

  private void shiftSubArrayLeft(int position) {
    IntStream.range(position, size).forEach(i -> {
      elements[i] = elements[i + 1];
    });
  }

  private void shiftSubArrayRight(int position) {
    if (size + 1 - position >= 0)
      System.arraycopy(elements, position, elements, position + 1, size + 1 - position);
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

}

