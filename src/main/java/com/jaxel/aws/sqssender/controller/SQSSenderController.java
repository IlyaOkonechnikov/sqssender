package com.jaxel.aws.sqssender.controller;

import com.jaxel.aws.sqssender.model.Book;
import com.jaxel.aws.sqssender.service.SenderService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sqs-sender")
public class SQSSenderController {

  private final SenderService service;

  @PostMapping("/send")
  @ResponseStatus(HttpStatus.CREATED)
  public void send(@RequestBody @Valid @NotNull Book book) {
    service.send(book);
  }
}
