---
layout: post
title:  "Kubernetes Token 생성하기"
date:   2019-05-22 19:31:12 +0900
categories: kubernetes
tags:
- kubernetes
---

### Kubernetes Token 생성하기
- Token : kubeadm join 시 사용되는 Key
- 최초 kubeadm 후에 생기는 Token은 유효기간이 짧아 원하는 기간으로 재생성 시켜줘야함

1.  Token 생성 (ttl은 만료기간 / 1m, 3h 등으로 설정 가능하며 0일 경우 만료기간이 없음)
> $ kubeadm token create --ttl 0

```
wo512h.oqpo4lrad31sst8v
```

2. 생성된 Token 조회
> $ kubeadm token list

```
TOKEN                     TTL         EXPIRES   USAGES                   DESCRIPTION   EXTRA GROUPS
wo512h.oqpo4lrad31sst8v   <forever>   <never>   authentication,signing   <none>        system:bootstrappers:kubeadm:default-node-token

```

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
