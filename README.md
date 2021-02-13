# Architecture_Study

## 환경구성

### 연습 모델링 설계도

![erd](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbXSdTF%2FbtqWWtYW8Ee%2FdQqEkjWJJmzqfZykLvaZaK%2Ftfile.svg)

https://parkprin.tistory.com/manage/posts

### Database

#### Docker 인스턴스 생성
```
docker run -d \
-p 2012:3306 \
--name Architecture_Study-db \
-e MYSQL_ROOT_PASSWORD=루트 비밀번호 \
-e MYSQL_DATABASE=Architecture_Study \
-e MYSQL_USER=as_admin \
-e MYSQL_PASSWORD=cmp_admin의 비밀번호 \
mariadb:latest
```

데이터베이스 IDE을 이용하여 root 계정으로 접속한 후 데이터베이스의 환경을 변경한다
1) 타임존 설정
```
$ SET GLOBAL time_zone ='Asia/Seoul';
$ SET time_zone ='Asia/Seoul';
$ select @@global.time_zone, @@session.time_zone;
```

2) Character Set UTF-8설정


```
root 권한으로 들어가서 아래 명령어를 클릭한다
$ show variables like 'c%'
```

그럼 매칭되어 있는 값중에 latin으로 되어있는 값을 utf8로 변환하여야 한다.
그런데 Docker image에서는 utf8로 설정을 해놓아서 딱히 작업할 내용이 없었다.

3) schema-mysql.sql 실행
아래 쿼리를 실행하여 오류를 방지한다.
```
CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BLOB NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

```

