package helloworld;

import akka.actor.AbstractActor;

import java.util.Optional;

public class HelloActor extends AbstractActor {

    public Receive createReceive() {
        return receiveBuilder()
                .match(Greeting.class, this::greet)
                .build();
    }
    private void greet(Greeting g) {
        System.out.println("Hello " + g.name);
    }



    /**
     * Message Class
      */

    public static class Greeting {
        private final String name;

        public Greeting(String name) {
            this.name = name;
        }
    }


    //public static class Greeting {};

}