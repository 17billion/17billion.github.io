---
layout: post
title:  "Docker 구축하기"
date:   2017-03-10 09:51:42 +0900
categories: docker
tags:
- docker
---
Docker 종류는 아래처럼 EE, CE가 있습니다. 저는 무료인 CE를 설치해서 실행해보겠습니다.
##### Docker Enterprise Edition (EE) <br>
##### - CentOS, RHEL (Red Hat Enterprise Linux), Ubuntu, SUSE Linux Enterprise Server (SLES), Oracle Linux 및 Windows Server 2016뿐만 아니라 클라우드 제공 업체를위한 통합 지원 및 인증 Container 플랫폼입니다. <br>
##### Docker Community Edition (CE) <br>
##### - 무료 Docker 제품의 새 이름입니다. Docker CE는 Mac 및 Windows 10, AWS 및 Azure, CentOS, Debian, Fedora 및 Ubuntu에서 실행되며 Docker Store에서 사용할 수 있습니다. Docker CE는 전체 Docker 플랫폼을 포함하며 개발자 및 DIY 작업 팀이 Container 앱을 만들기 시작하는 데 적합합니다. <br>

### 설치 및 실행 방법에 대해 설명하겠습니다.
##### (ubuntu 14.04 기준 <https://docs.docker.com/engine/installation/linux/ubuntu/#install-using-the-repository>) <br>
##### 1) 대부분의 사용자는 설치 및 업그레이드 작업을 쉽게하기 위해 Docker의 저장소를 설정 하고 설치합니다. (권장) <br>
##### 2) 일부 사용자는 DEB 패키지를 다운로드하여 수동으로 설치하고 업그레이드를 완전히 수동으로 관리합니다. 이 기능은 Docker를 인터넷에 액세스 할 수없는 에어 갭 시스템에 설치하는 경우에 유용합니다.
##### 저는 Docker에서 권장하는 1)의 방법으로 진행해보겠습니다. (Root 권한이 있는 사용자 계정으로 진행해야 합니다.)

#### 1. apt를 업데이트 합니다.
> $ sudo apt-get update

#### 2. aptHTTPS를 통해 저장소를 사용할 수 있도록 패키지 설치합니다.
> $ sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    software-properties-common
	
#### 3. Docker의 공식 GPG키를 추가합니다.
> $ curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - <br>
#### 3-2 (키 확인) 9DC8 5822 9FC7 DD38 854A E2D8 8D81 803C 0EBF CD88. <br>
> $ sudo apt-key fingerprint 0EBFCD88 <br>
pub   4096R/0EBFCD88 2017-02-22 <br>
      Key fingerprint = 9DC8 5822 9FC7 DD38 854A  E2D8 8D81 803C 0EBF CD88 <br>
uid                  Docker Release (CE deb) <docker@docker.com> <br>
sub   4096R/F273FCD8 2017-02-22 <br>

#### 4. 저장소 설정을 합니다. (각 기준 당 하나의 명령어 입니다.)
amd64 기준:
> $ sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \ 
   $(lsb_release -cs) \
   stable"

armhf 기준:
> $ sudo add-apt-repository \
   "deb [arch=armhf] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"

#### 5. apt를 업데이트 합니다.
> $ sudo apt-get update

#### 6.Docker engine을 설치합니다.
(최신버전 설치)
> $ sudo apt-get install docker-ce

(특정버전 설치) - <VERSION> 부분에 원하는 버전을 입력
> $ sudo apt-get install docker-ce=<VERSION>

#### 7. Docker를 실행합니다. (Hello-world)
> $ sudo docker run hello-world

![1_docker_install_complete](/images/docker_install_run/1_docker_install_complete.jpg)


-----------------------

#### Docker 제거 시
1. Docker CE 패키지 제거
> $ sudo apt-get purge docker-ce

2. 모든 이미지, Container 및 볼륨 삭제 (호스트의 이미지, Container, 볼륨 또는 사용자 정의 된 구성 파일은 자동으로 제거되지 않음)
> $ sudo rm -rf /var/lib/docker

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
