---
layout: post
title:  "Docker Command - build / push / pull / run "
date:   2017-03-20 09:51:42 +0900
categories: docker
tags:
- docker
---

> Docker는 다양한 명령어를 옵션과 함께 제공합니다.
이번 글에서는 build / push / pull / run / exec에 대해 알아보도록 하겠습니다.

--------------

### Docker build
##### build를 할 경우 Dockerfile이 레이어화 되어 이미지가 로컬에 생성됩니다. (빌드 후 조회방법 $ docker images)
> docker build [OPTIONS] PATH \| URL \| - 

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/build/#usage>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--add-host|| 호스트 - IP 매핑 추가 (호스트 : ip)|
	|	--build-arg	|	 	|	빌드 타임 변수 추가	|
	|	--cache-from	|	 	|	캐시 소스로 고려할 이미지 지정	|
	|	--cgroup-parent	|	 	|	컨테이너의 선택적 부모 cgroup	|
	|	--compress	|	FALSE	|	gzip을 사용하여 빌드 컨텍스트 압축	|
	|	--cpu-period	|	0	|	CPU CFS 기간 제한	|
	|	--cpu-quota	|	0	|	CPU CFS 할당량 제한	|
	|	--cpu-shares, -c	|	0	|	CPU 점유율 (상대적 가중치)	|
	|	--cpuset-cpus	|	 	|	실행을 허용하는 CPU (0-3, 0,1)	|
	|	--cpuset-mems	|	 	|	실행을 허용 할 MEM (0-3, 0,1)	|
	|	--disable-content-trust	|	TRUE	|	이미지 확인 건너 뛰기	|
	|	--file, -f	|	 	|	Dockerfile의 이름 (기본값 : 'PATH / Dockerfile')	|
	|	--force-rm	|	FALSE	|	이미지 생성을 실패했을때 임시컨테이너 제거	|
	|	--iidfile	|	 	|	이미지 ID를 파일에 기록	|
	|	--isolation	|	 	|	컨테이너 격리 기술	|
	|	--label	|	 	|	이미지 메타데이터 설정	|
	|	--memory, -m	|	0	|	메모리 제한	|
	|	--memory-swap	|	0	|	스왑 한도는 메모리 + 스왑과 같음, '-1'은 무제한 스왑을 가능하게 하는 설정	|
	|	--network	|	default	|	빌드하는 동안 RUN 명령어의 네트워킹 모드 설정	|
	|	--no-cache	|	FALSE	|	이미지를 만들 때 이전 빌드 때 사용한 캐시 미사용	|
	|	--pull	|	FALSE	|	항상 최신 버전의 이미지 pull	|
	|	--quiet, -q	|	FALSE	|	성공시 빌드 출력 및 인쇄 이미지 ID 미표시	|
	|	--rm	|	TRUE	|	성공적인 빌드 후에 임시 컨테이너 제거	|
	|	--security-opt	|	 	|	보안 옵션	|
	|	--shm-size	|	0	|	/ dev / shm의 크기	|
	|	--squash	|	FALSE	|	새로운 레이어로 생성	|
	|	--tag, -t	|	 	|	저장소, 이름 ('name(저장소) : tag')	|
	|	--target	|	 	|	빌드 할 대상의 단계 설정 (Dockerfile 내에서 단계를 지정가능)	|
	|	--ulimit	|	 	|	컨테이너 내 제한조건 설정	|

--------------

### Docker push

##### 로컬에 있는 이미지를 Docker Hub(public)이나 각 사용자가 생성한 Private 보관소에 업로드 하는 명령어입니다. 

> docker push [OPTIONS] NAME[:TAG]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/push/#description>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--disable-content-trust|true| 키를 이용한 이미지 서명 건너뛰기 (이미지 변조 방지 목적)|

--------------

### Docker pull
##### Docker Hub(public)이나 각 사용자가 생성한 Private 보관소의 이미지를 로컬로 불러오는 명령어입니다. 

> docker pull [OPTIONS] NAME[:TAG\|@DIGEST]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/pull/>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|--all-tags, -a|false|저장소에있는 모든 태그가 있는 이미지 다운로드|
|--disable-content-trust|true|이미지 서명 건너 뛰기|

--------------

### Docker run

##### 이미지를 컨테이너화시키는 명령어입니다. (실행 후 확인 명령어 docker ps)
##### build는 이미지를 생성하는 명령어, run은 실제 동작하는 프로세스로 컨테이너화 한다고 보시면 됩니다.

> docker run [OPTIONS] IMAGE [COMMAND] [ARG...]

##### OPTIONS (<https://docs.docker.com/engine/reference/commandline/run/#description>)

|NAME|DEFAULT|DESCRIPTION|
| --- | :---: | --- |
|add-host||맞춤 호스트 - IP 매핑 추가 (호스트 : ip)|
|	--attach, -a	|	 	|	표준 입력(STDIN), 표준 출력(STDOUT) 또는 표준 에러(STDERR)를 연결	|
|	--blkio-weight	|	0	|	10과 1000 사이의 IO(상대적 가중치)를 차단하거나 사용하지 않으려면 0 |
|	--blkio-weight-device	|	 	|	block IO 가중치 (상대 장치 무게)	|
|	--cap-add	|	 	|	cgroups의 특정 Capability를 사용, ALL을 지정하면 모든 Capability를 사용	|
|	--cap-drop	|	 	|	cgroups의 특정 Capability를 사용을 제외	|
|	--cgroup-parent	|	 	|	컨테이너의 부모 cgroup 선택 	|
|	--cidfile	|	 	|	파일에 컨테이너 ID를 사용	|
|	--cpu-count	|	0	|	CPU 수 (Windows 만 해당)	|
|	--cpu-percent	|	0	|	CPU 비율 (Windows 만 해당)	|
|	--cpu-period	|	0	|	CPU CFS (완전 공정 스케줄러) 기간 제한	|
|	--cpu-quota	|	0	|	CPU CFS (완전 공정한 스케줄러) 할당량 제한	|
|	--cpu-rt-period	|	0	|	마이크로 초 단위로 CPU 실시간 기간 제한	|
|	--cpu-rt-runtime	|	0	|	마이크로 초 단위로 CPU 실시간 런타임 제한	|
|	--cpu-shares, -c	|	0	|	CPU 점유율 (기본 값은 1024이며 각 값은 상대적 가중치)	|
|	--cpus	|	 	|	CPU 수	|
|	--cpuset-cpus	|	 	|	실행을 허용하는 CPU (0-3, 0,1)	|
|	--cpuset-mems	|	 	|	실행을 허용 할 MEM (0-3, 0,1)	|
|	--detach, -d	|	FALSE	|	백그라운드에서 컨테이너 실행|
|	--detach-keys	|	 	|	컨테이너를 분리하기 위한 key sequence 재정의	|
|	--device	|	 	|	컨테이너에 호스트 장치 추가	|
|	--device-cgroup-rule	|	 	|	cgroup allowed devices 목록에 규칙 추가	|
|	--device-read-bps	|	 	|	장치에서 읽기 속도 (바이트 / 초) 제한	|
|	--device-read-iops	|	 	|	장치에서 읽기 속도 (초당 IO) 제한	|
|	--device-write-bps	|	 	|	장치에 쓰기 속도 (바이트 / 초) 제한	|
|	--device-write-iops	|	 	|	장치에 쓰기 속도 (초당 IO) 제한	|
|	--disable-content-trust	|	TRUE	|	이미지 서명 확인 건너 뛰기	|
|	--dns	|	 	|	사용자 지정 DNS 서버 설정	|
|	--dns-opt	|	 	|	DNS 옵션 설정	|
|	--dns-option	|	 	|	DNS 옵션 설정	|
|	--dns-search	|	 	|	사용자 지정 DNS 검색 도메인 설정	|
|	--entrypoint	|	 	|	이미지의 기본 ENTRYPOINT를 덮어쓰기	|
|	--env, -e	|	 	|	환경 변수 설정	|
|	--env-file	|	 	|	환경 변수 파일 읽기	|
|	--expose	|	 	|	포트 또는 포트 범위 노출	|
|	--group-add	|	 	|	가입 할 추가 그룹추가	|
|	--health-cmd	|	 	|	Health 상태 점검	|
|	--health-interval	|	0s	|	검사를 실행하는 시간 (ms \| s \| m \| h) (기본값 0)	|
|	--health-retries	|	0	|	Health 체크 시 시도하는 횟수	|
|	--health-start-period	|	0s	|	Health-Retries Countdown (ms \| s \| m \| h)을 시작하기 전에 컨테이너를 초기화하는 시작 기간 (기본값 0)	|
|	--health-timeout	|	0s	|	하나의 검사를 실행할 수있는 최대 시간 (ms \| s \| m \| h) (기본값 0)	|
|	--help	|	FALSE	|	도움말	|
|	--hostname, -h	|	 	|	컨테이너 호스트 이름	|
|	--init	|	FALSE	|	ex)/bin/bash를 PID를 1로 실행 (TRUE일 경우 /dev/init가 pid 1)|
|	--interactive, -i	|	FALSE	|	컨테이너와 연결(attach)되어 있지 않더라도 표준 입력을 유지	|
|	--io-maxbandwidth	|	0	|	시스템 드라이브의 최대 입출력 대역폭 제한 (Windows 만 해당)	|
|	--io-maxiops	|	0	|	시스템 드라이브의 최대 입출력 제한 (Windows에만 해당)	|
|	--ip	|	 	|	IPv4 주소 (예 : 172.30.100.104)	|
|	--ip6	|	 	|	IPv6 주소 (예 : 2001 : db8 :: 33)	|
|	--ipc	|	 	|	사용할 IPC 네임 스페이스	|
|	--isolation	|	 	|	컨테이너의 격리유형을 설정|
|	--kernel-memory	|	0	|	커널 메모리 제한	|
|	--label, -l	|	 	|	컨테이너에 메타 데이터 설정	|
|	--label-file	|	 	|	레이블로 구분 된 파일을 읽기	|
|	--link	|	 	|	다른 컨테이너에 링크 추가	|
|	--link-local-ip	|	 	|	컨테이너 IPv4 / IPv6 링크 로컬 주소	|
|	--log-driver	|	 	|	컨테이너에 대한 로깅 드라이버	|
|	--log-opt	|	 	|	로깅 드라이버 옵션	|
|	--mac-address	|	 	|	컨테이너 MAC 주소 (예 : 92 : d0 : c6 ​​: 0a : 29 : 33)	|
|	--memory, -m	|	0	|	메모리 제한	|
|	--memory-reservation	|	0	|	유동적인 메모리 제한|
|	--memory-swap	|	0	|	스왑 한도 = 메모리 + 스왑, '-1'은 무제한 스왑을 가능	|
|	--memory-swappiness	|	-1	|	컨테이너 메모리 스왑 (0 ~ 100) 조정	|
|	--mount	|	 	|	컨테이너에 파일 시스템을 마운트	|
|	--name	|	 	|	컨테이너에 이름 지정	|
|	--net	|	default	|	컨테이너를 네트워크에 연결	|
|	--net-alias	|	 	|	컨테이너에 네트워크 범위 별칭 추가	|
|	--network	|	default	|	컨테이너를 네트워크에 연결	|
|	--network-alias	|	 	|	컨테이너에 네트워크 범위 별칭 추가	|
|	--no-healthcheck	|	FALSE	|	컨테이너가 지정한 HEALTHCHECK 비활성화	|
|	--oom-kill-disable	|	FALSE	|	OOM 킬러 미사용	|
|	--oom-score-adj	|	0	|	호스트의 OOM 환경 설정 조정 (-1000 - 1000)	|
|	--pid	|	 	|	사용할 PID 네임 스페이스	|
|	--pids-limit	|	0	|	컨테이너 pids 한도 조정 (무제한의 경우 -1로 설정)	|
|	--privileged	|	FALSE	|	이 컨테이너에 확장 된 리눅스 커널 기능(Capability) 권한을 부여	|
|	--publish, -p	|	 	|	컨테이너의 포트를 호스트에 게시	|
|	--publish-all, -P	|	FALSE	|	호스트에 연결된 컨테이너의 모든 포트를 외부에 노출	|
|	--read-only	|	FALSE	|	컨테이너의 루트 파일 시스템을 읽기 전용으로 마운트	|
|	--restart	|	no	|	컨테이너가 종료 할 때 적용 할 정책을 다시 시작	|
|	--rm	|	FALSE	|	컨테이너가 종료되면 컨테이너도 자동으로 제거합니다.	|
|	--runtime	|	 	|	이 컨테이너에 사용하는 런타임	|
|	--security-opt	|	 	|	보안 옵션	|
|	--shm-size	|	0	|	/ dev / shm의 크기	|
|	--sig-proxy	|	TRUE	|	모든 시그널을 프로세스에 전달(TTY 모드가 아닐 때도). 단 SIGCHLD, SIGKILL, SIGSTOP 시그널은 전달	|
|	--stop-signal	|	SIGTERM	|	컨테이너를 멈추게하는 신호	|
|	--stop-timeout	|	0	|	컨테이너를 중지하는 시간 초과 (초)	|
|	--storage-opt	|	 	|	컨테이너의 저장소 드라이버 옵션	|
|	--sysctl	|	map[]	|	Sysctl 옵션	|
|	--tmpfs	|	 	|	tmpfs 디렉토리 마운트	|
|	--tty, -t	|	FALSE	|	가상 TTY 할당	|
|	--ulimit	|	 	|	컨테이너 내 제한조건 설정	|
|	--user, -u	|	 	|	사용자 이름 또는 UID (형식 : <name \| uid> [: <group \| gid>])	|
|	--userns	|	 	|	사용할 사용자 이름 공간	|
|	--uts	|	 	|	사용할 UTS 네임 스페이스	|
|	--volume, -v	|	 	|	볼륨 마운트	|
|	--volume-driver	|	 	|	컨테이너 용 선택적 볼륨 드라이버	|
|	--volumes-from	|	 	|	지정된 컨테이너에서 볼륨 마운트	|
|	--workdir, -w	|	 	|	컨테이너 내부 작업 디렉토리	|

--------------

참고 사이트
- Docker 문서 사이트 : (<https://docs.docker.com/engine/reference/commandline/>)
- 류재영님의 블로그 : (<http://longbe00.blogspot.kr/>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
