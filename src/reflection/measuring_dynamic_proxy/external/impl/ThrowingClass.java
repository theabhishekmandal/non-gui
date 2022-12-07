package reflection.measuring_dynamic_proxy.external.impl;

import reflection.measuring_dynamic_proxy.external.ThrowingInterface;

import java.io.IOException;

public class ThrowingClass implements ThrowingInterface {
    @Override
    public void throwException() throws IOException {
        throw new IOException("File cannot be read");
    }
}
