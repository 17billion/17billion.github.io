---
layout: post
title:  "Sock Merchant - HackerRank"
date:   2018-06-05 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Sock Merchant
- 같은 숫자 양말의 갯수를 맞추는 문제 (같은 숫자 2개가 1켤레)

```
Input
9
10 20 20 10 10 30 50 10 20

Output
3
```

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Sock_Merchant {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int arrCnt = Integer.parseInt(br.readLine());
		
		int result = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for(int i  = 0 ; i < arrCnt ; i++){
			int num = Integer.parseInt(st.nextToken());
			if(!map.containsKey(num) || map.get(num) ==0){
				map.put(num, 1);
			} else{
				map.put(num, map.get(num)+1);
				if(map.get(num) ==2){
					result += 1;
					map.put(num, 0);
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
		}
}

```

참고 사이트 <br>
HackerRank : https://www.hackerrank.com/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


