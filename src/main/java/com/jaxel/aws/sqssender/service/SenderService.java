package com.jaxel.aws.sqssender.service;

import com.jaxel.aws.sqssender.model.BookInfo;

public interface SenderService {
  void send(BookInfo bookInfo);
}
