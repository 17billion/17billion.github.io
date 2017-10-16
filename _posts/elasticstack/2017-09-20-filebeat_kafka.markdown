---
layout: post
title:  "log를 filebeat(5.3.0) 이용하여 kafka(0.10.0.1)로 프로듀싱하기"
date:   2017-09-20 09:51:42 +0900
categories: elastic, kafka
tags:
- elastic
- kafka
---

filebeat(5.3.0), kafka(2.10-0.10.0.1) 기준

-----------------------------

#### 예시) filebeat을 이용해서 서버의 apache log를 kafka로 프로듀싱하기

#### 1. apach2 설치
> $ sudo apt-get update <br>
$ sudo apt-get install apache2 <br>
- 실행확인 <br>
$ curl localhost <br>
- 로그 조회 <br>
$ tail -F /var/log/apache2/access.log <br>

#### 2. kafka 설치 및 실행, Topic 생성 <br>
※ <a href ='/kafka/2017/09/05/kafka_install_exec.html'>'kafka(0.10.0.1) 설치 및 실행' 참고 </a> <br>
- apache_logs topic 생성 <br>
> (../kafka 디렉토리에서 실행) <br>
$ bin/kafka-topics.sh -\-create -\-zookeeper localhost:2181 -\-replication-factor 1 -\-partitions 1 -\-topic apache_logs <br>

#### 3. filebeat 설치 및 실행 <br>
> 3-1 파일 다운로드 <br>
$ wget https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-5.3.0-linux-x86_64.tar.gz <br>
3-2. 압축 해제 <br>
$ tar xzvf filebeat-5.3.0-linux-x86_64.tar.gz  <br>
3-3. 설정 파일 수정 <br>
$ cd filebeat-5.3.0-linux-x86_64/ <br>
$ vi filebeat.yml <br>

```
filebeat.prospectors:

- input_type: log

  paths:
    - /var/log/apache2/access.log

logging.level: debug
logging.to_files: true
logging.to_syslog: false
logging.files:
  path: /home/user/es/filebeat
  name: mybeat.log
  keepfiles: 7

output.kafka:
   hosts: ["192.168.56.103:9092"]
   topic: 'apache_logs'
   partition.round_robin:
      reachable_only: false
   required_acks: 1
   compression: gzip
   max_message_bytes: 1000000
```

※ hosts: ["192.168.56.103:9092"]의 IP부분은 kafka가 설치된 인스턴스의 IP

#### 4. filebeat 실행
> > (../filebeat 디렉토리에서 실행) <br>
$ nohup ./filebeat -e -c filebeat.yml & <br>
(실행 후 로그를 보기위해서는 $ ./filebeat -e -c filebeat.yml) <br>
- 참고 : 실행이 정상적으로 되지 않고 Failed to connect to broker localhost:9092 이런 로그가 노출될 때 kafka의 설정을 아래처럼 localhost가 아닌 위처럼 ip를 써줘야 함
```
$ sudo vi /usr/local/kafka/config/server.properties
advertised.listeners=PLAINTEXT://192.168.56.103:9092
zookeeper.connect=192.168.56.103:2181
```

#### 5. kafka에 저장되어 있는 apache 로그 확인
> (../kafka 디렉토리에서 실행) <br>
$ bin/kafka-console-consumer.sh -\-zookeeper localhost:2181 -\-topic apache_logs -\-from-beginning <br>
```
결과
user@node2:/usr/local/kafka$ bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic apache_logs --from-beginning
{"@timestamp":"2017-09-14T00:45:06.589Z","beat":{"hostname":"node1","name":"node1","version":"5.3.0"},"input_type":"log","message":"::1 - - [14/Sep/2017:09:32:21 +0900] \"GET / HTTP/1.1\" 200 11764 \"-\" \"curl/7.35.0\"","offset":82,"source":"/var/log/apache2/access.log","type":"log"}
Processed a total of 1 messages
```

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
