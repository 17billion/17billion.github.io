---
layout: post
title:  "Docker Swarm : 특정 조건(hostname, role, labels 등)을 지정하여 컨테이너 올리기 (constraint 옵션)"
date:   2019-03-12 3:31:42 +0900
categories: docker
tags:
- docker
---
### 특정 조건(hostname, role, labels 등)을 지정하여 컨테이너 올리기
예시) "node1" Node에만 컨테이너 올리기, Manager Node에만 컨테이너 올리기 등

```
예시 1) "node1" Node에만 컨테이너 올리기
user@master:/scloud/docker/yaml$ cat docker-compose.yml
version: '3'
services:
  nginx:
    image: nginx:latest
    ports:
      - 80:80
      - 443:443
    deploy:
      replicas: 2
      placement:
        constraints: [node.hostname == node1]
    networks:
      - nginx

networks:
  nginx:

user@master:/scloud/docker/yaml$ docker service ls
ID                  NAME                MODE                REPLICAS            IMAGE               PORTS
lzc7bkkg9kpc        test_nginx          replicated          2/2                 nginx:latest        *:80->80/tcp, *:443->443/tcp
user@master:/scloud/docker/yaml$ docker service ps test_nginx
ID                  NAME                IMAGE               NODE                DESIRED STATE       CURRENT STATE            ERROR               PORTS
lkxuit71s0l9        test_nginx.1        nginx:latest        node1               Running             Running 23 minutes ago
t357q0jes2sk        test_nginx.2        nginx:latest        node1               Running             Running 13 seconds ago


예시 2) Manager Node에만 컨테이너 올리기 (Manager Node : Master, Node2)
user@master:/scloud/docker/yaml$ cat docker-compose.yml
version: '3'
services:
  nginx:
    image: nginx:latest
    ports:
      - 80:80
      - 443:443
    deploy:
      replicas: 2
      placement:
        constraints: [node.role==manager]
    networks:
      - nginx

networks:
  nginx:

user@master:/scloud/docker/yaml$ docker stack deploy -c docker-compose.yml test
Updating service test_nginx (id: lzc7bkkg9kpckr0je715iw0r4)

user@master:/scloud/docker/yaml$ docker service ps test_nginx
ID                  NAME                IMAGE               NODE                DESIRED STATE       CURRENT STATE             ERROR               PORTS
2eao19s4q53m        test_nginx.1        nginx:latest        node2               Running             Running 19 seconds ago
lkxuit71s0l9         \_ test_nginx.1    nginx:latest        node1               Shutdown            Shutdown 20 seconds ago
ji20e0d966qq        test_nginx.2        nginx:latest        master              Running             Running 23 seconds ago
t357q0jes2sk         \_ test_nginx.2    nginx:latest        node1               Shutdown            Shutdown 24 seconds ago

user@master:/scloud/docker/yaml$ docker service ps test_nginx
ID                  NAME                IMAGE               NODE                DESIRED STATE       CURRENT STATE            ERROR               PORTS
2eao19s4q53m        test_nginx.1        nginx:latest        node2               Running             Running 4 minutes ago
ji20e0d966qq        test_nginx.2        nginx:latest        master              Running             Running 4 minutes ago
```


- 옵션 종류(https://docs.docker.com/engine/reference/commandline/service_create/#specify-service-placement-preferences-placement-pref 참고)

| node | attribute	| matches	example|
|:-------------:|:----------:|:----------:|
node.id	| Node ID	| node.id==2ivku8v2gvtg4
node.hostname	| Node hostname	| node.hostname!=node-2
node.role	| Node role	| node.role==manager
node.labels	| user defined node labels	| node.labels.security==high
engine.labels	| Docker Engine's labels	| engine.labels.operatingsystem==ubuntu 14.04


참고사이트 <br>
Docker : https://docs.docker.com/engine/reference/commandline/service_create/#specify-service-placement-preferences-placement-pref

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
