package trafficSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrafficSystem {
    private List<TrafficParticipant> trafficParticipants = new ArrayList<TrafficParticipant>();

    public static TrafficSystem create() {
        return new TrafficSystem();
    }

    public void add(TrafficParticipant... trafficParticipants) {
        this.trafficParticipants.addAll(Arrays.stream(trafficParticipants).toList());
    }

    public void tick() {
        trafficParticipants.forEach(trafficParticipant -> trafficParticipant.tick());
    }

    public void addNodes(TrafficNode... nodes) {
    }
}
