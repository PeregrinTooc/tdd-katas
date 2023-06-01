package trafficSystem.implementation;

abstract public class Message {
    public static final Message NOROUTE = new MessageImpl("No Route");
    public static final Message DESTINATION_REACHED = new MessageImpl("Destination Reached");
    public static final Message EN_ROUTE = new MessageImpl("En Route");

    private static class MessageImpl extends Message {
        private final String message;

        public MessageImpl(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return message;
        }
    }
}
