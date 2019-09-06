package io.realm9.prometheus.dds;

import io.realm9.prometheus.dds.services.ContainersFactory;
import io.realm9.prometheus.dds.services.DockerFactory;
import io.realm9.prometheus.dds.services.UpdatesService;
import io.realm9.prometheus.dds.services.WebServer;

public class Runner {

    public static void main(String[] args) {
        UpdatesService.INSTANCE.checkForUpdates();
        // todo: need to get updated list of the hadoop and zookeeper nodes and put them to config files

        var docker = DockerFactory.getDockerClient();

        ContainersFactory.createNameNode(docker);
        ContainersFactory.createJournalNode(docker);
        ContainersFactory.createDataNode(docker);
        ContainersFactory.createZookeeperNode(docker);

        WebServer.INSTANCE.start(8080);
    }
}
