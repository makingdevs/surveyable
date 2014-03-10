package com.makingdevs

enum SurveyPerInstanceStatus {

  SIN_CONTESTAR("surveyPerInstanceStatus.status.sin_contestar"),
  CONTESTADO ("surveyPerInstanceStatus.status.contestado")

  private final String code

  SurveyPerInstanceStatus(String code){
    this.code = code
  }

  public String getCode(){ return this.code }
}