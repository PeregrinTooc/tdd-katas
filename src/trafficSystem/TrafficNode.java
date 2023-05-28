package trafficSystem;

public interface TrafficNode {
    void connectTo(TrafficNode other, int distance);

    boolean connectedTo(TrafficNode other);
}
