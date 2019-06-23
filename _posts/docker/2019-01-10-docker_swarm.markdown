---
layout: post
title:  "Docker Swarm 구성하기"
date:   2019-01-10 12:31:42 +0900
categories: docker
tags:
- docker
---
### Docker Swarm 이란
Container Orchestration 중 하나로 Docker v1.12 이후부터 Swarm Mode가 통합되면서 쉽게 구성이 가능해짐
클러스터를 관리하는 Manager Node와 실제 컨테이너를 실행하는 Worker Node가 있음
![docker_Swarm](\images\docker_swarm\swarm-diagram.png)

> Manager Node 역할
- 클러스터 상태 유지
- 스케줄링 서비스
- HTTP API Endpoints 제공

> Manager Node 구성 중 확인사항
- 기본적으로 3EA의 Manager Node는 1EA Stop 허용
- 5EA의 Manager Node는 최대 2EA Stop 허용
- N EA의 Manager Node 최대 Stop 허용 갯수 :  (N-1)/2
- Docker는 최대 7EA의 Manager Node를 권장

※ Manager Node를 추가한다고해서 확장성또는 성능이 향상되진 않음. 일반적으로 그 반대가 사실.

### Docker Swarm 구성 (Manager Node 1EA Worker Node 2EA) <br>
Master > Manager Node(192.168.56.101) <br>
node1 >  Worker Node(192.168.56.102) <br>
node2 > Worker Node(192.168.56.103) <br>

#### Docker 설치
(https://17billion.github.io/docker/2017/03/10/docker_install_exec.html 참고)
```
18.06.1-ce 설치
1. $ sudo apt-get update
2. $ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common
3. $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg \| sudo apt-key add - 
4. $ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
5. $ sudo apt-get update
6. $ sudo apt-get install docker-ce=18.06.1~ce~3-0~ubuntu
7. $ sudo usermod -aG docker $USER 
   (현재 사용자에게 권한부여)
8. (재 로그인 후) $ docker ps
```

#### Swarm init (Manager Node)
```
$ docker swarm init --advertise-addr {{Manager Node IP}}
예시) user@master:~$ docker swarm init --advertise-addr 192.168.56.101
결과
Swarm initialized: current node (oczmgznj47bd58abjmrql4gc5) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join --token SWMTKN-1-1pud4gaae2bvwkqhbk1fngfsrw55v074lzoqd8fn31wxgmf0vj-egtud2vn5iam21giwki5kqxev 192.168.56.101:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.
```

#### Swarm join (Worker Node)
```
위 결과에서 나온 명령어 실행
예시) user@node2:~$ docker swarm join --token SWMTKN-1-1pud4gaae2bvwkqhbk1fngfsrw55v04lzoqd8fn31wxgmf0vj-egtud2vn5iam21giwki5kqxev 192.168.56.101:2377
This node joined a swarm as a worker.
```

#### 확인 (nginx 3EA 생성)
```
1. (Manager Node) user@master:~$ docker service create --name nginx_service --replicas 3 nginx
2. $ docker service ls
ID                  NAME                MODE                REPLICAS            IMAGE               PORTS
f1ii0n7slud7        nginx_service       replicated          3/3                 nginx:latest
3. $ docker service ps nginx_service 
ID                  NAME                IMAGE               NODE                DESIRED STATE       CURRENT STATE                ERROR               PORTS
v4sctczj607a        nginx_service.1     nginx:latest        master              Running             Running 56 seconds ago
neeyipblvb3i        nginx_service.2     nginx:latest        node1               Running             Running about a minute ago
qzorauvtvja3        nginx_service.3     nginx:latest        node2               Running             Running 59 seconds ago
```

※ Swarm 탈퇴하기
$ docker swarm leave


참고사이트 <br>
docker.com : https://docs.docker.com/engine/reference/commandline/swarm_init/ <br>
subicura님 블로그 : https://subicura.com/2017/02/25/container-orchestration-with-docker-swarm.html <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
