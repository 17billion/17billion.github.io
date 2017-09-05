---
layout: post
title:  "Kubernetes 클러스터 설치 및 구성(kube 1.5.1, ubuntu 14.04)"
date:   2017-04-23 09:51:42 +0900
categories: kubernetes
tags:
- kubernetes
---

### 지난 글 "<a href ='/kubernetes/2017/04/20/kubernetes_overview.html'>Kubernetes Overview</a>" <br>
이번 글에서는 ubuntu 14.04, kubernetes 1.5.1 버전을 기준으로 kube-up을 통해 설치 및 구성하는 방법을 알아보겠습니다. (<https://kubernetes.io/docs/getting-started-guides/ubuntu/manual/>)

---------------------------
예시) 제가 구성할 클러스터는 아래와 같습니다.

| IP Address  |   Role   |
|:-------------:|:----------:|
|192.168.56.101| both master and node|
|192.168.56.102|   node1   |
|192.168.56.103|   node2   |

---------------------------

#### 설치 전 필수조건 (Prerequisites)
##### 1. 각 노드(서버)는 docker 버전 1.2 이상, bridge-utils이 설치가 되어 있어야 합니다. (apt-get install bridge-utils)
##### 2. 각 노드는 서로 통신 할 수 있어야 합니다. 마스터 노드는 인터넷에 연결되어 있어야 합니다.
##### 3. 이 가이드는 Ubuntu 14.04 LTS 64bit 서버기준이며, upstart 대신 systemd를 사용하는 Ubuntu 15에서는 작동하지 않습니다.
##### 4. etcd, flannel, k8s는 최신버전 설치를 권장합니다. (이 글에서는 k8s 1.5.1 버전을 설치합니다.)
##### 5. 각 노드 간 패스워드 없이 ssh 통신이 되어야 합니다. (<a href ='/elastic/2017/07/19/ubuntu_rsa_conn_no_pw.html'>Ubuntu : RSA 비대칭키를 이용하여 패스워드 없이 ssh 접속방법 참고</a>) <br>
##### 6. 모든 컴퓨터의 원격 사용자는 /bin/bash를 로그인 쉘로 사용가능해야하고 sudo 액세스 권한을 갖고 있어야 합니다. <br>

---------------------------

### kubernetes 구성 및 실행 (master로 설정할 서버에서 진행)

#### 1. kubernetes.git clone <br>
> (설치를 원하는 디렉토리에서 실행) <br>
$ git clone --depth 1 -b v1.5.1 --single-branch  https://github.com/kubernetes/kubernetes.git <br>

##### (최신버전의 경우는 git clone을 통해 가져올 수 있으나 kubeadm을 통해 설치가 가능합니다.) $ git clone -\-depth 1 https://github.com/kubernetes/kubernetes.git

#### 2. flannel, etcd, kubernetes export (flannel, etcd은 최신버전 권장)
해당 변수를 변경하여 etcd 버전, flannel 버전, K8S 버전를 정의 할 수 있습니다. (확인은 $ env로 가능)
> $ export KUBE_VERSION=1.5.1 <br>
$ export FLANNEL_VERSION=0.8.0 <br>
$ export ETCD_VERSION=3.2.5 <br>

- 각 최신버전 조회 : <https://github.com/coreos/etcd/releases/>, <https://github.com/kubernetes/kubernetes/releases>, <https://github.com/coreos/flannel/releases> <br>

#### 3. 클러스터 config 설정
> $ vi {kubernetes}/cluster/ubuntu/config-default.sh <br>
```
export nodes="root@192.168.56.101 root@192.168.56.102 root@192.168.56.103"
export roles="ai i i"
export NUM_NODES=${NUM_NODES:-3}
export SERVICE_CLUSTER_IP_RANGE=192.168.3.0/24
export FLANNEL_NET=172.16.0.0/16
```

##### - nodes는 각 노드에 원격으로 접속가능한 계정과 IP를 의미합니다.
##### - role에서 a는 마스터, i는 노드, ai는 마스터/노드를 의미합니다. <br>
##### - NUM_NODES는 전체 노드의 수를 정의합니다. (master 포함) <br>
##### - SERVICE_CLUSTER_IP_RANGE변수는 Kubernetes 서비스 IP 범위를 정의합니다. <br>
##### - FLANNEL_NET변수는 아래 IP와 충돌하지 않아야 flannel 오버레이 네트워크에 사용되는 IP 범위를 정의할 수 있습니다. <br>
##### 10.0.0.0        -   10.255.255.255  (10/8 prefix) <br>
##### 172.16.0.0      -   172.31.255.255  (172.16/12 prefix) <br>
##### 192.168.0.0     -   192.168.255.255 (192.168/16 prefix) <br>

##### ※ 프록시 설정이 필요한 경우 PROXY_SETTING을 {kubernetes}/cluster/ubuntu/config-default.sh에서 설정할 수 있습니다 . $ PROXY_SETTING="http_proxy=http://{server}:{port} https_proxy=https://{server}:{port}" <br>
 
#### 4. 클러스터 실행
##### 위의 모든 변수가 올바르게 설정되면 다음 명령을 사용 하여 전체 클러스터를 구성합니다.
>{kubernetes}/cluster/ 에서 싱행
> $ KUBERNETES_PROVIDER=ubuntu ./kube-up.sh

```bash
... Starting cluster using provider: ubuntu
... calling verify-prereqs
Identity added: /root/.ssh/id_rsa (/root/.ssh/id_rsa)
... calling kube-up
.
.
.
Cluster validation succeeded
Done, listing cluster services:

Kubernetes master is running at http://192.168.56.101:8080
```

#### 5. 어디서든 kubectl 명령어 사용할 수 있도록 복사
> {kubernetes}/cluster 에서 아래 커맨드 실행 <br>
> $ cp ubuntu/binaries/kubectl /opt/bin/.

#### 6. 노드 구성 확인
> $ kubectl get nodes
```
NAME             STATUS    AGE
192.168.56.101   Ready     1m
192.168.56.102   Ready     56s
192.168.56.103   Ready     37s
```


참고 사이트 <br>
release-notes : <https://cloud.google.com/container-engine/release-notes> <br>
kubernetes docs: <https://kubernetes.io/docs/getting-started-guides/ubuntu/manual/>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
