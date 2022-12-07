package reflection.measuring_dynamic_proxy.external;

public interface HttpClient {
    void initialise();

    String sendRequest(String request);
}
