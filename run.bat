@echo off

mvnw clean install && docker build . && docker-compose up customer-account-aggregation

exit