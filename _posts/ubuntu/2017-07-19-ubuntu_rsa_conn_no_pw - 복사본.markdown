---
layout: post
title:  "RSA 비대칭키를 이용하여 패스워드 없이 ssh 접속방법"
date:   2017-07-19 09:51:42 +0900
categories: Ubuntu
tags:
- Ubuntu
---

각 서버간 RSA 비대칭키를 이용 패스워드 없이 접속하는 방법
> 1. $ ssh-keygen 
> 2. $ ssh-copy-id {계정}@{목적지 서버}

예시) node2(192.168.56.102)에서 node1(192.168.56.101)로 패스워드 없이 ssh 접속 <br>
접속을 시도하는 서버(node2)는 Private Key가 필요, 목적지 서버(node1)는 Public Key가 필요 <br>
(Public Key키는 자물쇠, Private Key는 그 자물쇠를 여는 키)

### 1. Private / Public Key 생성 (node2에서 실행)
> $ ssh-keygen 
```bash
Generating public/private rsa key pair.
Enter file in which to save the key (/home/user/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Your identification has been saved in /home/user/.ssh/id_rsa.
Your public key has been saved in /home/user/.ssh/id_rsa.pub.
The key fingerprint is:
```
위처럼 메세지가 뜨는데 저장할 경로를 입력하라는 의미, 엔터를 누르면 /home/{계정명}/.ssh/ 경로에 키들이 생성 <br>
(Private Key : /home/{계정명}/.ssh/id_rsa / Public Key : /home/{계정명}/.ssh/id_rsa.pub) <br>

### 2. 생성한 public Key를 목적지 서버(node1)에 복사 <br>

#### 방법 1) <br>
생성된 public 키를 조회(cat)하여 그 내용을 목적지 서버의 /home/{계정}/.ssh/authorized_keys에 복사 <br>
(authorized_keys파일이 없으면 700권한으로 생성) <br>

#### 방법 2) <br>
ssh-copy-id {계정}@{목적지 서버 ip} 명령어로 복사 (자동으로 /home/{계정}/.ssh/authorized_keys에 복사됨) <br>
> $ ssh-copy-id user@192.168.56.101
```bash
The authenticity of host '192.168.56.101 (192.168.56.101)' can't be established.
ECDSA key fingerprint is 83:38:0a:e4:16:d5:20:c0:30:e7:0b:00:f1:0f:6d:b0.
Are you sure you want to continue connecting (yes/no)? yes
/usr/bin/ssh-copy-id: INFO: attempting to log in with the new key(s), to filter out any that are already installed
/usr/bin/ssh-copy-id: INFO: 1 key(s) remain to be installed -- if you are prompted now it is to install the new keys
user@192.168.56.101's password:
Number of key(s) added: 1
Now try logging into the machine, with:   "ssh 'user@192.168.56.101'"
and check to make sure that only the key(s) you wanted were added.
```

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
