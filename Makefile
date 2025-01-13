.PHONY: build run-dist report

build:
	cd app && ./gradlew build

run-dist: build
	./app/build/install/app/bin/app

report:
	cd app && ./gradlew jacocoTestReport