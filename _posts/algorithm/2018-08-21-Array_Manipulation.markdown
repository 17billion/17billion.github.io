---
layout: post
title:  "Array Manipulation - HackerRank"
date:   2018-08-21 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Array Manipulation
- 1차원 배열 인덱스 a-1부터 b-1까지 k의 값을 누적해서 더한 후 최대값 구하기


```
Sample Input
5 3 : arrSize / arrCnt
1 2 100 : index arr[1-1] ~ arr[2-1]까지 100 더하기
2 5 100 : index arr[2-1] ~ arr[5-1]까지 100 더하기 (위 배열에서 누적)
3 4 100 : index arr[4-1] ~ arr[3-1]까지 100 더하기 (위 배열에서 누적)
Sample Output
200

ex) 
//5 3        : arrSize / arrCnt       :    arr
//1 2 100    : 100 100 0 0 0          : 100 0 -100 0 0 
//2 5 100    : 100 200 100 100 100    : 100 100 -100 0 0 
//3 4 100    : 100 200 200 200 100    : 100 100 0 -100 0

answer : 200 (100+100)
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


