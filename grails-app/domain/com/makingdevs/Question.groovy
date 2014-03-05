package com.makingdevs

@groovy.transform.ToString
class Question implements Comparable {

  String description
  QuestionType questionType
  List satisfaccion

  static constraints = {
    description blank:false, size:1..1000
  }

  static hasMany = [answers : Answer,
  satisfaccion:Integer]

  int compareTo(q){
    this.id <=> q.id
  }
}