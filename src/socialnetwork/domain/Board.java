package socialnetwork.domain;

import java.util.List;
import java.util.Queue;

public interface Board {

  boolean addMessage(Message message);

  boolean deleteMessage(Message message);

  int size();

  List<Message> getBoardSnapshot();
}
