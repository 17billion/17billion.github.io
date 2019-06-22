---
layout: post
title:  "Repeated String - HackerRank"
date:   2018-06-16 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Repeated String
- 주어진 문자열을 숫자만큼 반복한 다음 전체 문자열의 'a' 갯수를 출력

```
Sample Input
aba
10
Sample Output
7
```

```java

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Repeated_String {

        public static void main(String[] args) throws IOException{
            
            long result = 0;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            String str = br.readLine();
            Long cnt = Long.parseLong(br.readLine());
            int strLeng = str.length();
            br.close();
            
            char[] sArr = str.toCharArray();
            int[] aCnt = new int[strLeng];
            for(int i = 0 ; i < strLeng ; i++){
                if(sArr[i] == 'a'){
                    if(i != 0){
                    aCnt[i] = aCnt[i-1] +1;
                    } else {
                        aCnt[i] = 1;
                    }
                } else{
                    if(i != 0){
                        aCnt[i] = aCnt[i-1];
                    } else{
                        aCnt[i] = 0;
                    }
                }
            }
            
            
            result = cnt / strLeng * aCnt[strLeng-1];    
            
            if(cnt % strLeng != 0){
                result += aCnt[(int)(cnt % strLeng-1)];
            }
            
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


