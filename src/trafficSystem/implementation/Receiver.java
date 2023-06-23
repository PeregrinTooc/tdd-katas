package trafficSystem.implementation;

import trafficSystem.api.TrafficParticipant;

public interface Receiver {
    void receive(Message message, TrafficParticipant sender);
}
