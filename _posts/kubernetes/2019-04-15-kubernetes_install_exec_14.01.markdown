---
layout: post
title:  "Kubernetes 클러스터 설치 및 구성(kube 1.14.1, ubuntu 16.04)"
date:   2019-04-15 11:21:42 +0900
categories: kubernetes
tags:
- kubernetes
---

### kubernetes 1.14.1 구성 방법 (ubuntu 16.04)

---------------------------
클러스터 구성 예시

| IP Address  |   Role   |
|:-------------:|:----------:|
|192.168.56.101| control-plane Node|
|192.168.56.102|   Worker Node   |
|192.168.56.103|   Worker Node   |

#### 1) k8s 설치 (전체 노드 실행)
1. Docker 설치 : 지난 글 참고 "<a href ='/docker/2019/01/10/docker_swarm.html'>Docker 설치 참고 (docker-ce=18.06.1~ce~3-0~ubuntu)</a>" <br> 
2. $ sudo apt install apt-transport-https
3. $ curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg \| sudo apt-key add
4. $ sudo add-apt-repository "deb https://apt.kubernetes.io/ kubernetes-$(lsb_release -cs) main"
5. $ sudo apt update
6. $ sudo apt install kubelet=1.14.1-00 kubeadm=1.14.1-00 kubectl=1.14.1-00 kubernetes-cni=0.7.5-00

#### 2) Control Plane Node (구 Master Node) 구성
1. kubeadm-config.yaml 파일 생성 (아래 control-plane-node 부분은 Bode IP나 HA구성을 할 경우 연결 시킬 LB 주소를 입력)
> $ vi kubeadm-config.yaml
```
apiVersion: kubeadm.k8s.io/v1beta1
kind: ClusterConfiguration
kubernetesVersion: v1.14.1
controlPlaneEndpoint: 192.168.56.101:6443
networking:
  podSubnet: 10.244.0.0/16
```

2. 생성된 파일을 이용하여 초기화
> $ sudo kubeadm init --config=kubeadm-config.yaml --experimental-upload-certs

```
결과 : 
... 생략
To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

You can now join any number of the control-plane node running the following command on each as root:

  kubeadm join 192.168.56.101:6443 --token tf9afj.p3hglea5pdk4v23m \
    --discovery-token-ca-cert-hash sha256:e46f607087a9daf7d30b9afd0ff4f5ec7c4eb41f49ca2f5ba271eb01137e8e78 \
    --experimental-control-plane --certificate-key f4fdb1c3d25baa05eef7c5cd92f49e0d01425679da7871b760ddee33eaaddf57

Please note that the certificate-key gives access to cluster sensitive data, keep it secret!
As a safeguard, uploaded-certs will be deleted in two hours; If necessary, you can use
"kubeadm init phase upload-certs --experimental-upload-certs" to reload certs afterward.

Then you can join any number of worker nodes by running the following on each as root:

kubeadm join 192.168.56.101:6443 --token tf9afj.p3hglea5pdk4v23m \
    --discovery-token-ca-cert-hash sha256:e46f607087a9daf7d30b9afd0ff4f5ec7c4eb41f49ca2f5ba271eb01137e8e78
```
> $ mkdir -p $HOME/.kube <br>
  $ sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config <br>
  $ sudo chown $(id -u):$(id -g) $HOME/.kube/config <br>
  :: kubectl 명령어를 사용하고 싶은 계정에서 실행 <br>
  

> $ kubeadm join 192.168.56.101:6443 --token tf9afj.p3hglea5pdk4v13m \ <br>
    --discovery-token-ca-cert-hash sha256:e46f607087a9daf7d30b9afd0ff4f5ec7c4eb41f49ca2f5ba271eb0113123 \ <br>
    --experimental-control-plane --certificate-key f4fdb1c3d25baa05eef7c5cd92f49e0d01425679da7871b760ddee33eaa123 <br>
  :: 타 노드에서 Control-Plane Node로 HA로 구성하고 싶을 경우 실행 (전체가 한개의 명령어) <br>
  
> $ kubeadm join 192.168.56.101:6443 --token tf9afj.p3hglea5pdk4v13m \ <br>
    --discovery-token-ca-cert-hash sha256:e46f607087a9daf7d30b9afd0ff4f5ec7c4eb41f49ca2f5ba271eb0113123<br>
  :: 타 노드에서 일반 Worker Node로 올릴 경우 실행 (전체가 한개의 명령어) <br>


#### 3) Worker Node 구성
1. 위 명령어 중
> $ kubeadm join 192.168.56.101:6443 --token tf9afj.p3hglea5pdk4v13m \ <br>
    --discovery-token-ca-cert-hash sha256:e46f607087a9daf7d30b9afd0ff4f5ec7c4eb41f49ca2f5ba271eb0113123 <br>
  위 형식의 명령어를 실행 <br>
  
#### 4) Cluster 구성 확인 (Control-Plane Node에서 실행)
1. kubectl get nodes
> $ kubectl get nodes
```
NAME     STATUS     ROLES    AGE   VERSION
master   Ready      master   1h   v1.14.1
node1    Ready   <none>   1h   v1.14.1
node2    Ready   <none>   1h   v1.14.1
```

### Toubleshooting

1. coredns가 안떠서 NotReady 상태인 경우
> $ kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/master/Documentation/kube-flannel.yml

2. running with swap on is not supported. Please disable swap 이 메세지가 뜰 경우
> $ sudo swapoff -a


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
