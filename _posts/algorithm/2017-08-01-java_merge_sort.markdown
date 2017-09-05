---
layout: post
title:  "병합정렬 (Merge Sort)"
date:   2017-08-01 09:51:42 +0900
categories: algorithm
tags:
- algorithm
---


#### 시간복잡도 n*log (n) 의 방법 MergeSort

#### 분할정복 알고리즘
1. 배열을 반씩 분할
2. 동일한 알고리즘을 통해 각각 정렬
3. 병합

![merge_sort](/images/java_merge_sort/merge_sort.png)

#### 과정
1. 6 개의 정렬되지 않은 정수형 배열 생성 : Arr (5, 8, 3, 9, 1, 2)
2. 배열을 Arr1 = (5, 8, 3)과 Arr2 = (9, 1, 2)로 분할
3. 다시 Arr3 = (5, 8)과 Arr4 = (3), Arr5 = (9,1)과 Arr6 = (2)로 분할
4. 다시 Arr7 = (5), Arr8 = (8), Arr9 = (9), Arr10 = (1) 및 Arr6 = (2)로 분할
5. 값을 비교 후 정렬 및 병합

#### Java 소스

```java
public class mergesort {
	static int marr[] = new int[6];

	
	public static void main(String[] args) {
		int marr[] = {5, 8, 3, 9, 1, 2};
		MergeSort(0, 5, marr);
	}
	public static void MergeSort(int left, int right, int arr[]) {
		int mid = 0;
		if (left < right) {
			mid = (left + right) / 2;
			MergeSort(left, mid, arr);
			MergeSort(mid + 1, right, arr);
			merge(left, mid, right, arr);
		}
	}

	public static void merge(int left, int mid, int right, int[] arr) {
		int l = left;
		int m = mid + 1;
		int k = left;

		while (l <= mid || m <= right) {
			if (l <= mid && m <= right) {
				if (arr[l] <= arr[m]) {
					marr[k] = arr[l++];
				} else {
					marr[k] = arr[m++];
				}
			} else if (l <= mid && m > right) {
				marr[k] = arr[l++];
			} else {
				marr[k] = arr[m++];
			}

			k++;
		}
		for (int i = left; i < right + 1; i++) {
			arr[i] = marr[i];
		}

		for (int i = 0; i < marr.length; i++) {
			System.out.print(arr[i] + " ");
		}

		System.out.println();

	}
}
```

#### 결과
```
5 8 3 9 1 2 
3 5 8 9 1 2 
3 5 8 1 9 2 
3 5 8 1 2 9 
1 2 3 5 8 9 
```

참고 사이트
- freecodecamp : <https://forum.freecodecamp.org/t/freecodecamp-algorithm-merge-sort-guide/16104>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
