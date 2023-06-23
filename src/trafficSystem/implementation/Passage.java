package trafficSystem.implementation;

import org.junit.jupiter.api.*;
import trafficSystem.api.TrafficNode;

import java.util.HashMap;
import java.util.Map;


public class Passage implements TrafficNode {
    private Map<TrafficNode, Integer> connections = new HashMap<TrafficNode, Integer>();

    public Passage() {
        connections.put(this, 0);
    }

    @Override
    public void connectTo(TrafficNode other, int distance) {
        if (this.connectedTo(other)) return;
        connections.put(other, distance);
        other.connectTo(this, distance);
    }

    @Override
    public boolean connectedTo(TrafficNode other) {
        return connections.containsKey(other);
    }

    @Override
    public boolean reachedWithVelocity(TrafficNode destination, int velocity) {
        return connectedTo(destination) && connections.get(destination) <= velocity;
    }

    @Override
    public TrafficNode addNodeOnRoute(TrafficNode destination, int velocity) {
        TrafficNode result = new Passage();
        result.connectTo(this, velocity);
        result.connectTo(destination, connections.get(destination) - velocity);
        return result;
    }

    public static class Tests {
        @Test
        void conntectToIsSymmetric() {
            var anyNode = new Passage();
            var otherNode = new Passage();
            anyNode.connectTo(otherNode, 0);
            Assertions.assertTrue(otherNode.connectedTo(anyNode));
        }

        @Test
        void conntectToIsReflexive() {
            var anyNode = new Passage();
            Assertions.assertTrue(anyNode.connectedTo(anyNode));
        }
    }
}
