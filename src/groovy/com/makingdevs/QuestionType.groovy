package com.makingdevs

enum QuestionType {
  
  SIN_CALIFICACION("question.type.sin_calificacion"),
  CON_CALIFICACION("question.type.con_calificacion"),
  ABIERTA("question.type.abierta")

  private final String code

  QuestionType(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
  
}