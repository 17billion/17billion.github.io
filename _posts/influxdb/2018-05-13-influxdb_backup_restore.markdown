---
layout: post
title:  "Backing up and restoring in InfluxDB"
date:   2018-05-13 19:11:42 +0900
categories: influxdb
tags:
- influxdb
---

#### InfluxDB Backup / Restore 방법
1. Backup
```
influxd backup
    [ -database <db_name> ]
    [ -portable ]
    [ -host <host:port> ]
    [ -retention <rp_name> ] | [ -shard <shard_ID> -retention <rp_name> ]
    [ -start <timestamp> [ -end <timestamp> ] | -since <timestamp> ]
    <path-to-backup>
```
- 전체 백업
$ influxd backup -portable <path-to-backup>
- 최근 변경된 모든 데이터베이스를 백업
$ influxd backup -portable -start <timestamp> <path-to-backup>
- 특정 데이터베이스만 백업
$ influxd backup -portable -database telegraf <path-to-backup>
- 지정된 기간 동안의 데이터베이스 배업
$ influxd backup  -portable -database mytsd -start 2017-04-28T06:49:00Z -end 2017-04-28T06:50:00Z /tmp/backup/influxdb

2. Restore
```
influxd restore [ -db <db_name> ]
    -portable | -online
    [ -host <host:port> ]
    [ -newdb <newdb_name> ]
    [ -rp <rp_name> ]
    [ -newrp <newrp_name> ]
    [ -shard <shard_ID> ]
    <path-to-backup-files>
```
- 백업 디렉토리에있는 모든 데이터베이스를 복원
$ influxd restore -portable path-to-backup
- 특정 데이터베이스(telegraf)만 복원하려면 (해당 데이터베이스가 없어야 함)
$ influxd restore -portable -db telegraf path-to-backup

- 이미 존재하는 데이터베이스로 데이터를 복원할 경우
1. 데이터베이스를 임시 데이터베이스로 복원
$ influxd restore -portable -db telegraf -newdb telegraf_bak path-to-backup
2. Sideload를 대상 데이터베이스에 추가하고 임시 데이터베이스 삭제
$ use telegraf_bak
$ SELECT * INTO telegraf..:MEASUREMENT FROM /.*/ GROUP BY *
4 drop database telegraf_bak

참고 사이트
- InfluxData : (<https://docs.influxdata.com/influxdb/v1.5/administration/backup_and_restore/>)

[Jekyll-docs]: https://Jekyllrb.com/docs/home
[Jekyll-gh]:   https://github.com/Jekyll/Jekyll
[Jekyll-talk]: https://talk.Jekyllrb.com/
