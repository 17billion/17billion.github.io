---
layout: post
title:  "New Year Chaos - HackerRank"
date:   2018-07-25 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### New Year Chaos
- 원래 줄에서(1, 2, 3, 4, 5..) 뒷 번호가 뇌물을 주고 몇번 새치기를 했는지 구하는 문제 <br>
최고 2칸까지만 가능하고 3칸부터는 Too chaotic 반환

```
Sample Input

2
5
2 1 5 3 4
5
2 5 1 3 4
Sample Output

3
Too chaotic
```
     
```java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class New_Year_Chaos {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int allCnt = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 0; i < allCnt; i++) {
            int cnt = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[cnt];

            for (int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[j] = num;
                int chaos = num - j - 1;
                if (chaos > 2) {
                    result = -1;
                    break;
                }
            }

            if (result != -1) {
                for (int j = 0; j < cnt-1; j++) {
                    if (j + 1 != arr[j]) {
                        for (int k = j + 1; j < cnt-1; k++) {
                            if (arr[k] < arr[k - 1]) {
                                result += 1;
                                int tmp = arr[k];
                                arr[k] = arr[k - 1];
                                arr[k - 1] = tmp;
                                j--;
                                break;
                            }
                        }
                    }
                }
            }

            if (result == -1) {
                System.out.println("Too chaotic");
            } else {
                System.out.println(result);
            }
            result = 0;
        }

    }
}

```

참고 사이트 <br>
HackerRank : https://www.hackerrank.com/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


