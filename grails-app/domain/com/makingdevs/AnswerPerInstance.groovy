package com.makingdevs

class AnswerPerInstance {

  Date dateCreated
  Date lastUpdated

  Question question

  static belongsTo = [surveyPerInstance:SurveyPerInstance]

  static hasMany = [answerPerUsers:AnswerPerUser,
                    openAnswerPerUsers:OpenAnswerPerUser,
                    rangeAnswerPerUsers:RangeAnswerPerUser]

  static constraints = {
  }

}