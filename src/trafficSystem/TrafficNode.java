package trafficSystem;

public interface TrafficNode {
    void connectTo(TrafficNode other, int distance);

    boolean connectedTo(TrafficNode other);

    boolean reachedWithVelocity(TrafficNode destination, int velocity);

}
