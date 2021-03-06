package socialnetwork;

import java.util.List;
import java.util.function.Function;
import lineardatastructures.CoarseSet;
import socialnetwork.domain.Message;

public class CoarseBoard implements socialnetwork.domain.Board {

  private final Function<Integer, Integer> decreasingOrder = i -> Integer.MAX_VALUE - i - 1;
  CoarseSet<Message> coarseSet = new CoarseSet<>(decreasingOrder.compose(Message::getMessageId));


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
    return coarseSet.possibleSnapshot();
  }
}
