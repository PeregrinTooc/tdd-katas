package trafficSystem.test;


import org.junit.jupiter.api.*;
import trafficSystem.api.TrafficNode;
import trafficSystem.api.TrafficParticipant;
import trafficSystem.implementation.*;

public class SystemWithOneEntryOneExitOneCar extends AcceptanceTests {

    private TrafficParticipant carWithStartVelocityOne;
    private TrafficNode entry;
    private TrafficNode exit;

    @BeforeEach
    private void setUp() {
        entry = new Passage();
        exit = new Passage();
        givenSystemHasNodes(entry, exit);
        carWithStartVelocityOne = factory.createCar(entry, exit, 1);
        whenIAddParticipants(carWithStartVelocityOne);
    }

    @Test
    void yieldsNoRouteWhenNodesAreNotConnected() {
        whenIForwardTheSimulation();
        thenNoRouteMessageFrom(carWithStartVelocityOne);
    }

    @Test
    void sendsDestinationReachedMessageAfterOneTickForDistanceOneAndSpeedOne() {
        entry.connectTo(exit, 1);
        whenIForwardTheSimulation();
        thenDestinationReachedMessageFrom(carWithStartVelocityOne);
    }

    @Test
    void sendsEnRouteMessageAfterOneTickForDistanceTwoAndSpeedOne() {
        entry.connectTo(exit, 2);
        whenIForwardTheSimulation();
        thenEnRouteMessageFrom(carWithStartVelocityOne);
    }

    @Test
    void sendsDestinationReachedMessageAfterTwoTicksForDistanceTwoAndSpeedOne() {
        entry.connectTo(exit, 2);
        whenIForwardTheSimulation(2);
        thenDestinationReachedMessageFrom(carWithStartVelocityOne);
    }


}
