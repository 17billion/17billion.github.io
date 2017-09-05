---
layout: post
title:  "Docker Compose 설치, 실습"
date:   2017-04-02 09:51:42 +0900
categories: docker
tags:
- docker
---

DOCKER COMPOSE란
##### - 다중  Docker 컨테이너 응용 프로그램을 설정하고 실행하기 위한 도구입니다. docker-compose.yml 파일을 이용해서 컨테이너들의 포트포워딩, 이미지, 환경변수 등 다양한 설정이 가능합니다.
특징
##### - 단일 호스트의 여러 격리 된 환경 - 프로젝트 이름을 사용하여 서로 환경을 격리할 수 있습니다.
##### - 마운트시킨 볼륨 데이터 보존 가능 - 실행에서 컨테이너를 다시 올릴때 볼륨을 이전 컨테이너에서 새 컨테이너로 복사합니다. 이 과정은 볼륨에서 사용한 모든 데이터를 손실되지 않도록 합니다.
##### - 변경된 컨테이너만 다시 시작 가능 -  변경되지 않은 서비스를 다시 시작하면 Compose는 기존 컨테이너를 다시 사용합니다. 컨테이너를 다시 사용한다는 것은 환경을 매우 빠르게 변경할 수 있음을 의미합니다.
##### - yaml파일을 이용 외부에서 환경변수를 설정 - 다른 환경 또는 다른 사용자에 맞게 구성을 사용자 정의 할 수 있습니다. (컴포즈로 외부에서 환경을 설정가능)

### DOCKER COMPOSE 설치 <br>
(<https://docs.docker.com/compose/install/>)

#### 1. Docker 엔진 설치
참고 : <a href ='/docker/2017/03/10/docker_install_exec.html'>Docker 설치방법 링크</a>
 
#### 2. Docker 컴포즈 설치
##### 2-1. 윈도우, 맥 기준
> (1.14.0을 설치할 경우) <br>
> $ Invoke-WebRequest "https://github.com/docker/compose/releases/download/1.14.0/docker-compose-Windows-x86_64.exe" -UseBasicParsing -OutFile $Env:ProgramFiles\docker\docker-compose.exe <br>

```
Latest release 확인 : https://github.com/docker/compose/releases
```

##### 2-2. 리눅스 우분투 기준
> (1.14.0을 설치할 경우) <br>
> $ curl -L https://github.com/docker/compose/releases/download/1.14.0/docker-compose-\`uname -s\`-\`uname -m\` > /usr/local/bin/docker-compose <br>

```
Latest release 확인 : https://github.com/docker/compose/releases
"Permission denied"의 경우 : $sudo -i 를 통해 root 권한을 가진 사용자로 로그인하여 진행해야 합니다.
```

#### 3. 실행파일에 실행 권한 적용
> $ sudo chmod +x /usr/local/bin/docker-compose

#### 4. 설치 확인
> $ docker-compose \--version

```
docker-compose version 1.14.0, build c7bdf9e
```

※ 대체 설치 방법 (Install using pip, Install as a container)
(<https://docs.docker.com/compose/install/#Alternative install options>)

### docker compose 실습 (mariaDB를 이용한 wordpress 실행하기)
(<https://docs.docker.com/samples/wordpress/#-via-docker-compose>)

1) docker-compose.yml 작성
> $ vi docker-compose.yml

##### 아래는 docker-compose.yml 내용으로 wordpress(포트포워딩 8080:80) mariadb image를 다운로드 후 실행시켜서 컨테이너화 하겠다는 의미입니다.

```
version: '2'

services:

  wordpress:
    image: wordpress
    ports:
      - 8080:80
    environment:
      WORDPRESS_DB_PASSWORD: example

  mysql:
    image: mariadb
    environment:
      MYSQL_ROOT_PASSWORD: example
```

2) docker-compose up (-d 옵션 : 데몬으로 실행)
> $ docker-compose up -d

```
Creating network "dockercompose_default" with the default driver
Pulling wordpress (wordpress:latest)...
latest: Pulling from library/wordpress
9f0706ba7422: Pull complete
4c407763908f: Pull complete
82e2bc3a45c1: Pull complete
c84e1013aed1: Pull complete
a3b5e03d7e24: Pull complete
.
.
.

```

3) http://localhost:8080 접속확인
##### (접속한 뒤 언어설정 후 사용자 등록을 하면 아래와 같은 화면이 나옵니다.)
![11_docker_compose_practice](/images/docker_compose_install_exec/1_docker_compose_practice.jpg)


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
