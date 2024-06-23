## Mysql 환경 구축

- mysql 설치
```
sudo apt-get update && sudo apt-get upgrade
sudo apt-get install mysql-server mysql-client
sudo ufw allow mysql
```
- 루트 유저 설정
```
# mysql client 수행하여 접속
sudo mysql
# mysql root 패스워드 변경
create user 'root'@'%' identified with mysql_native_password by 'root';
grant all privileges on *.* to 'root'@'%' with grant option;
flush privileges;
```
- 데이터 베이스 생성
```bash
create database siem;
show databases;
```
- debezium 용 connect 유저 설정
```bash
create user 'connect_dev'@'%' identified by 'connect_dev';
grant all privileges on siem.* to 'connect_dev'@'%' with grant option;
grant SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'connect_dev'@'%' with grant option;

flush privileges;
```

- 테이블 생성 
```bash
use siem;

CREATE TABLE log_data (
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
src_ip varchar(100) NOT NULL,
dst_ip varchar(100) NOT NULL,
port int NOT NULL,
action varchar(10) NOT NULL,
protocol varchar(50) NOT NULL,
timestamp timestamp NOT NULL
) ENGINE=InnoDB ;
```
- 외부 연결 설정 및 타임존 설정
```
sudo vi /etc/mysql/mysql.conf.d/mysqld.cnf
bind-address = 0.0.0.0
# 아래 설정을 mysqld.cnf에 추가
default_time_zone = '+09:00'

sudo systemctl restart mysql
```


## Debezium Connector 등록

```
{
    "name": "cdc_test",
    "config": {
        "connector.class": "io.debezium.connector.mysql.MySqlConnector",
        "tasks.max": "1",
        "database.hostname": "192.168.56.101",
        "database.port": "3306",
        "database.user": "connect_dev",
        "database.password": "connect_dev",
        "database.server.id": "10000",
        "topic.prefix": "firewall",
        "database.include.list": "siem",
        "database.allowPublicKeyRetrieval": "true",
        "schema.history.internal.kafka.bootstrap.servers": "192.168.56.101:9092",
        "schema.history.internal.kafka.topic": "schema-changes-siem",
        "key.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",
        "database.connectionTimeZone": "Asia/Seoul"
    }
}
```
- mysql 서버에서 default time zone 설정 없이 수행 시 database.connectionTimeZone config 설정 반드시 추가 필요
- "schema.history.internal.kafka.topic" : 스키마 레지스트리가 사용하는 스키마 저장용 내부 토픽, 없으면 오류 발생
