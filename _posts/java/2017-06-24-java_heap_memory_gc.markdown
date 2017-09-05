---
layout: post
title:  "JAVA Heap Memory, Garbage Collection(GC)"
date:   2017-06-24 09:51:42 +0900
categories: java
tags:
- java
---


![gc](/images/java_heap_memory_gc/JVM_Components.png)
> 위 그림은 JVM(HotSpot)의 아키텍쳐이며 보라색으로 음영표시가 되어 있는 부분이 핵심 구성요소입니다.

- Heap Memory, Just-In-Time(이하 JIT) Compiler, Garbage Collector는 성능을 튜닝할때 초첨을 맞추는 세가지 구성요소입니다.
- 대부분의 튜닝은 Heap Memory의 크기를 조정하고 Garbage Collector 타입을 선택하는 것과 연관이 있습니다.
- JIT Compiler도 성능에 큰 영향을 미치지만 최근 버전의 JVM에서는 별도 튜닝의 필요가 거의 없습니다.


### Heap Memory
- 객체들이 저장되는 저장소입니다.
- Young Generation, Old Generation 및 Permanent Generation으로 구성되어 있습니다.
- Garbage Collector에 의해 관리됩니다. (Garbage Collecton(이하 GC)이란 Heap Memory 내 각 영역에 객체가 가득찼을 때 사용하지 않는 객체들을 정리하는 과정을 의미)
![heap](/images/java_heap_memory_gc/Heap_Structure.png)

- Young Generation
	- 새로운 객체가 할당되는 곳 입니다. 이 영역에 객체가 가득차면 GC가 진행됩니다. 이것을 Minor GC라고 하며 빠른 속도로 진행이 됩니다. GC 후 남은 객체들은 Old Generation으로 이동됩니다.
	
- Old Generation
	- 오랫동안 남아있는 객체들을 저장하는데 사용됩니다. 일반적으로 새로 생성된 객체들에 대해 임계 값이 설정되고 해당 값에 도달하게 되면 객체는 Old Generation으로 옮기게 됩니다. 이 영역에서 객체가 가득차서 발생하는 Collection을 Major GC이라고 합니다. <br>

> ※ 모든 GC은 "Stop the World" 이벤트 입니다. 이것은 Collection이 완료될때까지 모든 어플리케이션 쓰레드가 멈춘다는 의미입니다. 빠른 속도로 진행되는 Minor GC에 비해 Major GC은 모든 객체를 포함하기 때문에 훨씬 느립니다. 그렇기 때문에 응답을 기다려야 하는 어플리케이션의 경우 Major GC를 최소화해야합니다. Old Generation의 Gaebage Collector 종류에 따라 진행시간이 다를 수 있습니다.

- Permanent Generation
	- 어플리케이션에 사용되는 종류 및 방법을 설명하는 JVM에 필요한 메타데이터를 저장합니다.
	- 사용중인 클래스를 기반으로 런타임시 JVM에서 채워줍니다.
	- JAVA SE 라이브러리 클래스, 메소드도 저장됩니다.<br>
> ※ JVM에서 현재 저장하고 있는 클래스가 더 이상 필요하지 않고 다른 클래스에 영역이 필요할 것으로 판단하면 기존 클래스가 Collection될 수 있습니다. Permanent Generation은 full GC(<a href ='https://dzone.com/articles/minor-gc-vs-major-gc-vs-full'>Young, Old 영역의 전체 Heap을 정리</a>)에 포함 됩니다.


### GC 과정 - JVM의 객체 할당 및 프로세스 설명<br>
1. 새로운 객체가 Young Generation 내 Eden 영역에 할당됩니다.<br>
![1_Object_Allocation](/images/java_heap_memory_gc/1_Object_Allocation.png)

2. Eden 영역이 객체로 채워지면 Minor GC가 시작됩니다.<br>
![2_Filling_the_Eden_Space](/images/java_heap_memory_gc/2_Filling_the_Eden_Space.png)

3. 참조된 객체는 첫번째 Survivor Space로 이동합니다. 참조되지 않는 객체들은 Eden 영역이 지워질 때 삭제됩니다.<br>
![3_Copying_Referenced_Object](/images/java_heap_memory_gc/3_Copying_Referenced_Object.png)

4. Eden 영역에서 같은 현상(Minor GC)이 발생합니다. 참조되지 않는 객체는 삭제되고 참고된 객체는 Survivor 영역으로 이동합니다. 그러나 이 과정에서는 두번째 Survivor 영역(S1)으로 이동합니다. 추가적으로 첫번째 영역(S0)에서의 마지막 Minor GC의 객체들은 aging되고 S1으로 이동합니다.<br>
정리 후 남은 모든 객체가 S1으로 이동하면 S0와 Eden이 모두 지워집니다.<br>
![4_Object_Aging](/images/java_heap_memory_gc/4_Object_Aging.png)

5. 다음은 동일한 프로세스가 반복됩니다. 하지만 이번에는 생존하는 객체들의 영역이 바뀝니다. 참조된 객체는 S0으로 이동됩니다. <br>
살아남은 객체들은 임계값이 상승(aging)되고 Eden 영역과 S1 내 객체들은 삭제됩니다.<br>
![5_Additional_Aging](/images/java_heap_memory_gc/5_Additional_Aging.png)

6. Minor GC 후 객체가 특정 임계값 (이 예시에서는 8) 도달하면 Young Generation 영역에서 Old Generation으로 이동합니다.<br>
![6_Promotion](/images/java_heap_memory_gc/6_Promotion.png)

7. Minor GC가 계속 발생하면 남는 객체들은 Old Generation으로 계속 이동합니다.<br>
![7_Promotion_2](/images/java_heap_memory_gc/7_Promotion_2.png)

8. 위 과정이 계속되면 결국 Old Generation 내 객체가 쌓이게 되고 결국 Major GC가 수행되며 Old Generation 영역을 정리하고 참조된 객체들을 순차적으로 할당(압축)합니다.<br>
![8_GC_Process_Summary](/images/java_heap_memory_gc/8_GC_Process_Summary.png)

참고 사이트
- Oracle : (<http://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html>)
- Dzone : (<https://dzone.com/articles/minor-gc-vs-major-gc-vs-full>)


[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
