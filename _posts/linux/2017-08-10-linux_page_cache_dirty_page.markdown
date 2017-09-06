---
layout: post
title:  "Linux : Page Cache, Dirty Page"
date:   2017-08-10 09:51:42 +0900
categories: linux
tags:
- linux
---

### 1. Page Cache란 ($ cat /proc/meminfo의 Cached)
- <a href ='http://www.linux-tutorial.info/modules.php?name=MContent&pageid=310'>Linux Page Cache의 역할은 디스크의 파일에 대한 액세스 속도를 향상시키기 위함</a>
- <a href ='http://unixadminschool.com/blog/2013/10/rhel-6-controlling-cache-memory-page-cache-size/'>Write back 방식에서 page cache는 사용자 프로세스가 읽기 또는 쓰기를 시작할 때마다 사용되며 커널은 사용자가 작업중인 파일의 사본을 찾으며 그러한 사본이없는 경우 캐시 메모리의 한 페이지를 새로 할당하고 디스크에서 읽은 해당 내용을 적재</a>
- page cache는 linux에서 유동적으로 할당됨 (<a href ='https://www.kernel.org/doc/gorman/html/understand/understand009.html'>Physical Page Allocation</a>)
- 임의적으로 disable 할 수 없음

### 2. Dirty Page란 ($ cat /proc/meminfo의 Dirty)
- 읽은 파일이 디스크에 업데이트 되지 않고 page cache 내 특정 공간에만 업데이트 되어 있는 경우가 있는데 이때 그 특정 공간을 dirty page라고 함
- dirty page의 내용들은 page cache의 효율화를 위해 정해진 설정을 통해 주기적으로 디스크에 적재되고 dirty 공간을 비우게 되는데 이를 flushing이라고 함 
> <a href ='http://unixadminschool.com/blog/2013/10/rhel-6-controlling-cache-memory-page-cache-size/'>dflush (kernel version 2.6.31까지), flush (커널 버전 2.6.32 이상)</a>
- 설정(dirty_ratio)보다 많은 dirty page를 지속적으로 사용하게 되면 linux에서는 flushing을 정상적으로 수행하지 못하는 것으로 판단하고 write를 중지. 거기에 상태 D 프로세스 증가시키며 높은 load average까지 발생시킴 <br>
	※ 이때 커널 로그(/var/log/kern.log)에서는 아래와 같은 로그가 발생<br>
		```
		INFO: task {process}:{port} blocked for more than 120 seconds.
		"echo 0 > /proc/sys/kernel/hung_task_timeout_secs" disables this message.
		```			  	
		<br>- hung_task.c 이 코드에 의하면 위는 state D 상태에 머물러 있는 작업을 감지하는 커널스래드이며 위 로그는 해당 작업이 D 상태로 머물러 있다는 의미 <br>
		- 아래처럼 튜닝을 해주면 해결되는 경우가 있음 (<a href ='https://www.blackmoreops.com/2014/09/22/linux-kernel-panic-issue-fix-hung_task_timeout_secs-blocked-120-seconds-problem/'>참고 사이트</a>)
> $ sudo sysctl -w vm.dirty_ratio=10  <br>
> $ sudo sysctl -w vm.dirty_background_ratio=5  <br>
> $ sudo sysctl -p  <br>
		




참고 사이트 <br>
linux-tutorial.info : <http://www.linux-tutorial.info/modules.php?name=MContent&pageid=310> <br>
unixadminschool.com : <http://unixadminschool.com/blog/2013/10/rhel-6-controlling-cache-memory-page-cache-size/> <br>
kernel.org(1) : <https://www.kernel.org/doc/gorman/html/understand/understand009.html> <br>
kernel.org(2) : <https://www.kernel.org/doc/Documentation/sysctl/vm.txt> <br>
www.blackmoreops.com : <https://www.blackmoreops.com/2014/09/22/linux-kernel-panic-issue-fix-hung_task_timeout_secs-blocked-120-seconds-problem/> <br>
Linux Tuning The VM (memory) Subsystem : <https://www.cyberciti.biz/faq/linux-kernel-tuning-virtual-memory-subsystem/> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
