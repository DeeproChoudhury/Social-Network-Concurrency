package socialnetwork.domain;

import java.util.Optional;

public interface Backlog {

  void add(Task task);

  Optional<Task> getNextTaskToProcess();

  int numberOfTasksInTheBacklog();
}
