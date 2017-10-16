---
layout: post
title:  "kafka(0.10.0.1) 설치 및 실행"
date:   2017-09-05 09:51:42 +0900
categories: kafka
tags:
- kafka
---
### kafka 설치 및 실행 방법(ubuntu 14.04 기준) <br>

#### 1. JAVA 설치
> $ sudo add-apt-repository ppa:openjdk-r/ppa <br>
$ sudo apt-get update  <br>
$ sudo apt-get install openjdk-8-jdk  <br>

#### 2. kafka 설치 (/usr/local/ 디렉토리 내 설치 후 심볼링 링크 설정) <br>
> $ wget http://apache.mirror.cdnetworks.com/kafka/0.10.0.1/kafka_2.10-0.10.0.1.tgz <br>
$ tar zxvf kafka_2.10-0.10.0.1.tgz <br>
$ sudo cp -r kafka_2.10-0.10.0.1 /usr/local/ <br>
$ sudo ln -s /usr/local/kafka_2.10-0.10.0.1/ /usr/local/kafka <br>
$ sudo mkdir -p /tmp/zookeeper

#### 3. kafka broker 설정
> $ vi {$KAFKA_HOME}/config/server.properties <br>
- broker.id=0 부분의 0을 1이상으로 변경 (각 kafka broker 마다 id가 달라야 함)<br>
- 로그를 저장하는 디렉토리 설정은 log.dirs=/tmp/kafka-logs 부분 <br>
- 참고. zookeeper 관련 설정변경은 {$KAFKA_HOME}/config/zookeeper.properties 파일 <br>

#### 4. zookeeper 및 kafka broker 실행 / 중지 shell 생성
> ../usr/local/kafka$ vi kafka-start.sh <br>
```
#!/bin/bash
KAFKA_HOME=/usr/local/kafka 
$KAFKA_HOME/bin/kafka-server-start.sh -daemon $KAFKA_HOME/config/server.properties 
```
> ../usr/local/kafka$ vi kafka-stop.sh
```
#!/bin/bash
KAFKA_HOME=/usr/local/kafka
$KAFKA_HOME/bin/kafka-server-stop.sh
```
> ../usr/local/kafka$ vi zk-start.sh
```
#!/bin/bash
nohup bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
```
> ../usr/local/kafka$ vi zk-stop.sh
```
#!/bin/bash
KAFKA_HOME=/usr/local/kafka
$KAFKA_HOME/bin/zookeeper-server-stop.sh
```

#### 5. zookeeper, kafka broker 실행
> $ sudo ./zk-start.sh <br>
$ sudo ./kafka-start.sh

#### 6. topic 생성 및 테스트 (bin 디렉토리 내에서 실행) <br>
> 6-1. topic 생성 (replication-factor 1, partitions 1) <br>
$ ./kafka-topics.sh -\-create -\-zookeeper localhost:2181 -\-replication-factor 1 -\-partitions 1 -\-topic test_topic <br>
```
결과
Created topic "test_topic".
```
6-2. 생성된 topic 정보 조회 <br>
$ ./kafka-topics.sh -\-describe -\-zookeeper localhost:2181 -\-topic test_topic <br>
```
결과
Topic:test_topic        PartitionCount:1        ReplicationFactor:1     Configs: <br>
        Topic: test_topic       Partition: 0    Leader: 1       Replicas: 1     Isr: 1
```
6-3 토픽 내 메세지 생성 (produce / 종료는 ctrl + c) - <a href ='/kafka/2017/09/01/kafka_leader_not_available.html'>LEADER_NOT_AVAILABLE 에러 발생 시 - 참고</a>  <br>
$ ./kafka-console-producer.sh -\-broker-list localhost:9092 -\-topic test_topic <br>
```
(입력) message test
```
6-4 생성된 메세지 확인 (consume / 종료는 ctrl + c) <br>
$ ./kafka-console-consumer.sh -\-zookeeper localhost:2181 -\-topic test_topic -\-from-beginning
```
결과
message test
Processed a total of 1 messages
```
6-5 토픽 삭제 (<a href ='/kafka/2017/08/31/kafka_delete_topic.html'>삭제 전 설정 변경 필요 - 참고</a>) <br>
$ ./kafka-topics.sh -\-delete -\-zookeeper localhost:2181 -\-topic test_topic


참고 사이트 <br>
kafka.apache.org : <https://kafka.apache.org/quickstart> <br>
김용환님의 블로그 : <http://knight76.tistory.com/entry/kafka-01011-%EC%84%A4%EC%B9%98> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
