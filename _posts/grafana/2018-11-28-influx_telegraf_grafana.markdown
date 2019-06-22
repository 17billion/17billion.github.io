---
layout: post
title:  "InfluxDB(1.6.3), Telegraf(1.7.4), Grafana(5.2.4)를 이용한 서버 모니터링 하기"
date:   2018-11-28 02:21:42 +0900
categories: grafana
tags:
- grafana
---
### 구성<br>
 ![0](/images/grafana/0.png)

#### InfluxDB 설치 및 실행 (1.6.3)
> 1. $ wget https://dl.influxdata.com/influxdb/releases/influxdb_1.6.3_amd64.deb <br>
2. $ sudo dpkg -i influxdb_1.6.3_amd64.deb <br>
3. $ curl -sL https://repos.influxdata.com/influxdb.key \| sudo apt-key add - <br>
4. $ source /etc/lsb-release <br>
5. $ echo "deb https://repos.influxdata.com/${DISTRIB_ID,,} ${DISTRIB_CODENAME} stable" \| sudo tee /etc/apt/sources.list.d/influxdb.list <br>
6. (실행) $ sudo service influxdb start <br>
7. (확인) $ influx <br>
Connected to http://localhost:8086 version 1.6.3 <br>
InfluxDB shell version: 1.6.3 <br>
\ >

#### telegraf(1.7.4) 설치 및 실행
> 1. $ wget https://dl.influxdata.com/telegraf/releases/telegraf_1.7.4-1_amd64.deb <br>
2. $ sudo dpkg -i telegraf_1.7.4-1_amd64.deb <br>
3. (확인) $ ps -ef \| grep telegraf <br>
4. (InfluxDB 설정) $ sudo vi /etc/telegraf/telegraf.conf <br>
```
[[outputs.influxdb]]
  urls = ["http://{$influxDB ip 넣기}:8086"]
```
5. (재시작) $ sudo service telegraf restart

#### Grafana 설치 및 실행
> 1.  $ wget https://dl.influxdata.com/telegraf/releases/telegraf_1.7.4_amd64.deb <br>
2. $ sudo apt-get install -y adduser libfontconfig <br>
3. $ sudo dpkg -i grafana_5.2.4_amd64.deb <br>
4. $ sudo service grafana-server status <br>
5. 접속 : http://{grafana ip}:3000/login 접속 (환경설정 : /etc/grafana/grafana.ini) <br>
- 계정 : admin / admin
![1_login](/images/grafana/1 login.png)

```
※ Grafana 설치 진행 중 아래처럼 오류가 날경우 $ sudo apt-get -f install 실행 필요
Unpacking grafana (5.2.4) over (5.2.4) ...
dpkg: dependency problems prevent configuration of grafana:
 grafana 패키지는 다음 패키지에 의존: libfontconfig: 하지만:
  libfontconfig 패키지는 설치하지 않았습니다.

dpkg: error processing package grafana (--install):
 의존성 문제 - 설정하지 않고 남겨둠
Processing triggers for systemd (229-4ubuntu21.16) ...
Processing triggers for ureadahead (0.100.0-19) ...
처리하는데 오류가 발생했습니다:
 grafana
```

#### Grafana Dashboard 만들기

> 1. Datasource 생성 메뉴 선택<br>
![2 select_datasource](/images/grafana/2 select_datasource.png)
2. Add 버튼 클릭<br>
![3 add_datasource](/images/grafana/3 add_datasource.png)
3. 설정 후 Save & Test 클릭 <br>
![4 setting_datasource](/images/grafana/4 setting_datasource.png)
4. Telegraf Dashboard Import<br>
![5 import_dashboard](/images/grafana/5 import_dashboard.png)
![6 import_dashboard_2](/images/grafana/6 import_dashboard_2.png)
 5. Dashboard<br>
 ![7 dashboard_2](/images/grafana/7 dashboard.png)
 
참고 사이트 <br>
YENA WORLD : https://yenaworldblog.wordpress.com/2017/07/28/influxdb/ <br>
grafana : https://grafana.com/grafana/download/5.2.4?platform=linux <br>

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
