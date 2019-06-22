---
layout: post
title:  "Rod_Cutting"
date:   2017-11-12 21:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Rod_Cutting
- 막대기 자르기
- 길이 N인 막대기와 각 길이에 대한 값이 주어지며
막대기를 절단하여 얻을 수 있는 최대값을 구하는 문제

| length | 1 | 2 | 3 | 4 | 5 |
| price | 1 | 4 | 4 | 6 | 9 |

예시)

| 막대기 길이 | 1 | 2 | 3 | 4 |
| 최대값 | 1 | 4 | 5 | 8 |


- 문제를 푸는 방식에는 top-down(하향식), bottom-up(상향식)이 있는데
top-down의 경우는 전체의 경우의 수를 재귀형태로 푸는 방식이고
bottom-up은 하위문제부터 풀어가며 그 전 값을 참조 하여 푸는 방식
- 아래 그림 참고 (왼쪽 그림 : top-down,  오른쪽 그림 :  bottom-up)
- top-down 방식의 경우 반복되는 구간으로 인해 불필요하게 시간 소요되기 때문에 bottom-up 방식으로 풀어야함
![](https://gsourcecode.files.wordpress.com/2012/04/screenshot-at-2012-04-14-164607-e1334415808999.png)

아래는 JAVA 코드 (bottom-up)
```
class RC
{ 
    static int cut_Rod(int p[],int n) 
    { 
        int result[] = new int[n+1]; 
        result[0] = 0; 

        for (int i = 1; i<=n; i++) 
        { 
            int max_value = Integer.MIN_VALUE; 
            for (int j = 0; j < i; j++) 
                max_value = Math.max(max_value, p[j] + result[i-j-1]); 
            result[i] = max_val; 
        } 
  
        return result[n]; 
    } 

    public static void main(String args[]) 
    { 
        int arr[] = new int[] {1, 4, 4, 6, 9}; 
        System.out.println("Max :  " + cut_Rod(arr, arr.length)); 
    } 
} 
```

참고 사이트 <br>
gsourcecode : https://gsourcecode.wordpress.com/2012/04/12/cutting-rods-introduction-to-dynamic-programming/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


