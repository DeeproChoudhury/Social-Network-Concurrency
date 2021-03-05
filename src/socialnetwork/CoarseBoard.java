package socialnetwork;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import lineardatastructures.CoarseSet;
import lineardatastructures.NodeForSet;
import socialnetwork.domain.Message;

public class CoarseBoard implements socialnetwork.domain.Board {
  Lock lock = new ReentrantLock();
  CoarseLinkedList<Message> LinkedList = new CoarseLinkedList<>(null, Message::getMessageId);
  CoarseSet<Message> coarseSet = new CoarseSet<>();


  @Override
  public boolean addMessage(Message message) {
   return coarseSet.add(message);
  }

  @Override
  public boolean deleteMessage(Message message) {
    return coarseSet.remove(message);
  }

  @Override
  public int size() {
    return coarseSet.size();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return coarseSet.getAllNodes();
  }
}
