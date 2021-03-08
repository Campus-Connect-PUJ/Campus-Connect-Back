
run: maven

maven:
	mvn spring-boot:run

docker:
	cd config && docker-compose up

load-sql-dump:
	./load_sql_dump.sh

inst:
	mvn install
