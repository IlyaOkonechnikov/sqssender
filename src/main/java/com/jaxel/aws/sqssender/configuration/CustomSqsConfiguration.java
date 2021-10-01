package com.jaxel.aws.sqssender.configuration;

import java.util.Collections;

import org.springframework.cloud.aws.messaging.config.QueueMessageHandlerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.support.PayloadMethodArgumentResolver;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class CustomSqsConfiguration {

  @Bean
  public QueueMessageHandlerFactory queueMessageHandlerFactory(final ObjectMapper mapper,
                                                               final AmazonSQSAsync amazonSQSAsync) {
    final QueueMessageHandlerFactory queueHandlerFactory = new QueueMessageHandlerFactory();
    queueHandlerFactory.setAmazonSqs(amazonSQSAsync);

    queueHandlerFactory.setArgumentResolvers(
        Collections.singletonList(new PayloadMethodArgumentResolver(jackson2MessageConverter(mapper))));
    return queueHandlerFactory;
  }

  private MessageConverter jackson2MessageConverter(final ObjectMapper mapper) {
    final MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setStrictContentTypeMatch(false);
    converter.setObjectMapper(mapper);
    return converter;
  }
}