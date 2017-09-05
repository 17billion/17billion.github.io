---
layout: post
title:  "ELK 구축 (Elastic Search + Kibana + Logstash)"
date:   2017-07-05 09:51:42 +0900
categories: elastic
tags:
- elastic
---

#### 1. ElasticSearch를 설치합니다. (ubuntu 기준)
##### (<https://www.elastic.co/guide/en/beats/libbeat/5.5/elasticsearch-installation.html>)

1-1. JAVA 설치 (8이상 버전에서 실행가능)
> $ sudo add-apt-repository ppa:openjdk-r/ppa <br>
$ sudo apt-get update <br>
$ sudo apt-get install openjdk-8-jdk <br>

1-2. ElasticSearch 다운로드 및 unzip
> $ curl -L -O https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.5.1.zip <br>
$ unzip elasticsearch-5.5.1.zip <br>
$ cd elasticsearch-5.5.1 <br>

1-3. ElasticSearch config 설정 (사용자가 원하는 이름과 경로로 설정해주면 됩니다. 아래는 예시입니다.)
> (../elasticsearch-5.5.1/config 디렉토리에서 실행) <br>
$ vi elasticsearch.yml

```
cluster.name: elk_cluster
node.name: elk_node-1
http.port: 9200
```

1-4. 실행
> (../elasticsearch-5.5.1/bin 디렉토리에서 실행) <br>
./elasticsearch

1-5. 실행 확인
> $ curl localhost:9200
```
{
  "name" : "elk_node-1",
  "cluster_name" : "elk_cluster",
  "cluster_uuid" : "CEmUNQhIQlmuUdU6qv1JWQ",
  "version" : {
    "number" : "5.5.1",
    "build_hash" : "19c13d0",
    "build_date" : "2017-07-18T20:44:24.823Z",
    "build_snapshot" : false,
    "lucene_version" : "6.6.0"
  },
  "tagline" : "You Know, for Search"
}
```

#### 2. Logstash를 설치합니다. 
##### (<https://www.elastic.co/guide/en/beats/libbeat/5.5/logstash-installation.html>)

2-1. Logstash 다운로드 및 unzip
> $ curl -L -O https://artifacts.elastic.co/downloads/logstash/logstash-5.5.1.zip <br>
$ unzip logstash-5.5.1.zip <br>

2-2. Logstash config 설정 <br>
(<http://rea1man.tistory.com/83> 블로그에서 참고하였으며 최근 양식으로 conf 수정하였습니다.)
- 아래 conf는 free, cat /proc/stat, df -k 명령어를 파싱하여 mem, cpu, hdd를 모니터링하는 설정입니다.
- localhost:9200 이 부분은 ElasticSearch가 설치된 호스트 및 포트를 입력해주면됩니다.
- server-status-%{+YYYY.MM.dd} 이 부분은 원하는 index name을 설정해주면 됩니다. (추후 Kinaba에서 데이터 시각화 시 필요한 name입니다.)

> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ vi logstash-simple.conf
```
input {
  exec {
    command => "free | grep buffers/cache | awk '{print int($3/($3+$4)*100)}'"
    interval => "5"
    type => "mem"
  }
  exec {
    command => "cat /proc/stat | grep 'cpu ' | awk '{print int(($2+$3+$4)/($2+$3+$4+$5)*100)}'"
    interval => "5"
    type => "cpu"
  }
  exec {
    command => "df -k | grep /was | awk '{print ($5*1)}'"
    interval => "5"
    type => "hdd"
  }
}
filter {
  mutate {
    convert => ["message", "integer"]
  }
}
output {
  elasticsearch{
        hosts => "localhost:9200"
        index => "server-status-%{+YYYY.MM.dd}"
  }
}
```

2-3. 실행
> (../logstash-5.5.1/bin 디렉토리에서 실행) <br>
$ ./logstash -f logstash-simple.conf

##### ※ 만약 정상적으로 올라가지 않는다면 일단 ElasticSearch 프로세스를 재시작 후 Logstash를 실행 하시면 됩니다.

#### 3. kibana 설치
##### (<https://www.elastic.co/guide/en/beats/libbeat/5.5/kibana-installation.html>)

3-1. 다운로드 및 실행
> curl -L -O https://artifacts.elastic.co/downloads/kibana/kibana-5.5.1-linux-x86_64.tar.gz <br>
tar xzvf kibana-5.5.1-linux-x86_64.tar.gz <br>
cd kibana-5.5.1-linux-x86_64/ <br>
./bin/kibana

3-2. Kibana 접속 확인 <br>
(원격으로 putty 등으로 접속할 경우 터널링 필요) <br>
<http://localhost:5601> 접속
![0_input_index](/images/elk_kibana_chart/0_input_index.png)

참고 사이트 <br>
Realman World 블로그: <http://rea1man.tistory.com/83> <br>
Elastic : <https://www.elastic.co/guide>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
