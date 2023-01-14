
(a) Install Amazon Java SDK
(b) Install kafka from their site steps
(c) setup /bin path of IDE(in linux only) and kafka in system variable
(either linux os WSL2)

(d) Start Zookeeper + Kafka Broker Server
`zookeeper-server-start.sh config/zookeeper.properties`
`kafka-server-start.sh config/server.properties`

(e) Create topic  + start consumer client on the topic
`kafka-topic --bootstrap-server 127.0.0.1:9092 --create --topic demo_java --partitions 3 --replication-factor 1`
`kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --topic demo_java`
(f) run the ProducerDemo class in IDE & check if message is printed on console client or not.