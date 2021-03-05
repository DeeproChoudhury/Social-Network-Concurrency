package socialnetwork;

import java.util.Optional;
import lineardatastructures.FineSequentialSet;
import socialnetwork.domain.Backlog;
import socialnetwork.domain.Task;

public class FineBacklog implements Backlog {

  private final FineSequentialSet<Task> taskSet = new FineSequentialSet<>(Task::getId);

  @Override
  public void add(Task task) {
    taskSet.add(task);
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    return taskSet.poll();
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return taskSet.size();
  }
}
