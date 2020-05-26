@echo off

mvnw clean package && docker build . && docker-compose up customer-account-aggregation

exit