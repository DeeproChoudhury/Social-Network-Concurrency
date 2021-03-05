package socialnetwork;

import java.util.Optional;
import lineardatastructures.CoarseSet;
import socialnetwork.domain.Backlog;
import socialnetwork.domain.Task;
import lineardatastructures.SequentialSet;

public class CoarseBacklog implements Backlog {

  SequentialSet<Task> coarseSet = new CoarseSet<>(Task::getId);

  @Override
  public void add(Task task) {
//    Node<Task> newNode = new Node<Task>(task);
    coarseSet.add(task);
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    synchronized (this) {
      return coarseSet.poll();
    }
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return coarseSet.size();
  }

  private void resize() {

  }
}
