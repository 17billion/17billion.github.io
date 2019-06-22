---
layout: post
title:  "Linux 명령어 - awk를 이용하여 배열에 저장하기"
date:   2018-10-15 09:51:42 +0900
categories: Linux
tags:
- Linux
---

### awk
- awk는 기본적으로 패턴과 패턴을 처리하는 명령어 짝을 늘여놓은 구조로 이루어져 있다. 입력으로부터 한 줄씩을 읽어서 정규 표현식으로 조건이 맞는지를 검사하고 참으로 판명되면 그 줄에 대해 명령어를 실행하는 형식이다.
```
사용법: 
Usage: awk [POSIX or GNU style options] -f progfile [--] file ...
Usage: awk [POSIX or GNU style options] [--] 'program' file ...
```

#### awk를 이용하여 문자(열) 배열에 저장하기
- 배열의 원소번호(index)는 1부터 시작한다. 위의 예시에서 첫번째 원소가 arr[1]에 있다

- 예시
```bash
user@master:~$ echo "s:t:r:i:n:g" | awk '{split($0, arr, ":"); print arr[1]}'
s
user@master:~$ echo "S? T, R I! N G?" | awk '{ split($0, arr, /[ .,?!]+/); print arr[5] }'
N
```

참고 사이트 <br>
위키백과 : https://ko.wikipedia.org/wiki/AWK

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
