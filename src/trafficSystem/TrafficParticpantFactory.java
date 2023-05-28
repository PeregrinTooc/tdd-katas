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

    class Car implements TrafficParticipant {
        public TrafficNode destination;
        private TrafficNode position;
        private Set<Receiver> receivers = new HashSet<Receiver>();

        @Override
        public void broadcastTo(Receiver messageReceiver) {
            receivers.add(messageReceiver);
        }

        @Override
        public void tick() {
            if (position.connectedTo(destination)) {
                receivers.forEach(receiver -> receiver.receive(Message.DESTINATION_REACHED, this));
            } else {
                receivers.forEach(receiver -> receiver.receive(Message.NOROUTE, this));
            }
        }
    }
}
