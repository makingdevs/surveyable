package com.makingdevs

class SurveyPerInstance {

  Survey survey
  SurveyPerInstanceStatus surveyPerInstanceStatus

  Date dateCreated
  Date lastUpdated

  static hasMany = [answerPerInstances : AnswerPerInstance]

  static constraints = {
 }
 
}
