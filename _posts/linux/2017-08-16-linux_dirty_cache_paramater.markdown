---
layout: post
title:  "Linux : Dirty Page Parameters"
date:   2017-08-16 09:51:42 +0900
categories: Linux
tags:
- linux
---

### <a href ='/linux/2017/08/10/linux_page_cache_dirty_page.html'>Dirty Page 란 (링크)</a>

#### Dirty Page Parameters (<a href ='https://www.kernel.org/doc/Documentation/sysctl/vm.txt'>참고링크</a>) <br>
(ubuntu 14.04, Kernal 4.2.0-27-generic 기준) 
> $ sysctl -a \| grep dirty

```
vm.dirty_background_bytes = 0
vm.dirty_background_ratio = 10
vm.dirty_bytes = 0
vm.dirty_expire_centisecs = 3000
vm.dirty_ratio = 20
vm.dirty_writeback_centisecs = 500
vm.dirtytime_expire_seconds = 43200
```

- vm.dirty_background_bytes : 해당 임계치 사이즈(bytes)까지 dirty page가 도달하게 될 경우 flusher threads가 writeback을 진행

- vm.dirty_background_ratio : 사용가능한 총 메모리에서의 백분율로 해당 임계치 백분율까지 dirty page가 도달하게 될 경우 flusher threads가 writeback을 진행

- vm.dirty_bytes : 메모리에 flushing 되기 전 dirty page로 채울 수 있는 최대 값 (bytes)

- vm.dirty_expire_centisecs :  flushing 전 dirty page에 있을 수 있는 기간.  flusher threads가 writeback을 진행하면 더티 페이지가 얼마나 오래된 지 확인하고, 이 값보다 오래된 경우 디스크에 비동기 적으로 기록 (메모리에 더티 페이지를 보유하는 것이 안전하지 않기 때문에 이는 데이터 손실에 대한 보호 장치이기도 함)

- vm.dirty_ratio : 메모리에 flushing 되기 전 dirty page로 채울 수있는 시스템 메모리의 최대 백분율(%)
	- 설정(dirty_ratio)보다 많은 dirty page를 지속적으로 사용하게 되면 linux에서는 flushing을 정상적으로 수행하지 못하는 것으로 판단하고 write를 중지함, 거기에 높은 load average까지 발생시킴

- vm.dirty_writeback_centisecs : pdflush/flush/kdmflush 프로세스가 깨어나고 작업이 완료되어야하는지 확인하는 빈도 (500 > 5초)

- vm.dirtytime_expire_seconds : dirty time 만료되는 시간 조정하는 파라메터 (http://lists.ceph.com/pipermail/ceph-commit-ceph.com/2015-April/000083.html)

※ <a href ='https://www.kernel.org/doc/Documentation/sysctl/vm.txt'>_bytes 버전을 설정하면 _ratio 버전은 0이되고 그 반대의 경우도 마찬가지임</a>   <br>
※ <a href ='https://lwn.net/Articles/216853/'>dirty_ratio보다 dirty_backround_ratio 수치가 클 경우 자동으로 dirty_backround_ratio = dirty_ratio / 2로 설정 </a>  <br>
※ dirty_ratio, background_ratio의 수치는 사용가능한 총 메모리에서의 백분율로 계산 (<a href ='https://www.kernel.org/doc/Documentation/sysctl/vm.txt'>시스템 총 메모리와 동일하지 않음</a>)  <br>
> ratio = dirty / MemAvailable * 100 ($ cat /proc/meminfo) <br>
(MemAvailable이 없을 경우 : <a href ='https://www.reddit.com/r/linux/comments/3h7w8f/better_linux_disk_caching_performance_with/'>이용 가능한 메모리 = (MemFree + Cached - Mapped)</a> <br>

```
※ parameter 변경 및 적용방법
예) dirty_ratio, dirty_background_ratio
1) 설정 변경
$ sudo sysctl -w vm.dirty_ratio=10
$ sudo sysctl -w vm.dirty_background_ratio=5
2) 변경내용 적용 (커밋)
$ sudo sysctl -p
```

참고 사이트
kernel.org : <https://www.kernel.org/doc/Documentation/sysctl/vm.txt> <br>
lists.ceph.com : <http://lists.ceph.com/pipermail/ceph-commit-ceph.com/2015-April/000083.html> <br>
lwn.net : <https://lwn.net/Articles/216853/> <br>
reddit : <https://www.reddit.com/r/linux/comments/3h7w8f/better_linux_disk_caching_performance_with/> <br>


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
