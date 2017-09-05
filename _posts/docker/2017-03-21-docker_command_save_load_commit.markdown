---
layout: post
title:  "Docker Command - save / load / commit"
date:   2017-03-21 11:51:42 +0900
categories: docker
tags:
- docker
---

--------------

### Docker save
##### 하나의 이미지를 tar로 압축하여 파일로 생성하는 명령어입니다. 기본적으로 STDOUT으로 스트리밍 됩니다.
> docker save [OPTIONS] IMAGE [IMAGE...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/save/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--output, -o|	|	STDOUT 대신 파일에 쓰기|


--------------

### Docker load

##### tar파일 또는 STDIN에서 이미지를 불러오는 명령어입니다.

> docker load [OPTIONS]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/load/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--input, -i	|	 	|	STDIN 대신 tar 파일에서 읽기|
|--quiet, -q	|	false	|	과도한 출력을 억제|

--------------

### Docker commit
##### 변경된 컨테이너를 새 이미지로 만드는 명령어입니다.

> docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/commit/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--author, -a	|	 	|	생성자 (예 : "John Hannibal Smith hannibal@a-team.com ")|
|--change, -c	|	 	|	Dockerfile 명령을 생성 된 이미지에 적용|
|--message, -m	|	 	|	커밋 메시지|
|--pause, -p	|	true	|	커밋 중 컨테이너 일시 중지|

--------------


참고 사이트
- Docker 문서 사이트 : (<https://docs.docker.com/engine/reference/commandline/>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
