---
layout: post
title:  "Docker aufs"
date:   2017-03-14 09:51:42 +0900
categories: docker
tags:
- docker
---

> Docker를 빌드할때 명령어들은 각각 layer로 이미지화가 됩니다. 이 이미지 layer들은 설치 후 Docker info 명령어를 사용해서 나타나는 "Root Dir"를 통해 레이어를 저장하는 Storage Driver를 확인할 수가 있습니다.
이번 글에서는 이미지들을 저장하는 Storge Driver 중 ubuntu에서 사용하는 aufs 디렉토리와 하부 디렉토리들인 /diff, /layers, /mnt 디렉토리에 대해 간단하게 알아보겠습니다.

#### - 이미지를 저장하는 디렉토리 확인방법 (Root Dir 확인)
> $ sudo docker info <br>
```
Storage Driver: aufs
 Root Dir: /mnt/docker/aufs 
 Backing Filesystem: extfs 
 Dirs: 35 
 Dirperm1 Supported: true 
```
 
#### - aufs 하부 디렉토리들의 각각 역할
```
../docker/aufs/diff : 이미지 layer 및 데이터 보관장소
../docker/aufs/layers : 이미지가 어떻게 쌓여있는지에 대한 메타데이터 저장소
../docker/aufs/mnt : 이미지가 container화 되었을때 마운트되는 저장소 (컨테이너 미실행인 경우 비어있는 비어있는 디렉토리로 남게됨)
```
##### 만약 의존성이 없는 독립적인 이미지를 삭제 할 경우 .../diff, .../layers의 데이터들이이 삭제되는것을 확인할 수 있습니다.


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
