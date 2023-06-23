package trafficSystem.api;

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
        var result = (Car) createCar(entry, exit);
        result.velocity = startVelocity;
        return result;
    }

    class Car implements TrafficParticipant, Receiver {
        private TrafficNode destination;
        private int velocity = 0;
        private int acceleration = 0;
        private TrafficNode position;
        private Set<Receiver> receivers = new HashSet<Receiver>();
        private boolean destinationBlocked;

        @Override
        public void broadcastTo(Receiver messageReceiver) {
            if (messageReceiver != this) receivers.add(messageReceiver);
        }

        @Override
        public void tick() {
            if (position.connectedTo(destination)) {
                moveTowardsDestination();
            } else {
                sendMessageToWorld(Message.NOROUTE);
            }
        }

        private void moveTowardsDestination() {
            informWorldAboutMovement();
            if (destinationBlocked) {
                handleBlockedDestination();
                return;
            }
            if (position.reachedWithVelocity(destination, velocity)) {
                informWorldAboutReachedDestination();
            } else {
                updatePosition();
            }
        }

        private void updatePosition() {
            position = position.addNodeOnRoute(destination, velocity);
        }

        private void informWorldAboutReachedDestination() {
            sendMessageToWorld(Message.DESTINATION_REACHED);
        }

        private void sendMessageToWorld(Message destinationReached) {
            receivers.forEach(receiver -> receiver.receive(destinationReached, this));
        }

        private void handleBlockedDestination() {
            sendMessageToWorld(Message.DESTINATION_BLOCKED);
            destinationBlocked = false;
            return;
        }

        private void informWorldAboutMovement() {
            sendMessageToWorld(Message.EN_ROUTE);
        }

        @Override
        public void accelerate(int acceleration) {
            this.acceleration = acceleration;
        }

        @Override
        public void myPositionIs(TrafficNode position) {
            if (destination.equals(position)) {
                destinationBlocked = true;
            }
        }

        @Override
        public void receive(Message message, TrafficParticipant sender) {
            if (message.equals(Message.EN_ROUTE)) {
                sender.myPositionIs(this.position);
            }
        }
    }
}
