package pingpong;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

public class PingActor extends AbstractActor {

    private int counter = 0;

    public Receive createReceive() {
        return receiveBuilder()
                .match(Init.class, this::init)
                .match(Messages.Pong.class, this::ping)
                .build();


    }
    private void init(Init i) {
        System.out.println("PING!");
        i.pongActor.tell(new Messages.Ping(), getSelf());
    }

    private void ping(Messages.Pong p) {
        counter++;
        if (counter == 10) {
            getContext().system().terminate();
        } else {
            System.out.println("PING!");
            getSender().tell(new Messages.Ping(), getSelf());
        }
    }

    /**
     * Message Class
      */
    public static class Init{
        private final ActorRef pongActor;

        public Init(ActorRef pongActor) {
            this.pongActor = pongActor;
        }
    };



}