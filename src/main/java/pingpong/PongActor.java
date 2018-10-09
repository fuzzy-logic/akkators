package pingpong;

import akka.actor.AbstractActor;

public class PongActor extends AbstractActor {

    public Receive createReceive() {
        return receiveBuilder()
                .match(Messages.Ping.class, this::pong)
                .build();
    }
    private void pong(Messages.Ping p) {
        System.out.println("PONG!");
        getSender().tell(new Messages.Pong(), getSelf());
    }

}