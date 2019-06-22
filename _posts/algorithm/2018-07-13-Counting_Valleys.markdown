---
layout: post
title:  "Counting Valleys - HackerRank"
date:   2018-07-13 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Counting Valleys
- 계곡의 갯수를 하는 문제 <br>
계곡 기준 : 초기위치가 0으로 시작하며 내려갔다가(D) 다시 0까지 올라와야(U) 계곡 카운트 + 1.

```
Sample Input
8
UDDDUDUU
Sample Output
1

Ex) 1 Valley
_/\       _
    \    /
     \/\/
```
     
```java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Counting_Valleys {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        int result = 0;
        int start = 0;

        char[] ground = br.readLine().toCharArray();

        for (int i = 0; i < cnt; i++) {
            char c = ground[i];
            int tmpSt = start;
            if (c == 'D') {
                start--;
            } else if (c == 'U') {
                start++;
            }

            if (tmpSt == -1 && start == 0) {
                result++;
            }
            
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();

    }

}

```

참고 사이트 <br>
HackerRank : https://www.hackerrank.com/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


