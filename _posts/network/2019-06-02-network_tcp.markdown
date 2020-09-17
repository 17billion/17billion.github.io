---
layout: post
title:  "TCP (Transmission Control Protocol)"
date:   2019-06-02 20:20:12 +0900
categories: network
tags:
- network
---

### TCP (전송 제어 프로토콜, Transmission Control Protocol)
- 인터넷 프로토콜 (IP)의 핵심 프로토콜 중 하나로 IP와 함께 TCP/IP라는 명칭으로도 널리 불림. TCP는 근거리 통신망이나 인트라넷, 인터넷에 연결된 컴퓨터에서 실행되는 프로그램 간에 일련의 바이트를 안정적이고 순서대로 에러없이 교환할 수 있게 함.
- 전송 계층에 위치하며 네트워크의 정보 전달을 통제하는 프로토콜이자 인터넷을 이루는 핵심 프로토콜의 하나
- TCP의 안정성을 필요로 하지 않는 애플리케이션의 경우 일반적으로 TCP 대신 비접속형 사용자 데이터그램 프로토콜(User Datagram Protocol)을 사용. UDP는 전달 확인 및 순차 보장 기능이 없는 대신 오버헤드가 작고 지연시간이 짧다는 장점이 있음.

### TCP 세그먼트 구조 (<a href ='https://ko.wikipedia.org/wiki/%EC%A0%84%EC%86%A1_%EC%A0%9C%EC%96%B4_%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C'>위키 참고</a>)
- 데이터 스트림으로부터 데이터를 받아 들여 이것을 청크 단위로 분할한 뒤 TCP 헤더를 덧붙여 TCP 세그먼트를 생성. TCP 세그먼트는 IP 데이터그램에 캡슐화되어 상대방과 주고 받게 됨.
- TCP 패킷이라는 용어가 종종 사용되지만 이는 정확한 표현이 아님. 세그먼트가 TCP프로토콜 데이터 유닛(PDU)을 의미하는 정확한 표현이며 데이터그램은 IP PDU를, 프레임은 데이터 링크 계층 PDU를 의미함. 
![tcp header](/images/network_tcp/tcp_header.png)

### TCP 연결 설정 (3 Way-Handshake)
- 연결을 설정하기 위해 TCP는 3 Way-Handshake를 사용. 클라이언트가 서버와 연결을 시도하기 전에 서버는 먼저 포트를 바인드하고 청취하여 연결을 위해 포트를 열어야함(수동 열기). 수동 열기가 설정되면 클라이언트는 활성 열기를 시작할 수 있고 연결을 설정하기 위해 3 Way-Handshake가 발생
- 1) SYN : 활성 열기는 클라이언트가 서버에 SYN을 전송하여 수행. 클라이언트는 세그먼트의 시퀀스 번호를 임의의 값 A로 설정.
- 2) SYN-ACK : 이에 대한 응답으로 서버는 SYN-ACK로 응답. 수신 확인 번호는 수신 된 시퀀스 번호, 즉 A + 1보다 하나 이상으로 설정되고, 서버가 패킷에 대해 선택하는 시퀀스 번호는 다른 임의의 번호 B로 설정.
- 3) ACK : 마지막으로 클라이언트는 서버로 ACK를 다시 보냄. 시퀀스 번호는 수신 된 확인 응답 값, 즉 A + 1로 설정되고, 확인 응답 번호는 수신 된 시퀀스 번호, 즉 B + 1보다 하나 이상으로 설정됨.
- 위 시점에서 클라이언트와 서버 모두 연결에 대한 승인을 받게됨. 단계 1, 2는 한 방향에 대한 연결 매개 변수 (시퀀스 번호)를 설정하고 승인됨. 단계 2, 3은 다른 방향에 대한 연결 파라미터 (시퀀스 번호)를 설정하고 인정. 이것에 의해, 전이중 통신이 확립됨.
- link
  - https://mindnet.tistory.com/entry/네트워크-쉽게-이해하기-22편-TCP-3-WayHandshake-4-WayHandshake <br />
  - https://www.geeksforgeeks.org/tcp-3-way-handshake-process/

### TCP 연결 종료 (4 Way-Handshake)
- 연결 종료 단계는 4 Way-Handshake를 사용하며 연결의 각면이 독립적으로 종료. 끝 점이 연결의 절반을 멈추고 자 할 때 FIN 패킷을 전송하고 다른 쪽 끝은 ACK로 확인. 따라서 일반적인 분류에는 각 TCP 끝점에서 한 쌍의 FIN 및 ACK 세그먼트가 필요. 첫 번째 FIN을 보낸 측이 최종 ACK로 응답 한 후, 마지막 연결을 종료하기 전에 시간 종료를 기다린 후 로컬 포트를 새 연결에 사용할 수 없음.?이렇게하면 후속 연결 중에 지연된 패킷이 전달되어 혼동을 방지 할 수 있음.
- 연결은 "half-open" 일 수 있으며 이 경우 한쪽은 끝을 종료하지만 다른 쪽은 종료하지 않음. 종료 된 쪽은 더 이상 데이터를 연결로 보낼 수 없지만 다른 쪽은 보낼 수 있음. 종단 측은 다른 쪽도 종료 될 때까지 데이터를 계속 읽음.
- 호스트 A가 FIN을 보내고 호스트 B가 FIN & ACK로 응답하고 (단순히 2 단계를 하나로 묶음) 호스트 A가 ACK로 응답하면 3 Way-Handshake로 연결을 종료 할 수도 있음.
- Close Wait 종료
	- 커널 옵션으로 타임아웃 조절이 가능한 TIME_WAIT, FIN_WAIT과 달리 CLOSE_WAIT는 포트를 잡고 있는 프로세스의 종료 또는 네트워크 재시작 외에는 제거할 방법이 없음. 즉, 로컬 어플리케이션이 정상적으로 close()를 요청하는 것이 가장 좋은 방법. (http://docs.likejazz.com/close-wait/)
	- Close Wait 상태가 해결되는 것은 신호를 보내거나 네트워크 세션이 끊기는 방법 밖에 없음. 아니면 계속 그 상태로 유지됨 (<a href ='https://en.wikipedia.org/wiki/Transmission_Control_Protocol#/media/File:TCP_CLOSE.svg'>그림</a>)
	
	
참고 사이트 <br />
- <a href ='https://ko.wikipedia.org/wiki/%EC%A0%84%EC%86%A1_%EC%A0%9C%EC%96%B4_%ED%94%84%EB%A1%9C%ED%86%A0%EC%BD%9C'>wiki</a> <br />
- sungchan41 님 블로그 : https://mindnet.tistory.com/
- likejazz 님 블로그 : http://docs.likejazz.com/
- geeksforgeeks : https://www.geeksforgeeks.org/