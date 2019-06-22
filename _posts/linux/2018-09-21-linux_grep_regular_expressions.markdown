---
layout: post
title:  "Linux 명령어 - grep 정규표현식"
date:   2018-09-21 09:51:42 +0900
categories: Linux
tags:
- Linux
---

### grep
- grep은 유닉스를 위해 만들어진 텍스트 검색 기능을 가진 명령어이다. 그 이름은 유닉스 ed의 명령어로 비슷한 기능을 수행하는 g/re/p(global / regular expression / print)에서 유래되었다. <br>
grep 명령어는 파일이나 표준 입력을 검색하여 주어진 정규 표현식과 맞는 줄을 찾아 프로그램의 표준 출력으로 출력한다.
```
사용법: grep [옵션]... 패턴 [파일]...
grep [options] pattern [file...] 
grep [options] [-e pattern | -f patternFile] [file…] 
예제: grep -i 'hello world' menu.h main.c
```

- grep 정규표현식 메타문자 <br>
 ^ : 라인 시작 ex) '^string' <br>
 $ : 라인의 마지막 ex) 'string$' <br>
 . : 하나의 문자 매칭 ex) 's....g' <br>
 \* : 문자가 없거나 그 이상의 문자들 매칭 ex) '[s-z]*' <br>
 [] : []안의 문자 중 하나 이상 매칭 ex) '[Ss]tring' <br>
 [^] : []안의 문자 중 하나도 매칭되지 않는 문자 ex) '[^a-r]tring' <br>


- 예시
```bash
user@master:~$ cat test.txt
String Hi
Last String
String
StringT_T
string
Ztring
user@master:~$ grep '^String' test.txt
String Hi
String
StringT_T
user@master:~$ grep 'String$' test.txt
Last String
String
user@master:~$ grep 'S....g' test.txt
String Hi
Last String
String
StringT_T
user@master:~$ grep '[s-z]*' test.txt
String Hi
Last String
String
StringT_T
string
Ztring
user@master:~$ grep '[Ss]tring' test.txt
String Hi
Last String
String
StringT_T
string
user@master:~$ grep '[^a-r]tring' test.txt
String Hi
Last String
String
StringT_T
string
Ztring
```

참고 사이트 <br>
위키백과 : https://ko.wikipedia.org/wiki/Grep
real-world-systems. : https://www.real-world-systems.com/docs/grep.1.html

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
