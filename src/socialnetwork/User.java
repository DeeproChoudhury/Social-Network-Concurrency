package socialnetwork;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User extends Thread {

  private static final AtomicInteger nextId = new AtomicInteger(0);

  protected final SocialNetwork socialNetwork;
  private final int id;
  private final String name;

  public User(String username, SocialNetwork socialNetwork) {
    this.name = username;
    this.id = User.nextId.getAndIncrement();
    this.socialNetwork = socialNetwork;
  }

  public int getUserId() {
    return id;
  }

  @Override
  synchronized public void run() {
    Set<User> socialNetworkUsers = socialNetwork.getAllUsers();

  }

  @Override
  public String toString() {
    return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
  }

  @Override
  public int hashCode() {
    return id;
  }
}
