FROM alpine
MAINTAINER iLyK Necromancer

RUN HADOOP_URL=`curl -s 'https://www.apache.org/dyn/closer.cgi/hadoop/common/hadoop-3.2.0/hadoop-3.2.0.tar.gz?as_json=1' | python -c "import sys, json; j=json.load(sys.stdin); print j['preferred'] + j['path_info']"`
RUN ZK_URL=`curl -s 'https://www.apache.org/dyn/closer.cgi/zookeeper/stable/apache-zookeeper-3.5.5.tar.gz?as_json=1' | python -c "import sys, json; j=json.load(sys.stdin); print j['preferred'] + j['path_info']"`
RUN wget ${HADOOP_URL}
RUN tar xfz hadoop-3.2.0.tar.gz

CMD 'echo 123;'
