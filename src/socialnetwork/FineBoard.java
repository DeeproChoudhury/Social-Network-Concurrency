package socialnetwork;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lineardatastructures.FineSequentialSet;
import socialnetwork.domain.Board;
import socialnetwork.domain.Message;

public class FineBoard implements Board {

  private final Function<Integer, Integer> maxIntMinus = e -> Integer.MAX_VALUE - e - 1;

  private final FineSequentialSet<Message> fineSequentialSet =
      new FineSequentialSet<>(maxIntMinus.compose(Message::getMessageId));

  @Override
  public boolean addMessage(Message message) {
    return fineSequentialSet.add(message);
  }

  @Override
  public boolean deleteMessage(Message message) {
    return fineSequentialSet.remove(message).isPresent();
  }

  @Override
  public int size() {
    return fineSequentialSet.size();
  }

  @Override
  public List<Message> getBoardSnapshot() {
    return fineSequentialSet.possibleSnapshot();
  }

  @Override
  public String toString() {
    return "FineBoard{"
        + " m="
        + getBoardSnapshot().stream().map(Message::getMessageId).collect(Collectors.toList())
        + "}";
  }
}
