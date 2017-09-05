---
layout: post
title:  "Docker features"
date:   2017-03-03 09:51:42 +0900
categories: docker
tags:
- docker
---

> Docker는 Container 플랫폼으로 Swarm, compose, kubernetes와 같은 Orchestration tool을 이용하면 많은 host에도 손쉬운 배포가 가능하고 변경, Scale-in/out이 가능하기 때문에 Devops 환경을 구축하려는 이들에게 도움을 줄 수 있습니다.

#### 1. Container, VM, Container + VM 비교
● Docker Container <br>
##### Host OS위에 Docker-Engine만 설치하면 기존 빌드해 놓은 이미지(실행에 필요한 binary 파일들)를 통해 application을 사용할 수 있습니다. 여러 Container를 동일한 시스템에서 실행하고 OS 커널을 다른 Container와 공유 할 수 있습니다. 각 Container는 사용자 공간에서 분리 된 프로세스로 실행됩니다. 일반적으로 VM보다 공간을 적게 차지하며 거의 즉시 시작됩니다. <br>
● VM <br>
##### VM 방식의 경우는 Host OS위에 VMware와 같은 Hypervisor를 설치한 후에 다시 Guest OS 및 Apllication을 설치해서 사용하는 구조입니다. Hypervisor를 사용하면 여러 대의 VM을 단일 시스템에서 실행할 수 있습니다. 각 VM에는 운영 체제의 전체 사본, 하나 이상의 응용 프로그램, 필요한 바이너리 및 라이브러리가 포함되어 있습니다. 부팅 속도가 느릴 수 있습니다. <br>
● Container + VM <br>
##### Container 방식과 VM 방식을 같이 유연하게 사용이 가능합니다. <br>

| Container | VM | Container + VM | 
| ---
| ![1_Container](/images/docker_features/1_Container.png) | ![2_VM](/images/docker_features/2_VM.png) | ![3_containers-vms-together](/images/docker_features/3_containers-vms-together.png) |

##### Container, VM, Container+VM 방식 (<https://www.docker.com/what-container#/virtual_machines>)


#### 2. Docker Container의 특징 <br>
##### Docker Container의 장점은 host OS와 완전히 분리되어 있는 VM 방식과 달리 Host OS와 커널을 공유하기 때문에 속도면에서 빠르다고 할 수 있습니다. 또한 위에서 언급했다시피 Orchestration tool을 이용하면 이미지 빌드, 배포, Scale-in/out 등을 손쉽게 할 수 있는 환경을 구성할 수 있습니다. 반대로 단점은 커널을 공유하기 때문에 Root 권한이 필요하고 이로인해 사용자 그룹 관리나 보안면에서 기존 VM방식보다 취약할 수 있습니다. 또한 커널을 공유하는 특성 때문에 Host OS 와 다른 OS의 이미지를 Container화 하는 것이 제한됩니다. <br>
● Docker devops <br>
##### Docker를 사용하는 개발자는 복잡한 데이터베이스를 설치 및 구성 할 필요가 없습니다. 앱이 도킹 (dockerized) 될 때, 그 복잡성은 쉽게 만들어지고, 공유되고 실행되는 Container로 푸시됩니다. Dockerfiles와 함께 제공되는 코드는 작업하기가 더 간단합니다. 종속성은 깔끔하게 패키지화 된 Docker 이미지로 가져오고 Docker 및 편집기가 설치된 모든 사용자는 몇 분 안에 응용 프로그램을 빌드하고 디버깅 할 수 있습니다. <br>
● Docker 종류 (<https://docker.gnupark.com/bbs/board.php?bo_table=docker&wr_id=65>) <br>
##### Docker Enterprise Edition (EE) <br>
##### - CentOS, RHEL (Red Hat Enterprise Linux), Ubuntu, SUSE Linux Enterprise Server (SLES), Oracle Linux 및 Windows Server 2016뿐만 아니라 클라우드 제공 업체를위한 통합 지원 및 인증 Container 플랫폼입니다. <br>
##### Docker Community Edition (CE) <br>
##### - 무료 Docker 제품의 새 이름입니다. Docker CE는 Mac 및 Windows 10, AWS 및 Azure, CentOS, Debian, Fedora 및 Ubuntu에서 실행되며 Docker Store에서 사용할 수 있습니다. Docker CE는 전체 Docker 플랫폼을 포함하며 개발자 및 DIY 작업 팀이 Container 앱을 만들기 시작하는 데 적합합니다. <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
