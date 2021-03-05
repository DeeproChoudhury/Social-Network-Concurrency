package socialnetwork;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lineardatastructures.FineSequentialSet;
import socialnetwork.domain.Board;
import socialnetwork.domain.Message;

public class FineBoard implements Board {

  private final Function<Integer, Integer> decreasingOrder = i -> Integer.MAX_VALUE - i - 1;

  private final FineSequentialSet<Message> messageSet =
      new FineSequentialSet<>(decreasingOrder.compose(Message::getMessageId));

  @Override
  public boolean addMessage(Message message) {
    return messageSet.add(message);
  }

  @Override
  public boolean deleteMessage(Message message) {
    return messageSet.remove(message).isPresent();
  }

  @Override
  public int size() {
    return messageSet.size();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return messageSet.possibleSnapshot();
  }

  @Override
  public String toString() {
    return "FineBoard{"
        + " m="
        + getBoardSnapshot().stream().map(Message::getMessageId).collect(Collectors.toList())
        + "}";
  }
}
