package trafficSystem.test;

import org.junit.jupiter.api.*;
import trafficSystem.*;
import trafficSystem.implementation.Message;
import trafficSystem.implementation.Receiver;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AcceptanceTests {

    private TrafficSystem trafficSystem;
    private TrafficNode[] nodes;
    protected final TrafficParticpantFactory factory = new TrafficParticpantFactory();;
    private MessageReceiver messageReceiver;

    @BeforeEach
    void setup() {
        messageReceiver = new MessageReceiver();
        givenATrafficSystem();
    }



    protected final void thenNoRouteMessageFrom(TrafficParticipant... participants) {
        for (var participant : participants) {
            messageReceiver.assertMessageReceived(Message.NOROUTE, participant);
        }
    }

    protected void thenDestinationReachedMessageFrom(TrafficParticipant... participants) {
        for (var participant : participants) {
            messageReceiver.assertMessageReceived(Message.DESTINATION_REACHED, participant);
        }
    }

    protected void thenEnRouteMessageFrom(TrafficParticipant... participants) {
        for (var participant : participants) {
            messageReceiver.assertMessageReceived(Message.EN_ROUTE, participant);
        }
    }


    protected final void whenIForwardTheSimulation() {
        whenIForwardTheSimulation(1);
    }

    protected final void whenIForwardTheSimulation(int times) {
        for (int i = 0; i < times; i++) {
            trafficSystem.tick();
        }
    }

    protected final void whenIAddParticipants(TrafficParticipant... participants) {
        trafficSystem.add(participants);
        for (var participant : participants) {
            participant.broadcastTo(messageReceiver);
        }
    }

    protected final void givenSystemHasNodes(TrafficNode... nodes) {
        trafficSystem.addNodes(nodes);
        nodes = nodes;
    }


    private void givenATrafficSystem() {
        trafficSystem = TrafficSystem.create();
    }




    private class MessageReceiver implements Receiver {

        private Map<TrafficParticipant,Message> messages = new HashMap<TrafficParticipant,Message>();

        public void assertMessageReceived(Message message, TrafficParticipant messageSource) {
            assertEquals(message,messages.get(messageSource));
        }

        @Override
        public void receive(Message message, TrafficParticipant sender) {
            messages.put(sender,message);
        }
    }
}
