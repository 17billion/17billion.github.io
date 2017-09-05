---
layout: post
title:  "Docker Command - start / exec / stop / rm / rmi"
date:   2017-03-20 10:51:42 +0900
categories: docker
tags:
- docker
---

--------------

### Docker exec
##### exec 명령어를 이용하면 실행중인 컨테이너에 명령어를 실행할 수 있습니다.
> docker exec [OPTIONS] CONTAINER COMMAND [ARG...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/exec/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|add-host|| 호스트 - IP 매핑 추가 (호스트 : ip)|
|--detach, -d	|	false	| 백그라운드에서 명령 실행|
|--detach-keys	|	 	|	컨테이너를 분리하기 위한 key sequence 재정의|
|--env, -e	|	 	|	환경 변수 설정|
|--interactive, -i	|	false	|	컨테이너와 연결(attach)되어 있지 않더라도 표준 입력을 유지|
|--privileged	|	false	|	명령에 확장 된 권한 부여|
|--tty, -t	|	false	|	가상 TTY 할당|
|--user, -u	|	 	|	사용자 이름 또는 UID (형식 : <name \| uid> [: <group \| gid>])|


--------------

### Docker start

##### 컨테이너를 시작시키는 명령어입니다. 

> docker start [OPTIONS] CONTAINER [CONTAINER...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/start/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--attach, -a	|	false	|	표준 입력(STDIN), 표준 출력(STDOUT) 또는 표준 에러(STDERR)를 연결|
|--checkpoint	|	 	|	체크포인트로 복원|
|--checkpoint-dir	|	 	|	사용자 체크포인트 저장소 디렉토리로 사용|
|--detach-keys	|	 	|	컨테이너를 분리하기 위한 key sequence 재정의|
|--interactive, -i	|	false	|	컨테이너와 연결(attach)되어 있지 않더라도 표준 입력을 유지|

--------------

### Docker stop
##### 컨테이너를 중지시키는 명령어입니다.

> docker stop [OPTIONS] CONTAINER [CONTAINER...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/stop/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--time, -t|10|중지시키기 까지 기다리는 시간 (초)|

--------------

### Docker rm

##### 컨테이너를 제거하는 명령어입니다.
##### stop은 컨테이너 중지, rm은 컨테이너를 제거하는 명령어입니다.

> docker rm [OPTIONS] CONTAINER [CONTAINER...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/rm>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--force, -f	|	false	|	실행중인 컨테이너를 강제로 제거 (SIGKILL 사용)|
|--link, -l	|	false	|	지정된 링크 제거|
|--volumes, -v	|	false	|	컨테이너와 연결된 볼륨 제거|
--------------

### Docker rmi

##### 이미지를 제거하는 명령어입니다.

> docker rmi [OPTIONS] IMAGE [IMAGE...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/rmi>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--force, -f	|	false	|	이미지 강제 제거|
|--no-prune	|	false	|	태그 없는 부모 이미지를 미삭제|

--------------

참고 사이트
- Docker 문서 사이트 : (<https://docs.docker.com/engine/reference/commandline/>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
