---
layout: post
title:  "Kubernetes Control-Plane Node에 Pod 띄울수 있는 방법 (Taints)"
date:   2019-04-24 18:21:42 +0900
categories: kubernetes
tags:
- kubernetes
---

### Kubernetes Control-Plane Node에 Pod 올리는 방법

#### 1) Control-Plane Node에  Pod를 올릴경우 아래처럼 Pending 상태로 진행되지 않음 (테스트를 위해 Contrl-Plane Node만 Ready인 상태로 진행)
1. deployment yaml 생성
> $ vi nginx-deployment.yaml <br>

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  labels:
    app: nginx
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.7.9
        ports:
        - containerPort: 80
```

2. Apply 
> $ kubectl apply -f nginx-deployment.yaml
```
deployment.apps/nginx-deployment created
```
3. Pod 상태 확인 (Pending 상태로 지속 됨)
> $ kubectl get pods
```
NAME                               READY   STATUS    RESTARTS   AGE
nginx-deployment-6dd86d77d-4rkhf   0/1     Pending   0          20m
```

4. Pod 상태 자세히 확인  (Pod Name은 3. 참고)
> $ kubectl describe pod nginx-deployment-6dd86d77d-4rkhf
```
Name:               nginx-deployment-6dd86d77d-4rkhf                                     
Namespace:          default                                                              
.
... 생략
Events:                                                                                                                                        
  Type     Reason            Age                 From               Message                                                                    
  ----     ------            ----                ----               -------                                                                    
  Warning  FailedScheduling  43s (x17 over 22m)  default-scheduler  0/3 nodes are available: 3 node(s) had taints that the pod didn't tolerate.
```

#### 2) 위처럼 Pending 상태로 안올라오는 이유는 Contrl-Plane Node에 Pod를 못 올리도록 설정되어 있기 때문
1. Contrl-Plane Node 확인 (아래 master는 Contrl-Plane Node Name)
> $ kubectl describe node master | grep Taints
```
Taints:             node-role.kubernetes.io/master:NoSchedule
```

#### 3) Pod를 올리고 싶을경우
1.  Taint 설정 해제
> $ kubectl taint nodes --all node-role.kubernetes.io/master-
```
node/master untainted
```
2. Pod 상태 재조회 (자동으로 리스타트 됨)
> $ kubectl get pods -o wide
```
NAME                               READY   STATUS    RESTARTS   AGE   IP           NODE     NOMINATED NODE   READINESS GATES
nginx-deployment-6dd86d77d-4rkhf   1/1     Running   0          35m   10.244.0.7   master   <none>           <none>
```

#### 4) 다시 Pod를 못 올리도록 설정하고 싶은 경우
1. Taint 설정
> $ kubectl taint nodes master node-role.kubernetes.io=master:NoSchedule
```
node/master tainted
```
2. 확인
> $ kubectl describe node master | grep Taints
```
Taints:             node-role.kubernetes.io=master:NoSchedule
```



[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
