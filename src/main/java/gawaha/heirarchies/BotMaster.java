package gawaha.heirarchies;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
public class BotMaster extends AbstractActor {
    public BotMaster() {
        for (int index = 0; index < 5; index++) {
            getContext().actorOf(Props.create(AkkaBot.class));
        }
    }

    public Receive orig_createReceive() {
        return emptyBehavior();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(StartChildBots.class, this::onStartChildBots)
                .build();
    }



    private void onStartChildBots(StartChildBots startChildBots) {
        final AkkaBot.Move move = new AkkaBot.Move(AkkaBot.Direction.FORWARD);
        for (ActorRef child : getContext().getChildren()) {
            System.out.println("Master started moving " + child);
            child.tell(move, getSelf());
        }
    }



    public static class StartChildBots {

    }

}