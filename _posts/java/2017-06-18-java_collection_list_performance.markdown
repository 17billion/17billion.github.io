---
layout: post
title:  "JAVA collection : List Performance 비교"
date:   2017-06-19 09:51:42 +0900
categories: java
tags:
- java
---


최초 전체노드 삽입시 / 중간 노드 삽입시 / 중간 노드 조회 시 Performance를 확인했습니다. <br>
확인을 위해 작성한 로직 및 결과는 아래와 같습니다. <br>
##### * 아래 로직 중 TestCase 갯수는 {TCCNT}, 노드크기는 {NUM}을 의미합니다. ex) list 크기는 1000000, TestCase 갯수는 10
	
##### <a href ='/storage/java/ListPratice.java'>ListPratice.java (아래 내용과 동일)</a>

```java
import java.util.ArrayList;
import java.util.Vector;

public class ListPratice {
	private static final int NUM = 1000000;
	private static final int TCCNT = 10;

	private static long[] allInsertTimeArr = new long[TCCNT];
	private static long[] middleInsertTimeArr = new long[TCCNT];
	private static long[] middleGetTimeArr = new long[TCCNT];

	public static void main(String[] args) {
		System.out.println("--------VECTOR--------");
		testTC("vector");
		System.out.println("--------ARRAYLIST--------");
		testTC("arraylist");
		System.out.println("--------LINKEDLIST--------");
		testTC("linkedlist");
	}

	/**
	  * Vector insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void vecTest(int cnt) {
		Vector<Integer> a = new Vector<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;

	}

	/**
	  * arraylist insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void arrListTest(int cnt) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;
	}

	/**
	  * linkedlist insert, get practice
	  * @param int cnt(testcase cnt)
	  */
	public static void linListTest(int cnt) {
		java.util.LinkedList<Integer> a = new java.util.LinkedList<Integer>();
		long startTime = System.nanoTime();
		for (int i = 0; i < NUM; i++) {
			a.add(i);
		}
		long endTime = System.nanoTime();
		allInsertTimeArr[cnt] = endTime - startTime;
		startTime = System.nanoTime();
		a.add(1, 100);
		endTime = System.nanoTime();
		middleInsertTimeArr[cnt] = endTime - startTime;

		startTime = System.nanoTime();
		for (int i = 0; i < 10000; i++) {
			a.get(i);
		}
		endTime = System.nanoTime();
		middleGetTimeArr[cnt] = endTime - startTime;
	}

	/**
	  * print time (ns)
	  */
	static void outputArr() {
		System.out.println("---allInsertTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(allInsertTimeArr[i] + "ns");
		}

		System.out.println("---middleInsertTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(middleInsertTimeArr[i] + "ns");
		}
		
		System.out.println("---middleGetTimeArr---");
		for (int i = 0; i < TCCNT; i++) {
			System.out.println(middleGetTimeArr[i] + "ns");
		}
	}

	/**
	  * call list
	  */
	static void testTC(String type) {
		for (int i = 0; i < TCCNT; i++) {
			if (type == "vector") {
				ListPratice.vecTest(i);
			} else if (type == "arraylist") {
				ListPratice.arrListTest(i);
			} else {
				ListPratice.linListTest(i);
			}
		}
		ListPratice.outputArr();
	}
}

```



#### 결과
##### 아래는 결과를 표로 작성한 내용입니다. 시간 단위는 nanoTime(이하 ns) 기준입니다.

|                |                     | 0         | 1         | 2         | 3         | 4         | 5         | 6         | 7         | 8         | 9         | average (ns) |
|----------------|---------------------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|-----------|--------------|
| VECTOR    | allInsertTime (ns)  | 55565100  | 56296930  | 28933174  | 9141184   | 14301033  | 9947982   | 9217044   | 41523778  | 10230005  | 9446857   | 27178120.78  |
| ARRAYLIST    | allInsertTime (ns)  | 27274061  | 27590891  | 29797983  | 13085481  | 9073355   | 8556166   | 41103867  | 12495108  | 10826178  | 10294709  | 21121977.67  |
| LINKEDLIST | allInsertTime (ns)  | 34844488  | 30426287  | 22108858  | 26453877  | 56918093  | 9737358   | 11850740  | 11189416  | 8888613   | 10878835  | 24810729.44  |
|                |                     |           |           |           |           |           |           |           |           |           |           |              |
|                |                     | 0         | 1         | 2         | 3         | 4         | 5         | 6         | 7         | 8         | 9         | average (ns) |
| VECTOR     | middleIertTime (ns) | 742540    | 854100    | 761281    | 698809    | 856777    | 805013    | 828217    | 1025009   | 688545    | 691669    | 883551.1111  |
| ARRAYLIST     | middleIertTime (ns) | 1494897   | 799659    | 1043751   | 694792    | 778685    | 795196    | 1081235   | 1136121   | 1050444   | 689884    | 1062740.444  |
| LINKEDLIST | middleIertTime (ns) | 62027     | 39269     | 50871     | 9817      | 13387     | 9371      | 9817      | 12495     | 9817      | 12941     | 25534.66667  |
|                |                     |           |           |           |           |           |           |           |           |           |           |              |
|                |                     | 0         | 1         | 2         | 3         | 4         | 5         | 6         | 7         | 8         | 9         | average (ns) |
| VECTOR     | middleGetTime (ns)  | 2192367   | 164215    | 0         | 0         | 0         | 0         | 447       | 0         | 447       | 0         | 261941.7778  |
| ARRAYLIST     | middleGetTime (ns)  | 1099530   | 0         | 0         | 0         | 0         | 0         | 446       | 0         | 0         | 0         | 122219.5556  |
| LINKEDLIST | middleGetTime (ns)  | 130757529 | 115901823 | 118114716 | 112607694 | 167855965 | 113191373 | 120311099 | 112903549 | 114896002 | 118121856 | 136073511.8  |


### 결론
##### 1) 최초 전체 노드삽입 시 Vector의 경우는 동기화를 하며서 삽입하고 공간이 부족할 경우 배열크기를 2배씩 늘리기 때문에 시간이 비교적 ArrayList보다 오래 소요됩니다. <br>
##### 2) 중간에 데이터를 삽입할때는 Vector, ArrayList 둘다 배열을 사용해서 중간에 넣고 나머지 배열을 복사 후 삽입하는 과정을 거치기 때문에 LinkedList에 비해서 시간이 오래걸립니다. <br>
#####    (LinkedList의 경우는 중간에 삽입할 노드와 노드 간 주소값만 변경해주면 되기때문에 노드 추가 및 제거는 훨씬 빠름) <br>
##### 3) 중간 노드를 검색할 때 index가 있는 Vector, ArrayList는 성능이 좋지만 순차검색을 하는 LinkedList의 성능은 현저히 낮게 나타납니다.

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
