---
layout: post
title:  "Docker Swarm : Worker Node를 Manager Node로 승격시키기"
date:   2019-02-05 1:31:42 +0900
categories: docker
tags:
- docker
---
### Docker Swarm : Worker Node(Node2)를 Manager Node로 승격시키기
> (Manager Node에서 실행) $ docker node promote NODE [NODE...]

```
예시)
user@master:~$ docker node ls
ID                            HOSTNAME            STATUS              AVAILABILITY        MANAGER STATUS      ENGINE VERSION
oczmgznj47bd58abjmrql4gc5 *   master              Ready               Active              Leader              18.06.1-ce
z0afqkd570azc1pppxg1b6gsl     node1               Ready               Active                                  18.06.1-ce
zuz034mvcr2owqb7q4zgw3dn2     node2               Ready               Active                                  18.06.1-ce

user@master:~$ docker node promote node2
Node node2 promoted to a manager in the swarm.

user@master:~$ docker node ls
ID                            HOSTNAME            STATUS              AVAILABILITY        MANAGER STATUS      ENGINE VERSION
oczmgznj47bd58abjmrql4gc5 *   master              Ready               Active              Leader              18.06.1-ce
z0afqkd570azc1pppxg1b6gsl     node1               Ready               Active                                  18.06.1-ce
zuz034mvcr2owqb7q4zgw3dn2     node2               Ready               Active              Reachable           18.06.1-ce

user@node2:~$ docker service ls
ID                  NAME                MODE                REPLICAS            IMAGE               PORTS
f1ii0n7slud7        nginx_service       replicated          1/1                 nginx:latest

```

참고사이트 <br>
Docker : https://docs.docker.com/engine/reference/commandline/node_promote/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
