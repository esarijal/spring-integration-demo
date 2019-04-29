# spring-integration-demo
## Spring Integration Demo using Kafka

Demo using Spring Integration with Kafka

Only using DirectChannel P2P integration

- For running this need : 
  1. Kafka run at port `9092` and `9093` 
     Kafka host can be set using environment `SPRING_KAFKA_CONSUMER_BOOTSTRAP_SERVERS` and `SPRING_KAFKA_PRODUCER_BOOTSTRAP_SERVERS`
  2. Upload Server that run at host set by `UPLOAD_URL` or you can use https://github.com/esarijal/upload-download-rest

- Start the application mvn spring-boot:run
- send to test-topic topic from kafka producer https://raw.githubusercontent.com/ktarou/simple-r-web/master/test-data.csv
