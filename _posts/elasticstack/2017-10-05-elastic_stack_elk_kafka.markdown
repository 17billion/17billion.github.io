---
layout: post
title:  "Kafka 메세지를 Logstash를 이용하여 Elastic Search에 저장하기 Kafka(0.10.0.1) / Logstash(5.5.1) / Elastic Search(5.5.1)"
date:   2017-10-05 09:51:42 +0900
categories: elastic, kafka
tags:
- elastic
- kafka
---

Kafka(2.10-0.10.0.1) + Logstash(5.5.1) + Elastic Search(5.5.1)

-----------------------------

#### Kafka에 저장된 메세지(apache log)를 Logstash를 이용하여 Elastic Search에 저장하기 <br>

### 1. elastic search 설치 및 실행 <br>
1-1. elastic search 설치 <br>
- <a href ='/elastic/2017/07/05/ELK_install_exec.html'>'ELK 구축 (Elastic Search + Kibana + Logstash) 1. ElasticSearch를 설치합니다.' 참고</a>

1-2. elasticsearch.yml 설정변경 <br>
- elastic search를 localhost가 아닌 외부에서 접근하기 위해서는 elasticsearch.yml에서 network.host를 ip로 지정 후 재시작 필요
> (../elasticsearch-5.5.1/config 디렉토리에서 실행) <br>
$ vi elasticsearch.yml
```
...
network.host: 192.168.56.102
...
```
※ 192.168.56.102는 elasticsearch 설치된 인스턴스 ip로 변경

1-3. system 환경 설정
> $ sudo vi /etc/security/limits.conf
```
(아래내용 추가, user는 사용자 계정으로 변경)
user hard nofile 65536
user soft nofile 65536
user hard nproc 65536
user soft nproc 65536
```

> $ sudo vi /etc/rc.local
```
(exit 0 윗 부분에 아래 내용추가 )
echo 1048575 > /proc/sys/vm/max_map_count
```
> $ sudo sysctl -w fs.file-max=65536
```
(확인)
cat /proc/sys/fs/file-max
```
> $ sudo reboot 0

1-4 실행
> (../elasticsearch-5.5.1/bin 디렉토리에서 실행) <br>
$ ./elasticsearch

### 2. kafka 설치 및 실행 및 메세지 프로듀싱 <br>
2-1. kafka 설치 및 topic 생성 <br>
- <a href ='/elastic/2017/09/20/filebeat_kafka.html'>'로그를 filebeat(5.3.0) 이용해서 kafka(0.10.0.1)로 프로듀싱하기' 참고</a>
 
### 3. logstash 설치 및 실행 <br>
3-1. logstash 설치 <br>
- <a href ='/elastic/2017/07/05/ELK_install_exec.html'>'ELK 구축 (Elastic Search + Kibana + Logstash)' 2-1. Logstash 다운로드 및 unzip 참고</a> <br>

3-2. logstash-input-kafka 플러그인 설치
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ ./logstash-plugin install -\-version 8.0.2 logstash-input-kafka
```
Validating logstash-input-kafka-8.0.2
Installing logstash-input-kafka
Installation successful
```
> (확인) ./logstash-plugin list -\-verbose input-kafka
```
logstash-input-kafka (8.0.2)
```

3-3. logstash-output-elasticsearch 플러그인 설치
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ ./logstash-plugin install logstash-output-elasticsearch
```
Validating logstash-output-elasticsearch
Installing logstash-output-elasticsearch
Installation successful
```
> (확인) $ ./logstash-plugin list -\-verbose logstash-output-elasticsearch
```
logstash-output-elasticsearch (7.3.7)
```

3-4 config 설정
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ vi logstash-kafka-elasticsearch.conf <br>

```
input {
  kafka {
    bootstrap_servers => "192.168.56.103:9092"
    group_id => "logstash"
    topics => ["apache_logs"]
    consumer_threads => 1
    decorate_events => true
    }
}

output {
  elasticsearch{
        hosts => "192.168.56.102:9200"
        index => "server-status-%{+YYYY.MM.dd}"
  }
}
```

- input의 bootstrap_servers는 kafka IP주소
- topics명을 kafka에 생성되어 있는 topic 명
- output의 hosts는 elasticsearch의 IP 주소를 입력

3-5. 실행
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ ./logstash -f logstash-kafka-elasticsearch.conf

- 참고 'logstash Setting "xpack.monitoring.enabled" has not been registered' 로그가 발생하며 실행이 안될때 x-pack 플러그인 설치가 필요 <br>
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ sudo bin/logstash-plugin install x-pack

### 4. kafka 메세지가 logstash에서 정상적으로 소비되었는지 확인방법
> (kafka가 설치된 디렉토리에서 실행)
$ bin/kafka-run-class.sh kafka.tools.ConsumerOffsetChecker --zookeeper localhost:2181 --group logstash --topic apache_logs
```
(결과)
Group           Topic                          Pid Offset          logSize         Lag             Owner
logstash        apache_logs                    0   3               3               0               none
```
- Group은 소비그룹
- logsize는 전체 메세지 개수
- Offset은 소비된 메세지 개수
- Log은 남은 메세지 개수

### 5. elastic search에 생성된 인덱스 및 내용 확인
> $ curl -XGET \'http://{elastic search가 실행된 ip}:9200/{index}/_search?size=50&pretty\' <br>
예시) $ curl -XGET \'http://192.168.56.102:9200/server-*/_search?size=50&pretty\' <br>




참고 사이트 <br>
elastic : <https://www.elastic.co/guide/en/logstash/current/plugins-inputs-kafka.html> <br>
elastic(discuss) : <https://discuss.elastic.co/t/how-to-setup-kafka-input-in-logstash/67155> <br>
github(logstash-input-kafka) : <https://github.com/logstash-plugins/logstash-input-kafka/issues/219> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
