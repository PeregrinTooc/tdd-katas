package trafficSystem.api;

import trafficSystem.implementation.Receiver;

public interface TrafficParticipant extends Receiver {
    void broadcastTo(Receiver messageReceiver);

    void tick();

    void accelerate(int acceleration);

    void myPositionIs(TrafficNode position);
}
