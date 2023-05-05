package trafficSystem;

import org.junit.jupiter.api.*;

public class AcceptanceTests {
    private TrafficSystem trafficSystem;

    @Test
    void shouldBeAbleToAddACar(){
        givenATrafficSystem();

        whenIAddACar();

    }

    private void whenIAddACar() {

    }

    private void givenATrafficSystem() {
        trafficSystem = TrafficSystem.create();
    }

    ;

}
