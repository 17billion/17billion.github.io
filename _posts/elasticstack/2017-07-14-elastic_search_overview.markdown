---
layout: post
title:  "Elastic Search란?"
date:   2017-07-14 09:51:42 +0900
categories: elastic
tags:
- elastic
---
 
### 1. 특징 <br>
1) Apache Lucene 기반 (Full Text 검색엔진을 만들 수 있는 라이브러리를 제공, 자바언어로 개발, 사용자위치정보, 다국어 검색지원, 자동완성 지원, 미리보기 지원, 철자수정 기능 지원 / 대부분 Elasticsearch에서도 지원) <br>

2) 분산시스템 <br>
\- 여러개의 Node로 구성되어 있는 분산시스템 <br>
\- Node는 데이터를 색인하고 검색을 수행하는 단위 프로세스 <br>
\- 데이터는 각 데이터 Node에 분산저장 <br>
\- Replica를 유지하여 데이터 보호 <br>

3) REST API를 통해 데이터 처리 <br>

4) <a href ='https://www.slideshare.net/seunghyuneom/elastic-search-52724188#38'>RDBMS와 Elastic Search 구조 비교</a> (seunghyuneom님의 SliceShare) <br>

|   RDBMS  | ElasticSearch |
|:--------:|:-------------:|
| Database |     Index     |
|   Table  |      Type     |
|    Row   |    Document   |
|  Column  |     Field     |
|  Scheme  |    Mapping    |

### 2. 용어 <br>
1) Cluster : ElasticSearch의 가장 큰 시스템 단위 <br>
\- 하나의 Cluster는 동일한 cluster.name 내 여러개 Node로 구성되어 있음 <br>
\- 같은 Cluster 이름으로 노드를 실행 시키면 자동확장 (네트워크 바인딩) <br>

2) Node : 실행중인 ElasticSearch Instance <br>
\- Master Node : Cluster 메타 정보 관리, Node 추가 삭제 <br>
\- Data Node : 실제 데이터를 저장하는 Node <br>

3) Index : 관련성이 있는 데이터의 저장 위치 <br>
\- 물리적인 Shard에 대한 논리적인 이름 공간 <br>

4) Shard : Index의 데이터를 저장하여 Indexing된 Document가 실제 저장되는 곳 <br>
\- Primary Shard : 모든 Document는 단 하나의 Primary Shard에 위치, 갯수는 Index를 생성할 때 결정되고 변경될 수 있음(default) <br>
\- Replicat Shard : Primary Shard의 복사본 <br>
\- 장애발생 시 복구, 검색에 대한 Concurrnt Read 보장 <br>

5) 네트워크 바인딩 : 다른 서버의 노드와 바인딩 (자유롭게 Scale-in/out 가능) <br>

### 3. Rest API 예제 <br>

|  CRUD  | Elastic Search |
|:------:|:--------------:|
| Create |      Post      |
|  Read  |       Get      |
| Update |       Put      |
| Delete |     Delete     |

##### - curl -X {method} http://host:port/{Index}/{Type}/{Id} <br>
1) POST (생성)
```bash
curl -XPOST http://localhost:9200/custom/test/1 -d '
{
  "title": "Elasticsearch Test",
  "name": "Lee",
  "date": "2017-07-10"
}'
```

- Response
> {"_index":"custom","_type":"test","_id":"1","_version":1,"result":"created","_shards":{"total":2,"successful":1,"failed":0},"created":true}

2) PUT (업데이트)
```bash
curl -XPUT http://localhost:9200/custom/test/1 -d '
{
  "title": "Elasticsearch Test Update",
  "name": ["Lee", "Cho"],
  "date": "2017-07-11"
}'
```

- Response
> {"_index":"custom","_type":"test","_id":"1","_version":2,"result":"updated","_shards":{"total":2,"successful":1,"failed":0},"created":false}

3) GET (조회 / pretty 옵션은 보기좋게 출력하고 싶을때 사용)
```bash
curl -XGET http://localhost:9200/custom/test/1?pretty=true
```

- Response
> { <br>
  "_index" : "custom", <br>
  "_type" : "test", <br>
  "_id" : "1", <br>
  "_version" : 2, <br>
  "found" : true, <br>
  "_source" : { <br>
    "title" : "Elasticsearch Test Update", <br>
    "name" : [ <br>
      "Lee", <br>
      "Cho" <br>
    ], <br>
    "date" : "2017-07-11" <br>
  } <br>
} <br>

4) DELETE (삭제)
```bash
curl -XDELETE http://localhost:9200/custom/test/1
```

- Response
> {"found":true,"_index":"custom","_type":"test","_id":"1","_version":3,"result":"deleted","_shards":{"total":2,"successful":1,"failed":0}}

### 기타. <br>
1) Elasric Search의 동작방식 (NAVER D2) <br>
\- <a href ='https://www.slideshare.net/deview/2d1elasticsearch#12'>Creating, indexing and deleting a document</a> <br>
\- <a href ='https://www.slideshare.net/deview/2d1elasticsearch#13'>Retrieve, query and fetch a document</a> <br>

2) 설치방법  <br>
\- <a href ='/elastic/2017/07/05/ELK_install_exec.html'>'ELK 설치/실행 (Elastic Search + Kibana + Logstash)' : '1. ElasticSearch를 설치합니다. (ubuntu 기준) 참고'</a> <br>

참고 사이트 <br>
seunghyuneom님의 SliceShare : <https://www.slideshare.net/seunghyuneom/elastic-search-52724188> <br>
NAVER D2 SliceShare : <https://www.slideshare.net/deview/2d1elasticsearch> 

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
