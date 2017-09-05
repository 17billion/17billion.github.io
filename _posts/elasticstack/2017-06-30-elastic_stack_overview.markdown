---
layout: post
title:  "Elastic Stack 이란?"
date:   2017-06-30 09:51:42 +0900
categories: elastic
tags:
- elastic
---

![elastic_stack](/images/elastic_stack_overview/elastic_stack.png){: .center-image }

### Elastic Stack이란
##### 사용자가 서버로부터 원하는 모든 유형의 데이터를 가져와서 실시간으로 해당 데이터를 검색, 분석 및 시각화 할 수 있도록 도와주는 Elastic의 오픈소스 서비스 제품입니다.
##### 기존 Elastic Search + Logstash + Kibana를 같이 묶어 ELK(ELK Stack)라는 서비스명으로 제공했으나 5.0.0 버전부터 Beats가 포함되어 <a href ='https://www.elastic.co/kr/blog/heya-elastic-stack-and-x-pack'>Elastic Stack</a>이란 이름으로 현재 서비스가 제공되고 있습니다.
##### 추가적으로 확장기능인 X-Pack, Elastic Cloud 서비스도 제공하여 Enterprise 서비스로 확대하고 있습니다. <br>
##### 현재 비슷한 기능을 제공하는 influxDB + grafana 서비스가 있으며 어느 서비스가 더 퍼포먼스가 좋은지는 여전히 토론중입니다. (<a href ='https://discuss.elastic.co/t/elk-vs-grafana-influxdb/1686'>ELK vs grafana + influxDB</a>, <a href ='https://logz.io/blog/grafana-vs-kibana/'>Grafana vs Kibana</a>)<br>
##### 아래 그림은 Elastic Stack의 Flow입니다. 
![elastic_stack_2](/images/elastic_stack_overview/elastic_stack_2.png){: .center-image } <br>



### Elastic Search <br>
##### 루씬 기반의 Full Text로 검색이 가능한 오픈소스 분석엔진입니다. 주로 REST API를 이용해 처리합니다.
##### 대량의 데이터를 신속하고 거의 실시간으로 저장, 검색 및 분석 할 수 있습니다. 

### Logstash <br>
##### 다양한 플러그인을 이용하여 데이터 집계 및 보관, 서버 데이터 처리. 파이프라인으로 데이터를 수집하여 필터를 통해 변환 후 Elastic Search로 전송합니다.
##### - 입력 : Beats, Cloudwatch, Eventlog 등의 다양한 입력을 지원하여 데이터 수집
##### - 필터 : 형식이나 복잡성에 상관없이 설정을 통해 데이터를 동적으로 변환
##### - 출력 : Elastic Search, Email, ECS, Kafka 등 원하는 저장소에 데이터를 전송

### Kibana <br>
##### 데이터를 시각화해주는 도구입니다.

### Beats <br>
##### 경량 에이전트로 설치되어 데이터를 Logstash 또는 Elastic Search로 전송하는 도구입니다. Logstash보다 경량화되어 있는 서비스입니다. <br>
##### Filebeat, Metricbeat, Packetbeat, Winlogbeat, Heartbeat 등이 있으며 Libbeat을 이용하여 직접 구축도 가능합니다. <br>
##### - Packetbeat은 응용 프로그램 서버간에 교환되는 트랜잭션에 대한 정보를 제공하는 네트워크 패킷 분석기
##### - Filebeat는 서버에서 로그 파일을 제공
##### - Metricbeat은 서버에서 실행중인 운영 체제 및 서비스에서 메트릭을 주기적으로 수집하는 서버 모니터링 에이전트
##### - Winlogbeat는 Windows 이벤트 로그를 제공<br><br>


### X-Pack - Elastic Stack의 확장 Pack <br>
##### Elastic Stack에 확장기능을 제공합니다. (Sucurity, Monitoring, Alerting, Reporting, Graph, Machine Learning)

![x_pack](/images/elastic_stack_overview/x_pack.png)
##### (Basic으로 제공하는 Monitoring을 제외하고 나머지는 유료로 제공됩니다.)
##### - Sucurity(이전 Shield) : 인증기능, 사용자 관리, 노드 / HTTP / Elastic Search 클라이언트 간 통신 트래픽 보호, Field / Document 수준까지 데이터 보호 / Audit Log (감시로그) 제공
##### - Alerting : 데이터 변경사항에 대한 알림 제공
##### - Monitoring(이전 Marvel) : ELK Stack 상태를 지속적으로 체크
##### - Reporting : PDF 형식의 주기적인 보고서를 생성, Email 등으로 전송
##### - Graph : 데이터 시각화
##### - Alerting : 데이터 변경사항에 대한 알림 제공- Machine Learning : 데이터의 흐름, 주기성 등을 자동으로 실시간 모니터링을 하여 문제를 식별하고 근본원인 분석

### Elastic Cloud <br>
##### 최신 버전의 Elasticsearch 및 Kibana를 사용하여 Amazon AWS에서 실행되는 보안 클러스터를 배포하고 관리합니다.
##### 아래는 Elastic Solution Architecter(christoph-wurm)가 설명하는 Simple부터 Enterprise까지 서비스 구축 예시입니다. <a href ='https://www.slideshare.net/DevopsCon/using-elastic-to-monitor-everything-christoph-wurm-elastic-devopsdays-tel-aviv-2016#14'>(링크)</a>
![1_developing_elastic_stack_simple](/images/elastic_stack_overview/1_developing_elastic_stack_simple.png){: .center-image }
![2_developing_elastic_stack_additional_processing](/images/elastic_stack_overview/2_developing_elastic_stack_additional_processing.png){: .center-image }
![3_developing_elastic_stack_deployment_at_scale](/images/elastic_stack_overview/3_developing_elastic_stack_deployment_at_scale.png){: .center-image }
![3_developing_elastic_stack_deployment_in_the_enterprise](/images/elastic_stack_overview/3_developing_elastic_stack_deployment_in_the_enterprise.png){: .center-image }

참고 사이트 <br>
christoph-wurm(Elastic Solution Architecter) : <https://www.slideshare.net/DevopsCon/using-elastic-to-monitor-everything-christoph-wurm-elastic-devopsdays-tel-aviv-2016> <br>
Elastic : <https://www.elastic.co/guide/en/elastic-stack/current/elastic-stack.html> <br>
Elastic : <https://www.elastic.co/guide/en/beats/libbeat/5.5/beats-reference.html> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
