package socialnetwork.domain;

import java.util.Optional;
import socialnetwork.domain.Task.Command;

public class Worker extends Thread {

  private final Backlog backlog;
  private boolean interrupted = false;
  private final static int SLEEP_VALUE = 175;

  public Worker(Backlog backlog) {
    this.backlog = backlog;
  }

  @Override
  public void run() {
    while (!interrupted) {
      Optional<Task> nextTask = backlog.getNextTaskToProcess();
      if (nextTask.isPresent()) {
        process(nextTask.get());
      } else {
        try {
          Thread.sleep(SLEEP_VALUE);
        } catch (InterruptedException e) {
          interrupted = true;
        }
      }
    }
  }

  public void interrupt() {
    this.interrupted = true;
  }

  public void process(Task nextTask) {
    if (nextTask.getCommand().equals(Command.POST)) {
      nextTask.getBoard().addMessage(nextTask.getMessage());
    } else {
      boolean removedMessage = nextTask.getBoard().deleteMessage(nextTask.getMessage());
      if (!removedMessage) {
        backlog.add(nextTask);
      }
    }
  }
}
