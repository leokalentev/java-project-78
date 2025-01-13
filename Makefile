.PHONY: setup build run-dist report

setup:
	cd app && ./gradlew clean build
