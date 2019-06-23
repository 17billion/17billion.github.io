---
layout: post
title:  "Docker Swarm : yaml 파일을 이용하여 Service 올리기"
date:   2019-02-25 3:31:42 +0900
categories: docker
tags:
- docker
---
### Docker Swarm : yaml 파일을 이용하여 Service 올리기
> (Manager Node) $ docker stack deploy [OPTIONS] STACK

```
예시) nginx 컨테이너 2EA 올리기
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
    networks:
      - nginx

networks:
  nginx:

user@master:/scloud/docker/yaml$ docker stack deploy --compose-file docker-compose.yml test
Creating network test_nginx
Creating service test_nginx

user@master:/scloud/docker/yaml$ docker service ls
ID                  NAME                MODE                REPLICAS            IMAGE               PORTS
lzc7bkkg9kpc        test_nginx          replicated          2/2                 nginx:latest        *:80->80/tcp, *:443->443/tcp
```

```
추가 예시) 배포된 서비스에서 Scale 1EA로 변경하기
1. 위 yml파일에서 replicas > 1로 변경
2. $ docker stack deploy --compose-file docker-compose.yml test
아래 참고.
user@master:/scloud/docker/yaml$ cat docker-compose.yml
version: '3'
services:
  nginx:
    image: nginx:latest
    ports:
      - 80:80
      - 443:443
    deploy:
      replicas: 1
    networks:
      - nginx

networks:
  nginx:
user@master:/scloud/docker/yaml$ docker stack deploy -c docker-compose.yml test
Updating service test_nginx (id: lzc7bkkg9kpckr0je715iw0r4)
user@master:/scloud/docker/yaml$ docker service ls
ID                  NAME                MODE                REPLICAS            IMAGE               PORTS
lzc7bkkg9kpc        test_nginx          replicated          1/1                 nginx:latest        *:80->80/tcp, *:443->443/tcp

```

참고사이트 <br>
Docker : https://docs.docker.com/engine/reference/commandline/stack_deploy/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
