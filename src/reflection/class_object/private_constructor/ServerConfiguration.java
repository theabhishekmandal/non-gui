package reflection.class_object.private_constructor;

import java.net.InetSocketAddress;

public class ServerConfiguration {
    private static ServerConfiguration serverConfiguration;
    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.serverAddress = new InetSocketAddress("localhost", port);
        this.greetingMessage = greetingMessage;

        if (serverConfiguration == null) {
            serverConfiguration = this;
        }
    }

    public InetSocketAddress getServerAddress() {
        return this.serverAddress;
    }

    public String getGreetingMessage() {
        return this.greetingMessage;
    }

    public static ServerConfiguration getInstance() {
        return serverConfiguration;
    }
}
