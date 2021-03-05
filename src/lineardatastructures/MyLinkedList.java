package lineardatastructures;

public class MyLinkedList<E> {
  private Node<E> head;
  private int size;

  public void add(final E e) {
    if (head == null) {
      head = new Node(e);
    } else {
      Node<E> cursor = head;
      while (cursor.getNext() != null) {
        cursor = cursor.getNext();
      }
      cursor.setNext(new Node(e));
    }
    size++;
  }

  public void add(E element, int position) {
    //need to check if position is valid
    if (position == 0) {
      Node<E> previousHead = head;
      head = new Node<>(element, previousHead);
    } else {
      Node<E> cursor = head;
      for (int i = 0; i < position - 1; i++) {
        cursor = cursor.getNext();
      }
      Node<E> newNode = new Node<>(element, cursor.getNext());
      cursor.setNext(newNode);
    }
  }

  public E get(int position) {
    //need to check if position is valid
    if (position == 0) {
      return head.getElem();
    } else {
      Node<E> cursor = head;
      for (int i = 0; i < position; i++) {
        cursor = cursor.getNext();
      }
      return cursor.getElem();
    }
  }

  public E remove(final int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    final E oldValue = get(index);

    if (index == 0) {
      head = head.getNext();
    } else {
      Node<E> cursor = head;
      for (int i = 0; i < index - 1; i++) {
        cursor = cursor.getNext();
      }
      cursor.setNext(cursor.getNext().getNext());

    }
    return oldValue;
  }


  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  private class Node<E> {

    private E elem;
    private Node<E> next;

    public Node(final E elem) {
      this(elem, null);
    }

    public Node(final E elem, final Node<E> next) {
      this.elem = elem;
      this.next = next;
    }

    public E getElem() {
      return elem;
    }

    public void setElem(final E elem) {
      this.elem = elem;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(final Node<E> next) {
      this.next = next;
    }
  }
}
