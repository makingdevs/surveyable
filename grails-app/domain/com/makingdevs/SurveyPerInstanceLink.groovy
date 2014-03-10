package com.makingdevs

class SurveyPerInstanceLink {

  SurveyPerInstance surveyPerInstance

  Long surveyPerInstanceRef
  String type

  static constraints = {
    surveyPerInstanceRef min:0L
    type blank:false
  }
}