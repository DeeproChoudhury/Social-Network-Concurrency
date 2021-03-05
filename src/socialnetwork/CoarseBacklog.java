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
    coarseSet.add(task);
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
      return coarseSet.poll();
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return coarseSet.size();
  }

}
