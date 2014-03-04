package com.makingdevs

enum QuestionType {

  COMPUESTA_SI_NO("question.type.compuesta"),
  COMPUESTA_MULTIPLE("question.type.compuesta"),
  SIN_CALIFICACION("question.type.sin_calificacion"),
  ABIERTA("question.type.abierta")

  private final String code

  QuestionType(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
}