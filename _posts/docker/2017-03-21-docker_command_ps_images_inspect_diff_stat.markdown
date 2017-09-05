---
layout: post
title:  "Docker Command - ps / images / inspect / diff / stats"
date:   2017-03-21 10:51:42 +0900
categories: docker
tags:
- docker
---

--------------

### Docker ps
##### 현재 실행되고 있는 컨테이너 정보를 조회하는 명령어입니다.
> docker ps [OPTIONS]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/ps/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--all, -a	|	false	|	모든 컨테이너 표시 (기본값 : 실행 중인 컨테이너)|
|--filter, -f	|	 	|	조건에 따라 출력 필터링|
|--format	|	 	|	Go 템플릿을 사용하여 컨테이너 정보 출력|
|--last, -n	|	-1	|	마지막으로 생성 된 컨테이너 n 개 표시|
|--latest, -l	|	false	|	최신 생성 컨테이너 표시|
|--no-trunc	|	false	|	정보를 생략하지 않고 출력|
|--quiet, -q	|	false	|	숫자 ID 만 표시|
|--size, -s	|	false	|	총 파일 크기 표시|


--------------

### Docker images

##### 이미지들 정보를 출력하는 명령어입니다. 

> docker images [OPTIONS] [REPOSITORY[:TAG]]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/images/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--all, -a	|	false	|	모든 이미지 표시 (기본값은 중간 이미지 숨기기)|
|--digests	|	false	|	요약보기|
|--filter, -f	|	 	|	조건에 따라 출력 필터링|
|--format	|	 	|	Go 템플릿을 사용하여 컨테이너 정보 출력|
|--no-trunc	|	false	|	정보를 생략하지 않고 출력|
|--quiet, -q	|	false	|	숫자 ID 만 표시|

--------------

### Docker inspect
##### 컨테이너의 상세한 정보를 조회하는 명령어입니다.

> docker inspect [OPTIONS] NAME\|ID [NAME\ID...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/inspect/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--format, -f	|	 	|	Go 템플릿을 사용하여 컨테이너 정보 출력|
|--size, -s	|	false	|	유형이 컨테이너 인 경우 총 파일 크기 표시|
|--type	|	 	|	지정된 유형에 대한 JSON 반환|

--------------

### Docker diff

##### 컨테이너가 실행되면서 변경된 파일 목록을 출력합니다. 비교 기준은 컨테이너를 생성한 이미지 내용입니다.

> docker diff CONTAINER

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/diff>)

--------------

### Docker stats

##### 컨테이너의 실시간 자원을 모니터링하는 명령어입니다.

> docker stats [OPTIONS] [CONTAINER...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/stats>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--all, -a	|	false	|	모든 컨테이너 표시 (기본값은 실행 중임)|
|--format	|	 	|	Go 템플릿을 사용하여 컨테이너 정보 출력|
|--no-stream	|	false	|	실시간 정보를 비활성화 하고 첫번째 값만 반환|

--------------


참고 사이트
- Docker 문서 사이트 : (<https://docs.docker.com/engine/reference/commandline/>)


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
