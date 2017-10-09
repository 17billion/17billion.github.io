---
layout: post
title:  "kafka topic 삭제 설정"
date:   2017-08-31 09:51:42 +0900
categories: kafka
tags:
- kafka
---
### kafka 토픽 삭제 설정 <br>

> $ vi {$KAFKA_HOME}/config/server.properties
```
delete.topic.enable=true
```
설정 추가 필요 <br>

해당 값을 설정 후 kafka 재시작 <br>
위 설정값의 default는 false로 true로 설정해주지 않으면 해당 topic은 삭제 상태로 메세지가 들어와도 처리되지 않는 현상 발생함

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
