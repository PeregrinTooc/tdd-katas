package trafficSystem;

import trafficSystem.implementation.Message;
import trafficSystem.implementation.Receiver;

import java.util.HashSet;
import java.util.Set;

public class TrafficParticpantFactory {
    public TrafficParticipant createCar(TrafficNode entry, TrafficNode destination) {
        var result = new Car();
        result.position = entry;
        result.destination = destination;
        return result;
    }

    public TrafficParticipant createCar(TrafficNode entry, TrafficNode exit, int startVelocity) {
        var result = (Car)createCar(entry,exit);
        result.velocity = startVelocity;
        return result;
    }

    class Car implements TrafficParticipant {
        private TrafficNode destination;
        private int velocity = 0;
        private int acceleration = 0;
        private TrafficNode position;
        private Set<Receiver> receivers = new HashSet<Receiver>();

        @Override
        public void broadcastTo(Receiver messageReceiver) {
            receivers.add(messageReceiver);
        }

        @Override
        public void tick() {
            if (position.connectedTo(destination)) {
                if(position.reachedWithVelocity(destination,velocity)){
                receivers.forEach(receiver -> receiver.receive(Message.DESTINATION_REACHED, this));}
                else{
                    receivers.forEach(receiver -> receiver.receive(Message.EN_ROUTE, this));}

            } else {
                receivers.forEach(receiver -> receiver.receive(Message.NOROUTE, this));
            }
        }

        @Override
        public void accelerate(int acceleration) {
            this.acceleration = acceleration;
        }
    }
}
