---
layout: post
title:  "OSI 7 계층"
date:   2019-06-13 21:28:12 +0900
categories: network
tags:
- network
---

### OSI 7계층
http://blog.naver.com/PostView.nhn?blogId=demonicws&logNo=40117378644
- OSI란 네트워크 통신의 개발 시스템 상호 연결(Open Systems Interconnection)을 의미
- 같은 회사의 제품에서만 연결이 가능했던 과거에서 벗어나 제품이나 회사상관없이 연결이 가능하도록 ISO(국제 표준화 기구)에서 표준화 제시
- 데이터를 전송할 때 각각의 층마다 인식할수 있어야 하는 헤더를 붙이게 되는데 이러한 과정을 캡슐화라고 함 (반대는 디캡슐화)
- 출발지에서 데이터가 전송될 때 헤더가 씌워질 때 캡슐화가 되고 2계층에서민 오류제어를 위헤 테일(꼬리부위) 부분이 씌워짐 

![OSI 7 LAYER](/images/network_osi/osi_7_layer.png)

|계층 | Layer | 단위 / 프로토콜|
|:-------------:|:----------:|:----------:|
7계층 | 응용 | 서비스 /FTP, HTTP
6계층 | 표현 | 확장자 / JPEG, MPEG
5계층 | 세션 | 포트 / SSH
4계층 | 전송 | 세그먼트 / TCP
3계층 | 네트워크 | 패킷 / IP
2계층 | 데이터 |프레임 / MAC, PPP
1계층 | 물리 | 비트 / 이더넷
	
참고 사이트 <br />
- TIM 님 블로그 : http://blog.naver.com/PostView.nhn?blogId=demonicws&logNo=40117378644
