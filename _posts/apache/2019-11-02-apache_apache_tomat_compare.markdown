---
layout: post
title:  "Apache, Tomcat 차이점"
date:   2019-11-02 21:28:42 +0900
categories: apache
tags:
- apaches
- tomcat
---

### Apache, Tomcat 차이
- 웹서버와 웹애플리케이션의 차이 
- WAS(Tomcat)만 쓰지 않고 Apache를 쓰는 이유는 목적이 다르기 때문 (웹 컨테이너의 유무) 
- 웹서버는 정적인 데이터를 처리하는 서버이며 이미지나 단순 HTML을 처리하는 서버라면 웹 서버가 적당하며 빠르고 안정적
- WAS는 동적인 데이터를 처리하는 서버, DB연결 / 데이터 조작등과 같은 처리는 WAS를 활용해야 함
- Apache(80 포트) , Tomcat(8080 포트)
- Tomcat은 5.5부터 Httpd의 native모듈을 사용해서 Static 파일을 처리하는 기능을 제공.
  - 단지 Static 파일 처리의 성능만을 위해서라면 굳이 Tomcat 앞에 Apache Httpd를 두는 것은 불필요하며 메모리만 추가로 소요되고 불필요한 부하만 생김
- Tomcat 앞에 Apache Httpd을 사용하는 경우
  - 하나의 서버에서 PHP애플리케이션과 자바 애플리케이션을 함께 사용하거나, Httpd 서버로 간단한 로드밸런싱을 위해서 사용해야 하는 경우

참고 사이트 <br />
dongdong_님 블로그 : https://limmmee.tistory.com/4