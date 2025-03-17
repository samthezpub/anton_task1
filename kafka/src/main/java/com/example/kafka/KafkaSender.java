package com.example.kafka;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaSender {
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final Map<String, CompletableFuture<String>> pendingRequests = new ConcurrentHashMap<>();
  public void sendLog(String message) {
    kafkaTemplate.send("logging", "generalApp", message);
  }

  public CompletableFuture<String> sendFindUserCat(String username) {
    String correlationId = UUID.randomUUID().toString();
    CompletableFuture<String> future = new CompletableFuture<>();
    pendingRequests.put(correlationId, future);
    kafkaTemplate.send("findUserCat", correlationId, username);

    return future;
  }

  @KafkaListener(topics = "findUserCat-response", groupId = "my-group")
  public void listenFindUserCat(@Header(KafkaHeaders.RECEIVED_KEY) String correlationId, String response) {
    CompletableFuture<String> future = pendingRequests.remove(correlationId);
    if (future != null) {
      future.complete(response);
    }
  }

  public KafkaSender(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }
}
