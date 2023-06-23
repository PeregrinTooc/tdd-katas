package trafficSystem.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trafficSystem.api.TrafficNode;
import trafficSystem.api.TrafficParticipant;
import trafficSystem.implementation.Passage;

public class SystemWithTwoCars extends AcceptanceTests {

    private TrafficParticipant anyCar;
    private TrafficNode entry;
    private TrafficNode destination;
    private TrafficParticipant otherCar;

    @BeforeEach
    private void setUp() {
        entry = new Passage();
        destination = new Passage();
        entry.connectTo(destination, 1);
        givenSystemHasNodes(entry, destination);
        anyCar = factory.createCar(destination, destination, 0);
        otherCar = factory.createCar(entry, destination, 1);
        whenIAddParticipants(anyCar, otherCar);
    }

    @Test
    void sendsDestinationBlockedMessageIfDestinationIsOccupied() {
        whenIForwardTheSimulation();
        thenDestinationReachedMessageFrom(anyCar);
        thenDestinationBlockedMessageFrom(otherCar);
    }


}
