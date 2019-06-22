---
layout: post
title:  "Jumping on the Clouds - HackerRank"
date:   2018-06-31 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Jumping on the Clouds
- 천둥(1)을 피해서 마지막 구름까지 갈 수 있는 가장 최소한의 방법<br>
 움직이는 방법은 한번에 최대 두칸 혹은 한칸만 움직일 수 있음

```
Sample Input
7
0 0 1 0 0 1 0
Sample Output
4
```

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Jumping_on_the_Clouds {

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            int cnt = Integer.parseInt(br.readLine());
            int[] cloud = new int[cnt];
            int result = 0;
            int loc = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            for(int i = 0 ; i < cnt ; i++){
                cloud[i] = Integer.parseInt(st.nextToken());                
            }
            
            while(loc <= cnt){
                result += 1;
                loc += 2;
                if(loc >= cnt){
                    if(loc > cnt){
                    result -= 1;
                    }
                    break;
                } else if(cloud[loc]==1){
                    loc -= 1;
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


