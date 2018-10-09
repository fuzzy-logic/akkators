package helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Application {
  public static void main(String[] args) throws Exception {
    final ActorSystem system = ActorSystem.create();

    final ActorRef helloActor = system.actorOf( Props.create(HelloActor.class), "helloActor");

    helloActor.tell(new HelloActor.Greeting("bob"), ActorRef.noSender());

    system.terminate();
  }
}