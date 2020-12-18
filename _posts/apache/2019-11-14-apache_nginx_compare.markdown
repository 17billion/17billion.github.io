---
layout: post
title:  "Apache, Nginx 비교"
date:   2019-11-14 23:08:12 +0900
categories: apache
tags:
- apaches
- nginx
---

### Apache와 Nginx 비교
- Nginx는 비동기 / 이벤트 기반으로 요청을 처리, Apache는 요청당 쓰레드 또는 프로세스가 처리하는 구조.
- 최근 대용량의 정적 파일 및 큰 규모의 사이트가 많아짐에 따라 대량 접속에도 적은 리소스를 사용하며 빠르게 서비스를 할 수 있는 웹 사이트가 대세가 되었는데, 이런 면에서 Nginx가 각광 받기 시작.
- 성능 면에서 Apache 2.2는 Nginx에 뒤쳐짐 
  - 2012년에 빠른 응답 속도와 적은 리소스 사용 부분을 개선한 Apache 2.4를 발표하면서, Nginx에 대응하기 시작
  - Nginx가 성능 면에서도 Apahce 2.4 보다 좋다고 함
- PHP 모듈 등을 직접 적재할 수 있는 Apache가 구조상 이점이 있기에 복잡한 웹 사이트의 경우 Apache가 적합
- 세션 클러스터링 같은 특별한 목적을 추가적으로 수행하는 세팅을 할 경우에는 별도의 과정을 거쳐야 하기 때문에, 별도의 작업이 많이 필요한 서비스의 경우 유지 보수 측면에서 Apache가 유용
- 안정성과 확장성, 호환성에서 Apache가 우세, 성능 면에서는 Nginx가 우세하다는 것이 결론

참고 사이트 <br />
victorydntmd님 블로그 : https://victorydntmd.tistory.com/231#recentComments