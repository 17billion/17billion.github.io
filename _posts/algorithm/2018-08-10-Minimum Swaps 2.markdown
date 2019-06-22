---
layout: post
title:  "Minimum Swaps 2 - HackerRank"
date:   2018-08-10 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Minimum Swaps 2
- 가장 적게 swap을 해서 오름차순 정렬을 하는 문제 (최소 swap count)

```
Sample Input

4
4 3 1 2
Sample Output

3
```
     
```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Minimum_Swaps_2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        int[] arr = new int[cnt];
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0 ; i < cnt ; i++){
            arr[i] = Integer.parseInt(st.nextToken());            
        }        
        
        int result = Minimum_Swaps(arr);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
    
    static int Minimum_Swaps(int[] arr){
        int swap_cnt = 0;
        
        for(int i = 0 ; i < arr.length; i++){
            
            for(int j = i ; j < arr.length; j++){
                if(i+1 == arr[i]){
                    break;
                }
                if(i+1 == arr[j]){
                    int tmp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = tmp;
                    swap_cnt += 1;
                    break;
                }
            }
        }
        return swap_cnt;        
    }
}

```

참고 사이트 <br>
HackerRank : https://www.hackerrank.com/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


