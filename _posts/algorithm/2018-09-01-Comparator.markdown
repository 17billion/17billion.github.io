---
layout: post
title:  "Comparator - HackerRank"
date:   2018-09-01 20:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Comparator
- Comparator를 이용해서 오브젝트 정렬하기


```
Sample Input
5
amy 100
david 100
heraldo 50
aakansha 75
aleksa 150

Sample Output
aleksa 150
amy 100
david 100
aakansha 75
heraldo 50
```
     
```java
import java.util.*;

class Player {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
}

class Checker implements Comparator<Player> {
	// complete this method

	public int compare(Player a, Player b) {
		if (a.score == b.score) {
			return a.name.compareTo(b.name);
		} else {
			return a.score < b.score ? 1 : -1;
		}
	}
}

public class Comparator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();

		Player[] player = new Player[n];
		Checker checker = new Checker();

		for (int i = 0; i < n; i++) {
			player[i] = new Player(scan.next(), scan.nextInt());
		}
		scan.close();

		Arrays.sort(player, checker);
		for (int i = 0; i < player.length; i++) {
			System.out.printf("%s %s\n", player[i].name, player[i].score);
		}
	}
}
```

#### PriorityQueue, Comparable 이용
```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Player implements Comparable<Player> {
	String name;
	int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(Player o) {
		if (this.score == o.score) {
			return this.name.compareTo(o.name);
		} else {
			return this.score < o.score ? 1 : -1;
		}
	}

	public String toString() {
		return name + " " + score;
	}
}

public class Comparator_pq {

	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Player> pq = new PriorityQueue<Player>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int score = Integer.parseInt(st.nextToken());
			pq.add(new Player(name, score));
		}

		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}
```

참고 사이트 <br>
HackerRank : https://www.hackerrank.com/

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


