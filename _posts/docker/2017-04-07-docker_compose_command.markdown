---
layout: post
title:  "Docker Compose Commands / Options"
date:   2017-04-07 09:51:42 +0900
categories: docker
tags:
- docker
---
### Docker compose 명령어 및 옵션 (<https://docs.docker.com/compose/reference/overview/#command-options-overview-and-help>)

> $ docker-compose [-f <arg>...] [options] [COMMAND] [ARGS...]


| Commands: |                                                 |   |
|-----------|-------------------------------------------------|---|
| build     | 서비스 빌드                        |   |
| bundle    | 작성 파일의 Docker 번들(이미지, 포트, 네트워크 정보파일)생성                  |   |
| config    | 유효성 검사 및 작성 파일 조회                    |   |
| create    | 서비스 생성                                   |   |
| down      | 컨테이너, 네트워크, 이미지 및 볼륨 중지 및 제거 |   |
| events    | 컨테이너에서 실시간 이벤트 수신                 |   |
| exec      | 실행중인 컨테이너 내 명령 실행                 |   |
| help      | 명령에 대한 도움말                          |   |
| images    | 목록 이미지 조회                              |   |
| kill      | 컨테이너 kill                              |   |
| logs      | 컨테이너 로그 출력                               |   |
| pause     | 서비스 일시 중지                               |   |
| port      | 포트 바인딩                |   |
| ps        | 목록 컨테이너 조회                                   |   |
| pull      | 서비스 이미지 가져 오기                         |   |
| push      | 이미지 push                              |   |
| restart   | 서비스 재시작                                |   |
| rm        | 중지 된 컨테이너 제거                           |   |
| run       | 일회적인 명령 실행                                |   |
| scale     | 서비스에 대한 컨테이너 수 설정                  |   |
| start     | 서비스 시작                                     |   |
| stop      | 서비스 중지                                     |   |
| top       | 실행중인 프로세스 정보 조회                          |   |
| unpause   | 서비스 일시중지 해제                           |   |
| up        | 컨테이너 만들기 및 시작                         |   |
| version   | Docker-Compose 버전 정보 표시                   |   |

##### * 명령어의 자세한 사용방법은 (<https://docs.docker.com/compose/reference/envvars/#compose_file>) 참고

| Options:                   |                                                                                                        |   |
|----------------------------|--------------------------------------------------------------------------------------------------------|---|
| -f, --file FILE            | 다른 docker-compose 파일지정 (기본값 : docker-compose.yml).                                           |   |
| -p, --project-name NAME    | 대체 프로젝트 이름 지정 (기본값 : 디렉토리 이름).                                            |   |
| --verbose                  | 보다 더 자세한 정보로 출력                                                                                       |   |
| -v, --version              | 버전 조회                                                                                      |   |
| -H, --host HOST            | 연결할 데몬 호스트                                                                                    |   |
|                            |                                                                                                        |   |
| --tls                      | TLS 플래그 사용 (--tlsverify) / 이 명령은 Linux에서 작동하는 인증서 셋만 생성                                                      |   |
| --tlscacert CA_PATH        | 해당 CA에서만 서명 한 인증서 신뢰                                                                        |   |
| --tlscert CLIENT_CERT_PATH | TLS 인증서 파일의 경로                                                                                 |   |
| --tlskey TLS_KEY_PATH      | TLS 키 파일의 경로                                                                                     |   |
| --tlsverify                | TLS를 사용하고 리모컨을 확인하십시오.                                                                  |   |
| --skip-hostname-check      | 데몬의 호스트 이름을 클라이언트 인증서에 지정된 이름과 비교하지 마십시오 (예 : 도커 호스트 IP address) |   |
| --project-directory PATH   | 대체 작업 디렉토리 지정 (기본값 : 작성 파일의 경로)                                                    |   |

참고 사이트
- Docker 문서 사이트 : (<https://docs.docker.com/compose/reference/overview/>)
- Medium - Lachlan Evenson : (<https://medium.com/@LachlanEvenson/5-minutes-dabbling-with-docker-distributed-application-bundles-dab-60f2dca3829>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
