package com.makingdevs

enum AnswerType {

  SIMPLE("answer.type.simple"),
  COMPUESTA ("answer.type.compuesta")

  private final String code

  AnswerType(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
}