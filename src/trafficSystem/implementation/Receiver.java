package trafficSystem.implementation;

import trafficSystem.implementation.Message;
import trafficSystem.TrafficParticipant;

public interface Receiver {
    void receive(Message message, TrafficParticipant sender);
}
