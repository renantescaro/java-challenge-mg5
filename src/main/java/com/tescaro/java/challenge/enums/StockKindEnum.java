package com.tescaro.java.challenge.enums;

public enum StockKindEnum {
  INPUT("Entrada"),
  OUTPUT("Saída");

  private final String description;

  StockKindEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
