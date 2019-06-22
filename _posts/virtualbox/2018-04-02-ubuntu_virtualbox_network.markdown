---
layout: post
title:  "Virtualbox Host-Only Network 설정 및 고정 IP 설정 방법"
date:   2018-03-07 09:11:42 +0900
categories: virtualbox
tags:
- virtualbox
---

#### Virtualbox Host-Only Network 설정 및 고정 IP 설정 방법

1. Virtualbox Host-Only Network 설정
- Guest Machine > 설정 > 네트워크 > 어댑터 > Virtualbox Host-Only Network 설정 (어댑터1)
- Guest Machine > 설정 > 네트워크 > 어댑터 > NAT 설정(어댑터2)
![Host-only Network](/images\ubuntu_virtualbox_network/Host-only Network.png)
- 파일 > 네트워크 > 호스트 전용 네트워크 설정 > 더블클릭 > 아래 그림과 같이 설정 (DHCP 설정 해제)
![Host-only Network](/images\ubuntu_virtualbox_network/Host-only Network_2.png)

1. 고정 IP 설정
- Guest Machine 접속 <br>

```
$ vi /etc/network/interfaces
.
.
.
auto enp0s3
iface enp0s3 inet static
address 192.168.56.101
netmask 255.255.255.0
network 192.168.56.0
broadcast 192.168.56.255

auto enp0s8
iface enp0s8 inet dhcp

```
- 위 처럼 설정 후 $ service networking restart 실행




[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
