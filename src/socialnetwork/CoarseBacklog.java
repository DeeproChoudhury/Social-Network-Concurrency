package socialnetwork;

import java.util.Optional;
import lineardatastructures.CoarseSet;
import socialnetwork.domain.Backlog;
import socialnetwork.domain.Task;
import lineardatastructures.SequentialSet;

public class CoarseBacklog implements Backlog {

  SequentialSet<Task> coarseSet = new CoarseSet<>();

  @Override
  public boolean add(Task task) {
//    Node<Task> newNode = new Node<Task>(task);

    return coarseSet.add(task);
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    synchronized (this) {
      if (coarseSet.size() == 0) {
        return Optional.empty();
      } else {
        for (int i = 0; i < coarseSet.size(); i++) {
          if (coarseSet.getFromPosition(i) != null) {
            Task task = coarseSet.getFromPosition(i);
            coarseSet.remove(task);
            return Optional.of(task);
          }
        }
      }
      return Optional.empty();
    }
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return coarseSet.size();
  }

  private void resize() {

  }
}
