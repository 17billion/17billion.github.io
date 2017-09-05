---
layout: post
title:  "ELK : Kibana로 데이터 시각화"
date:   2017-07-06 09:51:42 +0900
categories: elastic
tags:
- elastic
---
<a href ='/elastic/2017/07/05/ELK_install_exec.html'>지난 글</a>에서 ELK를 설치/실행을 진행했습니다. 이번에는 이어서 Logstash에서 설정한 부분을 토대로 서버의 cpu, memory, hdd 정보를 Kinaba를 통해 시각화 하는 방법을 알려드리겠습니다. <br>
※ 설치/실행 url : <a href ='/elastic/2017/07/05/ELK_install_exec.html'>'ELK 설치/실행 (Elastic Search + Kibana + Logstash)'</a>
 
#### Kibana 데이터 시각화 
(<https://www.elastic.co/guide/en/kibana/current/tutorial-visualizing.html>)
#### 1. Kibana 접속 후 index 설정
(원격으로 putty 등으로 접속할 경우 터널링 필요) <br>
<http://localhost:5601> 접속 <br>
지난글에 이어서 접속할 경우 처음에 웹페이지에 접속하면 아래와 같은 메세지가 노출됩니다. <br>
```
Unable to fetch mapping. Do you have indices matching the pattern?
```
지난 글에서 Logstash 설정으로 server-status-* 형태로 index를 생성해서 logstash-* 형태의 index를 못 찾기 때문입니다. <br>

1-1. 텍스트 박스 안에 logstash-\*를 server-status-\*로 변경한 후 create 버튼을 클릭하면 해당 index의 데이터를 불러와서 표시해줍니다. <br>
![1_input_index](/images/elk_kibana_chart/1_input_index.png)
1-2. 아래처럼 해당 Index의 모든 field 목록을 보여줍니다.
![2_create_index](/images/elk_kibana_chart/2_create_index.png)

#### 2. Visualization 생성 및 설정
데이터를 시각화 하기 위해서는 불러온 데이터를 어떤 방식으로 시각화할것인지 선택 및 설정을 해야합니다. <br>
2-1. Visualization 생성 
![4_create_visualize](/images/elk_kibana_chart/4_create_visualize.png)
2-1. Chart 종류 선택 (저는 Line으로 선택했습니다.)
![5_choice_chart](/images/elk_kibana_chart/5_choice_chart.png)
2-2. Filter 추가 (memory 예시)
![6_add_filter](/images/elk_kibana_chart/6_add_filter.png)
2-3. Y-Axis, X-Axis 설정 후 재생 버튼 클릭
![7_select_graph](/images/elk_kibana_chart/7_select_graph.png)
2-4. 데이터 시각화
![8_view_chart](/images/elk_kibana_chart/8_view_chart.png)
2-5. 해당 설정 저장
![9_save_type](/images/elk_kibana_chart/9_save_type.png)
※ 2-2 ~ 2-5 과정을 cpu, hdd도 반복하시면 됩니다.

#### 3. 저장된 Visualization을 Dashboard에 추가 (클릭)
3-1. Dashboard 생성 및 추가
![10_create_a_dashboard](/images/elk_kibana_chart/10_create_a_dashboard.png)
![11_add_a_dashboard](/images/elk_kibana_chart/11_add_a_dashboard.png)
3-2. 2에서 저장한 Visualization을 클릭하여 Dashboard에 추가
![12_select_metric](/images/elk_kibana_chart/12_select_metric.png)
3-3 Dashboard로 출력 완료
![13_view_chart](/images/elk_kibana_chart/13_view_chart.png)


참고 사이트 <br>
webkid 블로그 : <https://blog.webkid.io/visualize-datasets-with-elk/> <br>
Elastic : <https://www.elastic.co/guide/en/kibana/current/discover.html> <br>
Elastic : <https://www.elastic.co/guide/en/kibana/current/tutorial-visualizing.html> <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
