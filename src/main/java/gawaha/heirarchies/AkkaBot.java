package gawaha.heirarchies;

import akka.actor.AbstractActor;

import java.util.Optional;

public class AkkaBot extends AbstractActor {

    private Optional<Direction> direction = Optional.empty();
    private boolean moving = false;

    public Receive createReceive() {
        return receiveBuilder()
                .match(Move.class, this::onMove)
                .match(Stop.class, this::onStop)
                .build();
    }
    private void onMove(Move move) {
        moving = true;
        direction = Optional.of(move.direction);
        System.out.println(getSelf().path() + ": I am now moving " + direction.get());
    }
    private void onStop(Stop stop) {
        moving = false;
        System.out.println("I stopped moving");
    }


    /**
     * Message Classes
      */
    public enum Direction {
        FORWARD,
        BACKWARDS,
        RIGHT,
        LEFT
    }

    public static class Move {
        public final Direction direction;
        public Move(Direction direction) {
            this.direction = direction;
        }
    }

    public static class Stop {}

    public static class GetRobotState {}

    public static class RobotState {
        public final Direction direction;
        public final boolean moving;
        public RobotState(Direction direction, boolean moving) {
            this.direction = direction;
            this.moving = moving;
        }
    }
}