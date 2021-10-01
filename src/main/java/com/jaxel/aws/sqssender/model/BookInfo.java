package com.jaxel.aws.sqssender.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
  @NotNull
  private int id;
  @NotBlank
  private String title;
  @NotBlank
  private String author;
}
