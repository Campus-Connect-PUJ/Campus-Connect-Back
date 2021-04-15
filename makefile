
run: maven

maven:
	mvn spring-boot:run

docker:
	cd config && docker-compose up

load:
	python3 ./scripts/cargarJson.py ./scripts/informacion.json

inst:
	mvn install
