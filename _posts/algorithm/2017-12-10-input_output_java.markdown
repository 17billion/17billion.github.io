---
layout: post
title:  "빠른 입출력 (JAVA)"
date:   2017-12-10 21:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### 빠른 입력

```
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

// 한 줄에 정수 하나가 주어지는 경우
int N = Integer.parseInt(br.readLine());

// 한 줄에 정수 N개가 공백으로 분리되어 주어지는 경우
int[] A = new int[N];
StringTokenizer st = new StringTokenizer(br.readLine()); 
for (int i = 0; i < N; i ++) {
    A[i] = Integer.parseInt(st.nextToken());
}
br.close();
```

#### 빠른 출력

```
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

int N = 1000;
int[] A = new int[N];
for (int i = 0; i < N; i ++) {
    A[i] = i * i;
    bw.write(String.valueOf(A[i]]) + "\n");
}
bw.write("String\n");

bw.flush(); // 이 부분이 없으면 출력이 되지 않을 수도 있음
bw.close();
```

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


