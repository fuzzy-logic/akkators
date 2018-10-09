package pingpong;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;


public class Application {


  public static void main(String[] args) {
    ActorSystem system = ActorSystem.create("MyActorSystem");
    ActorRef pingActor = system.actorOf(Props.create(PingActor.class), "pingActor");

    ActorRef pongActor = system.actorOf(Props.create(PongActor.class), "pongActor");

    pingActor.tell(new PingActor.Init(pongActor), ActorRef.noSender());

    //pongActor.tell(new Messages.Ping(), ActorRef.noSender());
    system.whenTerminated();
  }
}