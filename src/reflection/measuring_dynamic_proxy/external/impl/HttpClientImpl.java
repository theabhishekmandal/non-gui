package reflection.measuring_dynamic_proxy.external.impl;

import reflection.measuring_dynamic_proxy.external.HttpClient;

public class HttpClientImpl implements HttpClient {
    @Override
    public void initialise() {
        System.out.println("Initialising Http Client");
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String sendRequest(String request) {
        System.out.printf("Sending request %s", request);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("Received Response");
        return "someResponse data";
    }
}
