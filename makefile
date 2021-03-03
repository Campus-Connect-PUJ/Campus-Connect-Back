
run: maven

maven:
	mvn spring-boot:run

docker:
	cd config && docker-compose up

SQLDUMP='./scripts/init.sql'
json2sql:
	python ./scripts/jsonAsql.py ./scripts/gruposEstudiantiles.json > $(SQLDUMP)

DOCKERCONTAINER='ccb_postgres'
DOCKERUSER='postgres'
DOCKERDATABASE='postgres'
load-sql-dump: json2sql
	cat $(SQLDUMP) | docker exec -i $(DOCKERCONTAINER) psql -U $(DOCKERUSER) -d $(DOCKERDATABASE)


inst:
	mvn install
