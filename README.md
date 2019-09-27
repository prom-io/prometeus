# DDS – Distributed data storage 

## Table of contents

- [Description](#description)
- [How it works](#how-it-works)
- [How to run](#how-to-run)
    - [Prerequisites](#prerequisites)
    - [Build and run process](#build-and-run-process)
        - [Running inside Docker](#running-inside-docker)
        - [Running outside Docker](#running-outside-docker)
- [Stages of project](#stages-of-project)
    - [What DDS can do now](#what-dds-can-do-now)
    - [What DDS will do in the future](#what-dds-will-do-in-the-future)

## Description

DDS is an application which will work directly with hadoop nodes to store the required data through out the network
distributed between multiple nodes.

DDS will expose internal API for uploading, downloading and updating files, also it will allow to get information 
information about consumed storage space by each particular node.

You can find a diagram which describes uploading files
[here](https://github.com/Prometeus-Network/prometeus/blob/master/docs/diagrams/dds_store_file.pdf), 
and a diagram describing notification about completed payment 
[here](https://github.com/Prometeus-Network/prometeus/blob/master/docs/diagrams/dds_storage_paid.pdf)


## How it works

Upon starting, DDS performs the following steps:
- Checking for updates. DDS connects to last known node to check if there is updated list of the neighbour nodes
- Creating necessarily containers for: Hadoop Name Node, Hadoop Journal Node, Hadoop Data Node and Zookeeper
- Starting listening as a rest http service

As soon as all services are up and running, DDS is starting to receive http connections. More information about API could be found [here](https://github.com/Prometeus-Network/prometeus/wiki/DDS-Billing-Api).

The workflow is the following:
- File upload requested including the term to store the file for
- DDS stores a file into a temporary storage (NoSQL database, could be CouchDB, H2 or MongoDB)
- DDS calculates and returns back the storage costs basing on file size and the term requested
- As soon as the payment done, DDS should be requested once more, notifying about the payment
- Once DDS received confirmation about successful payment, it stores the file into the Hadoop network

Hadoop HDFS is used in High Availability mode using Quorum Journal Manager. To read more information please follow [here](https://hadoop.apache.org/docs/r3.2.0/hadoop-project-dist/hadoop-hdfs/HDFSHighAvailabilityWithQJM.html)

## How to run

### Prerequisites

In order to run a DDS node, you need to install:
- Docker. You can find installation instructions on 
[official website](https://docs.docker.com/install/).
- If you want to run service-node outside of docker container, 
you will need Java 11 JRE (
You can find installations instructions [here](https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase11-5116896.html)) and also Maven (You can find installations instructions [here](https://maven.apache.org/download.cgi)) installed

### Build and run process

Firstly, you need to clone DDS from repository:

````
git clone https://github.com/Prometeus-Network/prometheus.git
````

After repository is cloned, you need to go into the `dds` directory:

````
cd dds
````

#### Running inside Docker

To run DDS Stub node inside Docker, execute the following command:

````
docker build -f stub/Dockerfile -t dds .
docker run -d --rm --name dds -p 8080:8080 dds
````

#### Running outside Docker

If you want to run DDS Stub node outside docker container, you will need to perform next steps:
- Run `mvn clean test package`.
This will run tests and build the latest JAR file
- Run `java -cp target/classes:target/classes/lib/* io.realm9.prometheus.dds.Stub` to start the application
