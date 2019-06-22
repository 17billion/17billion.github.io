---
layout: post
title:  "Priority Queue (우선순위큐)"
date:   2018-02-07 18:00:00 +0900
categories: algorithm
tags:
- algorithm
---


#### Priority Queue(우선순위큐)란

- 먼저 들어간 데이터가 먼저 나오는 일반 큐와 달리 우선순위를 결정하여 들어온 순서와 상관없이 그 우선순위가 높은 데이터가 먼저 나가는 자료구조
- 자바에서는 Comparable의 compareTo를 이용하여 우선순위를 정해주면 된다. (아래 소스코드 참고)

```
import java.util.PriorityQueue;

public class PQ {
	static PriorityQueue<Person> getPriorityQueueOfPersons() {
	    PriorityQueue<Person> priorityQueue = new PriorityQueue<>();

	    priorityQueue.offer(new Person("B", 37));
	    priorityQueue.offer(new Person("C", 42));
	    priorityQueue.offer(new Person("E", 98));
	    priorityQueue.offer(new Person("A", 28));
	    priorityQueue.offer(new Person("D", 66));

	    return priorityQueue;
	}
	
	public static void main(String[] args) {
	    PriorityQueue<Person> priorityQueue = getPriorityQueueOfPersons();

	    // 나이가 많은 순으로 학생들 목록을 출력
	    while (!priorityQueue.isEmpty())
	        System.out.println(priorityQueue.poll());
	}
}

class Person implements Comparable<Person> {
    String name;
    int weight;

    public Person(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Person target) {
        return this.weight <= target.weight ? 1 : - 1;
    }

    @Override
    public String toString() {
        return "이름 : " + name + ", 몸무게 : " + weight;
    }
}
```

결과
```
이름 : E, 몸무게 : 98
이름 : D, 몸무게 : 66
이름 : C, 몸무게 : 42
이름 : B, 몸무게 : 37
이름 : A, 몸무게 : 28
```



참고 사이트 <br>
Hong's Store House : http://asuraiv.blogspot.com/2015/11/java-priorityqueue.html

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/


