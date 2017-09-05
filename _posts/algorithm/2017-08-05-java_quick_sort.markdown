---
layout: post
title:  "퀵정렬 (quick Sort)"
date:   2017-08-05 09:51:42 +0900
categories: algorithm
tags:
- algorithm
---


#### 시간복잡도 최선/평균 n*log (n), 최악 n^2의 방법 Quick Sort

![Sorting_quicksort_anim](https://upload.wikimedia.org/wikipedia/commons/6/6a/Sorting_quicksort_anim.gif)

#### 분할정복 알고리즘
1. 리스트 가운데서 하나의 값을 선택하여 피봇(p)으로 지정
2. 피봇 앞에는 피봇보다 값이 작은 모든 값들을 위치, 피봇 뒤에는 피봇보다 값이 큰 모든 값들을 위치시켜 피봇을 기준으로 리스트를 둘로 분할
3. 분할된 두 개의 작은 리스트에 대해 재귀(Recursion)적으로 이 과정을 반복. 재귀는 리스트의 크기가 0이나 1이 될 때까지 반복
4. 재귀 호출이 한번 진행될 때마다 최소한 하나의 값은 최종적인 위치가 정해지므로, 이 알고리즘은 반드시 종료된다는 것이 보장됨


#### 과정
1) 피봇은 p, 리스트 왼쪽 끝과 오른쪽 끝에서 시작한 인덱스들은 l(left), r(right)
```
5 - 3 - 7 - 6 - 2 - 1 - 4    
l                   r   p 
```

2) 리스트 왼쪽에 있는 l 위치의 값이 피봇 값보다 크고, 오른쪽에 있는 r 위치의 값은 피봇 값보다 작으므로 둘을 교환한다.
```
5 - 3 - 7 - 6 - 2 - 1 - 4 
l                   r   p 
1 - 3 - 7 - 6 - 2 - 5 - 4 
l                   r   p 
```

3) r 위치의 값이 피봇 값보다 작지만, l 위치의 값도 피봇값보다 작으므로 교환하지 않는다.
```
1 - 3 - 7 - 6 - 2 - 5 - 4 
    l           r       p 
```

4) l위치를 피봇 값보다 큰 값이 나올 때 까지 진행해 r 위치의 값과 교환(swap)한다.
```
1 - 3 - 7 - 6 - 2 - 5 - 4 
        l       r       p >> swap 
1 - 3 - 2 - 6 - 7 - 5 - 4  
                         p
```

5) l위치가 r위치보다 같거나 커지면, l위치의 값과 피봇 값을 교환한다.
```
1 - 3 - 2 - 6 - 7 - 5 - 4 
           l,r          p >> swap 
1 - 3 - 2 - 4 - 7 - 5 - 6 
            p             
```

6) 최종 위치가 정해진 피봇 값을 제외하고 좌우의 리스트에 대해 각각 퀵 정렬을 재귀적으로 수행한다.
```
1 - 3 - 2 
1 - 2 - 3 
```
완성된 리스트는 다음과 같다.

```
1 - 2 - 3 - 4 - 5 - 6 - 7
```

#### Java 소스

```java
public class quicksort {
	static int marr[] = new int[6];

	public static void main(String[] args) {
		int marr[] = { 9, 7, 6, 8, 5, 4 };
		quickSort(0, 5, marr);
	}

	public static void quickSort(int low, int high, int[] arr) {

		if (low < high) {
			int pivot = arr[high];
			int left = low - 1;
			int right = high;

			while (true) {
				while (pivot > arr[++left]) {
				}
				// low < high >>> low < right
				while (low < right && pivot < arr[--right]) {
				}
				if (left >= right)
					break;

				arr = swap(left, right, arr);
			}
			arr = swap(left, high, arr);

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}

			System.out.println();

			quickSort(low, left - 1, arr);
			quickSort(left + 1, high, arr);

		}

	}

	private static int[] swap(int left, int right, int[] arr) {
		int tmp;
		tmp = arr[left];
		arr[left] = arr[right];
		arr[right] = tmp;

		return arr;
	}
}
```

#### 결과
```
4 7 6 8 5 9 
4 7 6 8 5 9 
4 5 6 8 7 9 
4 5 6 7 8 9 
```

참고 사이트
- wikipedia : <ko.wikipedia.org/wiki/퀵_정렬>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
