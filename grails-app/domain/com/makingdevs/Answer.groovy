package com.makingdevs

@groovy.transform.ToString
class Answer implements Comparable{

  String description
  AnswerType answerType 

  static constraints = {
    description blank:false, size:1..1000
  }

 static belongsTo = [question: Question]

 int compareTo(q){
    this.id <=> q.id
  }
 
}