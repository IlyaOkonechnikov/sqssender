package com.jaxel.aws.sqssender.controller;

import com.jaxel.aws.sqssender.dto.BookInfoDTO;
import com.jaxel.aws.sqssender.service.SQSSenderService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send")
public class SQSSenderController {

  private final SQSSenderService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public long create(@RequestBody BookInfoDTO bookInfo) {
    return 0L;
  }
}
