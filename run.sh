#!/bin/bash

./mvnw clean package

docker build .

docker-compose up customer-account-aggregation