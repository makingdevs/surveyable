package com.makingdevs

class SurveyPerInstance {

  Survey survey

  Date dateCreated
  Date lastUpdated

  static hasMany = [answerPerInstances : AnswerPerInstance]

  static constraints = {
 }
 
}
