---
layout: post
title:  "Time Series Analysis - ACF, PACF, AR, MA"
date:   2020-04-09 21:28:12 +0900
categories: analysis
tags:
- analysis
- timeseries
- anomaly detection
---

#### ACF(Autocorrelation Function / 자기상관함수), PACF(Partial Autocorrelation Function / 부분자기상관함수)
- 어느 시점까지의 데이터가 현재 시점에 영향을 주는지 연산하는 함수
- AR, MA 모델 적합성을 설정할때 필요한 함수 (절단값으로 설정됨)
- 절단값 : 아래 예시 그림 중 값이 파란색 부분으로 들어오기 직전의 값
	- 어느 시점에 절단값을 가진다는 것은 0으로 수렴한다는 뜻으로 이해하면 됨
- ex) ![ACF_PACF](/images/analysis_acf_pacf_ar_ma/acf_pacf.png)
- 인플럭스디비를 이용한 ACF 실습 (https://www.influxdata.com/blog/autocorrelation-in-time-series-data/)
  - 주피터 파일 : https://github.com/Anaisdg/autocorrelation/blob/master/Autocorrelation.ipynb

#### AR (Autoregression / 자기회귀)
- 이전 관측값의 오차항이 이후 관측값에 영향을 주는 모형
	- P 시점 전의 값이 현재 값에 영향을 줌
- ex) ar(1)은 1시점 전에 의해 현재시점이 영향을 받는 모형, ar(2)는 2시점 전까지에 의해 현재 시점에 영향을 주는 모형

#### MA(Moving Average / 이동평균 모형)
- 관측값이 이전의 연속적인 오차항의 영향을 받는다는 모형
	- 데이터의 평균값 자체가 시간에 따라 변화하는 경향을 시계열 모형으로 구성 한 것
- ex) ACF는 3시점 이후 절단점을 보이고 PACF가 빠르게 감소하면 MA(2) 모형이라고 볼 수 있음

#### AR/MA 모형과 ACF/PACF 의 관계
- 요약 관계표
  - ![summary](/images/analysis_acf_pacf_ar_ma/summary.png)

참고 사이트 <br />
- NASTY 님 블로그 : http://blog.naver.com/PostView.nhn?blogId=demonicws&logNo=40117378644
- Influxdata : https://www.influxdata.com/blog/autocorrelation-in-time-series-data/

