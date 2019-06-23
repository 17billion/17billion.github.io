---
layout: post
title:  "Docker Swarm : Scale In-Out 명령어"
date:   2019-01-17 12:31:42 +0900
categories: docker
tags:
- docker
---
### Docker Swarm Scale In-Out
> $ docker service scale SERVICE=REPLICAS

```
예시)
0EA로 Scale In $ docker service scale nginx_service=0
3EA로 Scale Out $ docker service scale nginx_service=3
```

참고사이트 <br>
Docker : https://docs.docker.com/engine/reference/commandline/service_scale/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
