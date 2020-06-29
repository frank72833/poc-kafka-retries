# poc-kafka-retries
POC to understand kafka retries

## Initialize container
For this, make sure you have installed and running docker and java 11.

- Start: ``` make start ``` will compile the service and generate a docker image. It will also run docker-compose
to run the zookeeper and kafka containers alongside the application.

Note: If order to run Kafka you need to provide your local IP (run ```ifconfig``` in the terminal) and update ```KAFKA_ADVERTISED_HOST_NAME```.

## API
The application exposes an API endpoint to send messages.
```@POST http://localhost:8080/api/v1/messages```
with the following body schema:
```
{
	"error": false,
	"body": {
		"description":"123rfw"
	}
}
```

If error is `false`, the listener will throw an error when processing the message otherwise, it will process it successfully.
The description can be any string to identify the logs and help you debug the behaviour.

## Kafka settings
There is an application.yml file in resources which contains all the setup settings for kafka consumer.