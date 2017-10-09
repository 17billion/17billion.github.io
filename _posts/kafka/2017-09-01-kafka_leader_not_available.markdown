---
layout: post
title:  "kafka LEADER_NOT_AVAILABLE 에러"
date:   2017-09-01 09:51:42 +0900
categories: kafka
tags:
- kafka
---

### kafka LEADER_NOT_AVAILABLE 에러 발생 시 <br>
메세지를 생성할때 LEADER_NOT_AVAILABLE 에러가 발생하면서 안될 경우  <br>
ex) WARN Error while fetching metadata with correlation id 0 : {topic_name=LEADER_NOT_AVAILABLE} (org.apache.afka.clients.NetworkClient) <br>

> vi {$KAFKA_HOME}/config/server.properties <br>
```
advertised.listeners = PLAINTEXT://localhost:9092
```

설정 추가(주석 해제) 후 kafka 재시작 <br>

참고 :  <br>
kafka.apache.org : <https://kafka.apache.org/documentation/> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
