package com.jaxel.aws.sqssender.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
  @NotBlank
  private String title;
  @NotBlank
  private String author;
}
