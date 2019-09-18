package io.realm9.prometheus.dds.services;

import io.realm9.prometheus.dds.rest.service.FilesService;
import io.realm9.prometheus.dds.rest.service.FilesStubService;
import io.realm9.prometheus.dds.rest.service.GenericExceptionMapper;
import io.realm9.prometheus.dds.rest.service.PaymentsStubService;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum WebServer {
    INSTANCE, STUB_INSTANCE;

    private static Logger logger = Logger.getLogger(WebServer.class.getName());

    private static ResourceConfig createApp(WebServer webServer) {
        logger.info("creating resource config");
        final var resourceConfig = new ResourceConfig();

        logger.info("registering LoggingFeature");
        resourceConfig.register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 10000));

        switch (webServer) {
            case INSTANCE:
                logger.info("Registering FilesService");
                resourceConfig.register(FilesService.class);
                break;
            case STUB_INSTANCE:
                logger.info("Registering FilesStubService");
                resourceConfig.register(FilesStubService.class);
                logger.info("Registering PaymentsStubService");
                resourceConfig.register(PaymentsStubService.class);
                break;
        }
        logger.info("Registering GenericExceptionMapper");
        resourceConfig.register(GenericExceptionMapper.class);

        return resourceConfig;
    }

    public void start(int port) {
        final var uri = URI.create("http://0.0.0.0:" + port + "/");
        try {
            System.out.println("Prometheus DDS" + (this.equals(STUB_INSTANCE) ? ": STUB" : "."));

            logger.info("Creating GrizzlyHttpServer");
            final var server = GrizzlyHttpServerFactory.createHttpServer(uri, createApp(this), false);

            logger.info("addShutdownHook");
            Runtime.getRuntime().addShutdownHook(new Thread(server::shutdownNow));

            logger.info("server.start()");
            server.start();

            System.out.println(String.format("Application started.%nStop the application using CTRL+C"));

            Thread.currentThread().join();
        } catch (IOException | InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
