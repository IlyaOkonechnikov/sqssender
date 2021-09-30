package com.jaxel.aws.sqssender.service;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaxel.aws.sqssender.dto.BookInfoDTO;
import com.jaxel.aws.sqssender.exception.BookInfoSerializationException;

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
public class SQSSenderService {

  @Value("${cloud.aws.queue.url}")
  private final String queueUrl;
  @Value("${cloud.aws.queue.timeout}")
  private final long queueTimeout;

  private final ObjectMapper mapper;
  private final AmazonSQSAsync amazonSqs;

  public boolean send(BookInfoDTO bookInfo) {
    MessageChannel messageChannel = new QueueMessageChannel(amazonSqs, queueUrl);
    Message<String> msg;
    try {
      msg = MessageBuilder
          .withPayload(mapper.writeValueAsString(bookInfo))
          .build();
    } catch (JsonProcessingException e) {
      throw new BookInfoSerializationException(e);
    }
    boolean sentStatus = messageChannel.send(msg, queueTimeout);
    log.info("The next message has been sent: {}", msg.getPayload());
    return sentStatus;
  }
}
