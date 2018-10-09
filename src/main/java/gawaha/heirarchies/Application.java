package gawaha.heirarchies;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
public class Application {
  public static void main(String[] args) throws Exception {
    final ActorSystem system = ActorSystem.create();

    final ActorRef botMaster = system.actorOf(Props.create(BotMaster.class), "botMaster");
    botMaster.tell(new BotMaster.StartChildBots(), ActorRef.noSender());

  /*
    akkaBot.tell(
            new PongActor.Move(PongActor.Direction.FORWARD),
            ActorRef.noSender());
    akkaBot.tell(
            new PongActor.Move(PongActor.Direction.BACKWARDS),
            ActorRef.noSender());
    akkaBot.tell(
            new PongActor.Stop(),
            ActorRef.noSender());
  */
  //  system.terminate();
  }
}