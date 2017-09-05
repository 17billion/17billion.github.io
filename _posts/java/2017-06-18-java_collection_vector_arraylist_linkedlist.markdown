---
layout: post
title:  "JAVA collection : List(vector, arraylist, linkedlist) 소개 및 특징"
date:   2017-06-18 09:51:42 +0900
categories: java
tags:
- java
---

#### - Collection이란? (<https://docs.oracle.com/javase/tutorial/collections/intro/>) <br>
여러 요소의 그룹을 하나의 단위로 엮는 객체이고 다양한 인터페이스를 제공합니다. 주로 집계 데이터를 저장, 검색, 수정 및 전달하는 데 사용됩니다.

##### Class diagram of Java Collections framework <br> (<http://www.codejava.net/java-core/collections/overview-of-java-collections-framework-api-uml-diagram>)
![Class diagram of Java Collections framework](http://www.codejava.net/images/articles/javacore/collections/collections%20framework%20overview.png)


#### - List란
Collection 인터페이스를 확장한 자료형으로 동일한 데이터의 중복 입력이 가능하며 순차적이고 다량의 데이터를 입력할 때 주로 사용합니다.
종류는 Vector, Arraylist, Linkedlist가 있습니다.

##### Class diagram of List API <br> (<http://www.codejava.net/java-core/collections/class-diagram-of-list-api>)
![Class diagram of List API](http://www.codejava.net/images/articles/javacore/collections/List%20API%20class%20diagram.png)

### 각 자료형 특징
#### - Vector
- 배열로 구현
- 데이터 추가 시 공간이 부족한 경우 배열 크기를 2배로 증가시킴
- 다중 스레드에 적합 (동기화 제공)

#### - Arraylist
- 배열로 구현
- 데이터 추가 시 공간이 부족한 경우 배열 크기를 1.5배로 증가시킴
- 싱글 스레드에 적합 (동기화가 필요한 경우  Collections.synchronizedList() 사용)

#### - Linkedlist
- 연결리스트 라고도 불리며 노드 간에 연결을 통해서 리스트로 구현된 객체
- 다음 노드의 위치 정보만 가지고 있으며 인덱스를 가지고 있지 않기 때문에 탐색시 순차접근만 가능 (노트 탐색 시 시간이 많이 소요될 수 있음)
- 노드 추가/삭제는 위치정보의 수정만으로 가능하기 때문에 성능이 좋음

#### 중간 노드 삽입시 Vector, Arraylist / Linkedlist의 차이점

- Arraylist, Vector<br>
![Arraylist](http://www.nextree.co.kr/content/images/2016/09/jdchoi_20140225_arraylist_insert3.png)

- Linkedlist - 노드 생성 후 주소값만 변경 <br>
![Linkedlist](http://www.nextree.co.kr/content/images/2016/09/jdchoi_20140225_linklist_insert-1.png)

##### (<http://www.nextree.co.kr/p6506/> 참고)

### 종합
- 삽입할때 Vector의 경우는 동기화를 하며서 삽입하고 공간이 부족할 경우 배열크기를 2배씩 늘리기 때문에 시간이 비교적 ArrayList보다 오래 소요됩니다.
- 싱글스레드에서는 ArrayList를 사용하는 것을 권장합니다.
- 중간에 데이터를 삽입할때는 Vector, ArrayList 둘다 배열을 사용해서 중간에 넣고 나머지 배열을 복사 후 삽입하는 과정을 거치기 때문에 LinkedList에 비해서 시간이 오래걸립니다.
  (LinkedList의 경우는 중간에 삽입할 노드와 노드 간 주소값만 변경해주면 되기때문에 노드 추가 및 제거는 훨씬 빠름)
- Vector와 ArrayList의 경우는 배열을 사용해서 하기때문에 성능상 중요한 코드인 경우는 일반 배열을 사용해서 구현하는걸 추천합니다.

참고 사이트
- Oracle : (<https://docs.oracle.com/javase/tutorial/collections/intro//>)
- codejava : (<http://www.codejava.net/java-core/collections/class-diagram-of-list-api>)
- javaworld : (<http://www.javaworld.com/article/2077425/java-se/vector-or-arraylist-which-is-better.html>)
- NEXTREE : (<http://www.nextree.co.kr/p6506/>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
