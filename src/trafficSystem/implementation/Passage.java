package trafficSystem.implementation;

import org.junit.jupiter.api.*;
import trafficSystem.TrafficNode;

import java.util.HashMap;
import java.util.Map;


public class Passage implements TrafficNode {
    private Map<TrafficNode, Integer> connections = new HashMap<TrafficNode,Integer>();

    @Override
    public void connectTo(TrafficNode other, int distance) {
        if(this.connectedTo(other))
            return;
        connections.put(other,distance);
        other.connectTo(this,distance);
    }

    @Override
    public boolean connectedTo(TrafficNode other) {
        return connections.containsKey(other);
    }

    @Override
    public boolean reachedWithVelocity(TrafficNode destination, int velocity) {
        return connectedTo(destination) && connections.get(destination)<=velocity;
    }

    public static class Tests{
        @Test
        void conntectToIsTransitive(){
            var anyNode = new Passage();
            var otherNode = new Passage();
            anyNode.connectTo(otherNode,0);
            Assertions.assertTrue(otherNode.connectedTo(anyNode));
        }
    }
}
