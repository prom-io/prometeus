package io.realm9.prometheus.dds.services;

import io.realm9.prometheus.dds.rest.service.FilesService;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

public enum WebServer {
    INSTANCE;

    private static ResourceConfig createApp() {
        return new ResourceConfig(resourceLoaderClasses());
    }

    private static Set<Class<?>> resourceLoaderClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();

        classes.add(FilesService.class);
        return classes;
    }

    public void start(int port) {
        final var uri = URI.create("http://localhost:" + port + "/");
        try {
            System.out.println("JSON with Jackson Jersey Example App");

            final var server = GrizzlyHttpServerFactory.createHttpServer(uri, createApp(), false);
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));
            server.start();

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
