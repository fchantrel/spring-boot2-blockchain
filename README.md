# Spring Boot 2 – My BlockChain API.

# The BlockChain for developers
This is an example of how a blockchain works but only for purely educational purposes, for example you will discover the basics like :
blockchain
transaction
proof of work
consensus protocol

# Requirements
Java 10 and maven OR docker

# Launching with maven

```
> mvn spring-boot:run
```

# Launching with Docker

```
> docker build -t fchantrel/blockchain .
> docker run -p=8080:8080 fchantrel/blockchain
```

# Available operations
HealthCheck: http://localhost:8080/manage/health
Doc Swagger : http://localhost:8080/swagger-ui.html

## Reading the blockchain

```
curl --request GET --url http://localhost:8080/demoBlockChain/blockchain/
```

## Create a new transaction

```
curl --request POST \
  --url http://localhost:8080/demoBlockChain/transactions \
  --header 'content-type: application/json' \
  --data '{
	"sender":"d4ee26eee15148ee92c6cd394edd974e",
	"recipient":"toi",
	"amount":10
}'
```

## Mine a block

```
curl --request POST --url http://localhost:8080/demoBlockChain/blocks
```

## For more information
https://www.youtube.com/watch?v=519UIMIs5eI