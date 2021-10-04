package com.jaxel.aws.sqssender.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaxel.aws.sqssender.exception.BookInfoSerializationException;
import com.jaxel.aws.sqssender.model.Book;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessageChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class SQSSenderService implements SenderService {

  @Value("${cloud.aws.queue.name}")
  private String queueName;
  @Value("${cloud.aws.queue.timeout}")
  private long queueTimeout;

  private final ObjectMapper mapper;
  private final AmazonSQSAsync amazonSqs;

  public void send(Book book) {
    MessageChannel messageChannel = new QueueMessageChannel(amazonSqs, amazonSqs.getQueueUrl(queueName).getQueueUrl());
    String payload;
    try {
      payload = mapper.writeValueAsString(book);
    } catch (JsonProcessingException e) {
      throw new BookInfoSerializationException(e);
    }
    Message<String> msg = MessageBuilder
        .withPayload(payload)
        .build();
    messageChannel.send(msg, queueTimeout);
    log.info("Message sent successfully: {}", payload);
  }
}
