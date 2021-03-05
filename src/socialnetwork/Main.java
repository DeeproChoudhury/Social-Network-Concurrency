package socialnetwork;

import socialnetwork.domain.Backlog;
import socialnetwork.domain.Worker;

public class Main {

  public static void main(String[] args) {
    // Implement logic here following the steps described in the specs
    Backlog backlog = new CoarseBacklog();
    SocialNetwork socialNetwork = new SocialNetwork(backlog);

    Worker worker1 = new Worker(backlog);
    worker1.start();
    Worker worker2 = new Worker(backlog);
    worker2.start();
    Worker worker3 = new Worker(backlog);
    worker3.start();

    User user1 = new User("user1", socialNetwork);


  }
}
