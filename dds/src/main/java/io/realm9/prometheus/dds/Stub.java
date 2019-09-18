package io.realm9.prometheus.dds;

import io.realm9.prometheus.dds.services.WebServer;

public class Stub {
    public static void main(String[] args) {
        new Stub().run();
    }

    private void run() {
        WebServer.STUB_INSTANCE.start(8080);
    }
}
