package trafficSystem;

import trafficSystem.implementation.Receiver;

public interface TrafficParticipant {
    void broadcastTo(Receiver messageReceiver);

    void tick();

}
