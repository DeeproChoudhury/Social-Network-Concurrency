package socialnetwork;

import socialnetwork.domain.Backlog;
import socialnetwork.domain.Board;
import socialnetwork.domain.Worker;

public class Main {

  public static void main(String[] args) {
    // Implement logic here following the steps described in the specs
    Backlog backlog = new CoarseBacklog();
    SocialNetwork socialNetwork = new SocialNetwork(backlog);
    Worker[] workers = new Worker[5];
    User[] users = new User[5];

//    Worker worker1 = new Worker(backlog);
//    worker1.start();
//    Worker worker2 = new Worker(backlog);
//    worker2.start();
//    Worker worker3 = new Worker(backlog);
//    worker3.start();
//
//    User user1 = new User("user1", socialNetwork);
//    user1.start();

    for(int i = 0; i < workers.length; i++) {
      workers[i] = new Worker(backlog);
      workers[i].start();
    }

    for(int i = 0; i < users.length; i++) {
      Board board = new CoarseBoard();
      users[i] = new User("user" + i, socialNetwork);
      socialNetwork.register(users[i], board);
      users[i].start();
    }

    for (Worker worker : workers) {
      try {
        worker.join();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }


  }
}
