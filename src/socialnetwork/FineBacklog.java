package socialnetwork;

import java.sql.SQLOutput;
import java.util.Optional;
import lineardatastructures.FineSequentialNode;
import lineardatastructures.FineSequentialSet;
import lineardatastructures.SequentialSet;
import socialnetwork.domain.Backlog;
import socialnetwork.domain.Task;

public class FineBacklog implements Backlog {

  private final FineSequentialSet<Task> fineSequentialSet = new FineSequentialSet<>(Task::getId);

  @Override
  public void add(Task task) {
//    Node<Task> newNode = new Node<Task>(task);
    boolean isNewTask = fineSequentialSet.add(task);
    assert isNewTask;
    if(SocialNetwork.DEBUG) {
      System.out.println("Task::Add::" + task);
    }
  }

  @Override
  public Optional<Task> getNextTaskToProcess() {
    Optional<Task> nextTask = fineSequentialSet.poll();
    if(SocialNetwork.DEBUG) {
      System.out.println("Task::Poll::" + nextTask);
    }
    return nextTask;
  }

  @Override
  public int numberOfTasksInTheBacklog() {
    return fineSequentialSet.size();
  }


}
