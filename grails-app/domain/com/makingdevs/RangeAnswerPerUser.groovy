package com.makingdevs

class RangeAnswerPerUser {

  Date dateCreated
  Date lastUpdated

  Integer userAnswer

  static belongsTo = [answerPerInstance:AnswerPerInstance]

  static constraints = {
    userAnswer blank:false, nullable: true
  }
}