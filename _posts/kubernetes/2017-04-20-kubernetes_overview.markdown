---
layout: post
title:  "Kubernetes Overview"
date:   2017-04-20 09:51:42 +0900
categories: kubernetes
tags:
- kubernetes
---

<a href ='https://github.com/kubernetes/community/blob/master/contributors/design-proposals/architecture.md'>Kubernetes란</a> Container 배치 및 관리를 위한 플랫폼(Orchestratior)입니다.

#### 1. 개요
> Kubernetes는 Google의 과거 연구에서 영감을 얻어 만들어진 호스트 클러스터 내 애플리케이션 Container의 배포, 확장, 관리 및 구성을 위한 프로덕션급의 오픈 소스 인프라이며 줄여서 k8s라고도 불립니다. <br>

물리적 / 가상 컴퓨팅, 네트워크 및 스토리지 인프라를 조율하는 부담을 줄이고 애플리케이션 운영자와 개발자가 container 중심의 셀프 서비스 운영을 위해 전적으로 집중할 수 있도록 지원합니다. <br>
사용자 정의 된 워크플로우 및 높은 수준의 자동화를 구축하기위한 안정적이고 이동 가능한 플랫폼을 제공합니다. <br>
주로 여러 Container로 구성된 응용 프로그램을 대상으로 합니다. 따라서 pod와 label을 사용하여 Container를 단단히 결합하고 느슨하게 결합하여 쉽게 관리하고 발견 할 수 있습니다. <br>


#### 2. Kubernetes가 지향하는 목표
1. Portable(휴대성)
- public cloud, private cloud, bare metal, laptop 등 모든 곳에서 일관된 동작을하며, 애플리케이션과 툴은 생태계 전반뿐만 아니라 개발 환경, 생산 환경에 이식이 가능합니다. <br>
2. General-purpose(범용)
- 모든 주요 작업 범주를 실행하여 single infrastructure, stateless and stateful, microservices and monoliths, services and batch, greenfield and legacy에서 모든 작업를 실행할 수 있도록해야합니다. <br>
3. Meet users partway(현재 서비스에서도 사용가능한 플랫폼).
- 단순한  <a href ='https://en.wikipedia.org/wiki/Greenfield_project'> 그린 필드 </a> 클라우드 기반 응용 프로그램을 제공하는 것이 아니라 모든 사용자를 충족시킵니다. 마이크로 서비스 및 클라우드 고유 응용 프로그램의 배포 및 관리에 중점을두고 있지만 <a href ='https://en.wikipedia.org/wiki/Monolithic_application'>모듈성이 없는 응용 프로그램</a> 및 레거시 응용 프로그램을 쉽게 마이그레이션 할 수 있는 메커니즘을 제공합니다. <br>
4. Flexible(유연함). 
- 단품으로 사용될 수 있으며 (대부분의 경우) Kubernetes는 기본 제공 기능 대신 자신의 솔루션을 사용하는 것을 방해하지 않습니다. <br>
5. Extensible(확장가능).
- Kubernetes를 사용하면 기본 제공 기능에서 사용하는 것과 동일한 인터페이스를 노출하여 이를 환경에 통합하고 필요한 추가 기능을 추가 할 수 있습니다. <br>
6. Automatable(자동화). 
- 수동 작업의 부담을 극적으로 줄이는 것을 목표로 합니다. API를 통해 사용자가 원하는 의도를 지정하여 선언적 제어를 지원할뿐만 아니라 상위 수준의 조정 및 자동화를 지원하는 명령형 제어를 지원합니다.  <a href ='https://ko.wikipedia.org/wiki/%EC%84%A0%EC%96%B8%ED%98%95_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D_%EC%96%B8%EC%96%B4'>선언적 접근 방식</a>은 시스템 자가복구 및 자동화 기능의 핵심입니다. <br>
7. Advance the state of the art(최첨단 기술 향상). 
- 비 클라우드 기본 응용 프로그램을 지원하려고하지만 응용 프로그램을 자체 관리에 참여 시키는 등 advance the cloud-native 및 DevOps를 향상시키기 위해 노력합니다. 그러나 이렇게함으로써 우리는 응용 프로그램이 Kubernetes API에 종속되도록 강요하지 않을 것입니다. <br>

#### 3. Kubernetes Design and Architecture

#### 3-1. 아키텍쳐 그림 (<a href='https://upload.wikimedia.org/wikipedia/commons/b/be/Kubernetes.png'>wikimedia.org</a>)
![Kubernetes.png](https://upload.wikimedia.org/wikipedia/commons/b/be/Kubernetes.png)


#### 3-2. 구성요소
Kuberbetes는 Master와 Node로 구분되지만 구축 시 Master, Node를 하나에 서버에서 동작하도록 설정이 가능합니다.
ex) master+node0, node1, node2

#### Master(The Kubernetes Control Plane) <br>
1) <a href='https://kubernetes.io/docs/tasks/administer-cluster/configure-upgrade-etcd/'> etcd </a> <br>
- Kubernetes 클러스터 상태에 대한 정보를 저장하는 저장소입니다. 이슈 시 kube-apiserver, kube-scheduler, kube-controller-manager 컴포넌트에도 영향이 있습니다.

2) <a href='https://v1-6.docs.kubernetes.io/docs/admin/kube-apiserver/'> kube-apiserver </a>
- pod, service, replication controller 및 기타를 포함하는 API 개체의 데이터의 유효성을 검사하고 구성합니다. API 서버는 REST 작업을 서비스하고 다른 모든 구성 요소가 상호 작용하는 클러스터의 공유 상태에 대한 프론트 엔드를 제공합니다. 프로세스가 내려가면 kubectl 명령어가 동작하지 않습니다.

3) <a href='https://v1-6.docs.kubernetes.io/docs/admin/kube-scheduler/'> kube-scheduler </a>
- 각 컴포넌트들이 요청한 워크로드 동작을 체크하여 실행되도록 하는 컴포넌트 입니다. ex) 이슈로 인해 pod가 내려가서 replication controller에 의해 자동으로 다시 실행 될때 kube-scheduler가 없으면 pending 상태에서 진행되지 않습니다.

4) <a href ='https://v1-6.docs.kubernetes.io/docs/admin/kube-controller-manager/'> kube-controller-manager </a>
- Kubernetes에서 컨트롤러는 apiserver를 통해 클러스터의 공유 상태를 감시하고 현재 상태를 원하는 상태로 이동하려고 변경하는 제어 루프입니다. 현재 Kubernetes와 함께 제공되는 컨트롤러의 예로는 replication controller, endpoints controller, namespace controller, and serviceaccounts controller가 있습니다. 이 프로세스가 내려가면 이슈로 인해 스탑된 pod가 다시 실행되지 않습니다.

#### Node
1) <a href='https://v1-6.docs.kubernetes.io/docs/admin/kubelet/'> kubelet </a>
- 각 Node 내 Pods와 이에 속한 Container, 이미지, 볼륨을 관리합니다. kubelet은 다양한 메커니즘 (주로 API를 통해)을 통해 제공되는 Pod Spec 정보를 가져 와서 해당 PodSpec에 설명 된 Container가 실행 중이며 건강한 상태를 유지하도록합니다.
kubelet은 Kubernetes가 생성하지 않은 Container를 관리하지 않습니다. 컴포넌트 프로세스가 내려가도 사용자 관점의 서비스는 이상이 잆지만 node들에 있는 pod나 Container들이 스탑되면 자동으로 올라오지 않습니다.

2) <a href='https://v1-6.docs.kubernetes.io/docs/admin/kube-proxy/'> kube-proxy </a>
- 각 노드에서 동작하는 네트워크 프록시 입니다. 이것은 각 노드의 Kubernetes API에 정의 된대로 서비스를 반영하며 간단한 TCP, UDP 스트림 전달 또는 라운드 로빈 TCP, 백엔드 집합에 걸친 UDP 전달을 수행 할 수 있습니다. 서비스 클러스터 ips 및 포트는 현재 서비스 프록시에서 연 포트를 지정하는 Docker-link-compatible 환경 변수를 통해 찾습니다. 이러한 클러스터 IP에 클러스터 DNS를 제공하는 선택적 애드온이 있습니다.

참고 사이트 <br>
Kubernetes Github : <https://github.com/kubernetes/community/blob/master/contributors/design-proposals/architecture.md> <br>
Kubernetes Doc : <https://kubernetes.io/docs/>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
