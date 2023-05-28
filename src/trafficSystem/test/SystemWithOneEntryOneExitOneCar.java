package trafficSystem.test;


import org.junit.jupiter.api.*;
import trafficSystem.*;
import trafficSystem.implementation.*;

public class SystemWithOneEntryOneExitOneCar extends AcceptanceTests {

    private TrafficParticipant car;
    private TrafficNode entry;
    private TrafficNode exit;

    @Test
    void yieldsNoRouteWhenNodesAreNotConnected() {
        whenIForwardTheSimulation();
        thenNoRouteMessageFrom(car);
    }

    @Test
    void reachesDestinationAfterOneTickForDistanceOneAndSpeedOne(){
        entry.connectTo(exit,1);
        whenIForwardTheSimulation();
        thenDestinationReachedMessageFrom(car);
    }

    @BeforeEach
    private void setUp() {
        entry = new Passage();
        exit = new Passage();
        givenSystemHasNodes(entry, exit);
        car = factory.createCar(entry, exit);
        whenIAddParticipants(car);
    }
}
