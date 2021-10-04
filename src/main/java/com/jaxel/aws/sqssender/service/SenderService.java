package com.jaxel.aws.sqssender.service;

import com.jaxel.aws.sqssender.model.Book;

public interface SenderService {
  void send(Book book);
}
