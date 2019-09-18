package io.realm9.prometheus.dds.services;

/*
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.BuildResponseItem;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.SearchItem;
import com.github.dockerjava.core.command.BuildImageResultCallback;
*/

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ContainersFactory {
/*
    public static final String HADOOP_IMAGE = "prometheus/hadoop";
    public static final String ZOOKEEPER_IMAGE = "zookeeper";

    private static CreateContainerResponse zookeeper;
    private static CreateContainerResponse nameNode;
    private static CreateContainerResponse journalNode;
    private static CreateContainerResponse dataNode;

    public static void createContainer(DockerClient docker, String image) {
        File baseDir = new File("classpath:/docker" + image);
        docker.buildImageCmd(baseDir)
                .withTags(Collections.singleton(HADOOP_IMAGE))
                .exec(new BuildImageResultCallback() {
                    @Override
                    public void onNext(BuildResponseItem item) {
                        System.out.println("" + item);
                        super.onNext(item);
                    }
                }).awaitImageId();
    }

    private static List<String> getEnvList(String image) {
        final var envFile = "classpath:/nodes/" + image + "/.env";
        var path = (envFile.toLowerCase().startsWith("file:")) ? Paths.get(URI.create(envFile)) : Paths.get(envFile);

        List<String> env;
        try {
            if (Files.exists(path)) {
                env = Files.lines(path).collect(Collectors.toList());
            } else {
                var inputStream = ContainersFactory.class.getResourceAsStream(envFile);
                if (inputStream == null) {
                    return List.of();
                }
                var scanner = new java.util.Scanner(inputStream, StandardCharsets.UTF_8);
                var lines = new ArrayList<String>();
                while (scanner.hasNext()) {
                    lines.add(scanner.nextLine());
                }
                env = lines;
            }
        } catch (IOException e) {
            return List.of();
        }

        return env;
    }

    public static void createJournalNode(DockerClient docker) {
        journalNode = createHadoopNode(docker, "journalnode", ExposedPort.tcp(8485));
    }

    public static void createNameNode(DockerClient docker) {
        nameNode = createHadoopNode(docker, "namenode", ExposedPort.tcp(9870), ExposedPort.tcp(8020));
    }

    public static void createDataNode(DockerClient docker) {
        dataNode = createHadoopNode(docker, "datanode");
    }

    private static CreateContainerResponse createHadoopNode(DockerClient docker, String nodeType, ExposedPort... ports) {
        var env = getEnvList(nodeType);

        final var containerCmd = runContainer(docker, HADOOP_IMAGE)
                .withDomainName(nodeType)
                .withEnv(env)
                .withExposedPorts(ports);

        return containerCmd.exec();
    }

    public static void createZookeeperNode(DockerClient docker) {
        var env = getEnvList("zookeeper");

        zookeeper = runContainer(docker, ZOOKEEPER_IMAGE)
                .withDomainName("zookeeper")
                .withExposedPorts(ExposedPort.tcp(9870))
                .withEnv(env)
                .exec();
    }

    public static CreateContainerCmd runContainer(DockerClient docker, String image) {
        final List<SearchItem> searchItems = docker.searchImagesCmd(image).exec();
        if (searchItems.size() == 0) {
            ContainersFactory.createContainer(docker, image);
        }

        return docker.createContainerCmd(image);
    }
*/
}
