package com.taha.webfluxrealtimechat.messages.controller;

import com.taha.webfluxrealtimechat.messages.model.HotMessage;
import com.taha.webfluxrealtimechat.messages.model.LongLivedMessage;
import com.taha.webfluxrealtimechat.messages.service.MessagesService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

/*
* flow
* the driver will use the order to get the customer id
* and then use the customer id to get the customer details
* if the customer id and the driver they have an opened channel between them
* then the driver will get the channel id and use it
* if the driver doesn't have an opened channel between them
* then the driver will create a new channel, then use to it to communicate with the customer
* will get the channels id and open them for any new messages by doing a get request to the channels' id
* the driver will refresh the channel with the returned channel id
 */
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Slf4j
/*
  * This Controller is used to get the hot messages from the database in real-time
  * and send the messages to the database to the long-lived messages
  * and then the long-lived messages will be sent to clients to load history messages
 */
public class ChatController {
  private final MessagesService messagesService;
  private final RestTemplate restTemplate;

  @GetMapping(path = "hot/{channelId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<HotMessage> getMessages(@PathVariable String channelId) {
    return messagesService.getMessages(channelId);
  }

  @GetMapping(path = "long-lived/{receiverId}")
  public ResponseEntity<Page<LongLivedMessage>> getLongLivedMessages(
      @PathVariable String receiverId, @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
      return ResponseEntity.ok(messagesService.getLongLivedMessages(receiverId, PageRequest.of(page, size)));
  }

  @PostMapping("/")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<HotMessage> postMessage(@Validated @RequestBody HotMessage hotMessage) {
    try{
      Mono<HotMessage> messageMono = messagesService.postMessage(hotMessage);
      LongLivedMessage longLivedMessage =
              LongLivedMessage.builder()
                      .message(hotMessage)
                      .receiverId(hotMessage.getReceiverId())
                      .createdAt(LocalDateTime.now())
                      .build();
      messagesService.saveNonReactiveMessage(
              longLivedMessage);
      log.info("Saved non reactive message: {}", longLivedMessage);
      return messageMono;
    } catch (Exception e) {
      log.error("Error while saving non reactive message: {},\n {}", hotMessage, e.getMessage());
      return Mono.empty();
    }
  }
  @PostMapping("/create-channel")
    @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<String> createChannel(@RequestParam("customerId") String customerId, @RequestParam("driverId") String driverId) {
    try{
      String channelId = UUID.randomUUID().toString();
      String urlCustomer = "http://localhost:8080/api/v1/customers/?customerId=" + customerId +  "&channelId=" + channelId;
      String urlDriver = "http://localhost:8080/api/v1/customers/?customerId=" + driverId +  "&channelId=" + channelId;
      restTemplate.postForEntity(urlCustomer, null, String.class);
      restTemplate.postForEntity(urlDriver, null, String.class);
      return ResponseEntity.ok(channelId);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
