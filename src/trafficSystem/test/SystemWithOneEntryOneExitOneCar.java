package trafficSystem.test;


import org.junit.jupiter.api.*;
import trafficSystem.*;
import trafficSystem.implementation.*;

public class SystemWithOneEntryOneExitOneCar extends AcceptanceTests {

    private TrafficParticipant carWithStartVelocityOne;
    private TrafficNode entry;
    private TrafficNode exit;

    @Test
    void yieldsNoRouteWhenNodesAreNotConnected() {
        whenIForwardTheSimulation();
        thenNoRouteMessageFrom(carWithStartVelocityOne);
    }

    @Test
    void reachesDestinationAfterOneTickForDistanceOneAndSpeedOne(){
        entry.connectTo(exit,1);
        whenIForwardTheSimulation();
        thenDestinationReachedMessageFrom(carWithStartVelocityOne);
    }
    @Test
    void sendsEnRouteMessageAfterOneTickForDistanceTwoAndSpeedOne(){
        entry.connectTo(exit,2);
        whenIForwardTheSimulation();
        thenEnRouteMessageFrom(carWithStartVelocityOne);
    }

    @BeforeEach
    private void setUp() {
        entry = new Passage();
        exit = new Passage();
        givenSystemHasNodes(entry, exit);
        carWithStartVelocityOne = factory.createCar(entry, exit, 1);
        whenIAddParticipants(carWithStartVelocityOne);
    }
}
