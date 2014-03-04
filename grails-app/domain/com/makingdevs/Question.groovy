package com.makingdevs

@groovy.transform.ToString
class Question implements Comparable {

  String description
  QuestionType questionType
  Answer satisfaccion
  Answer comentario

  static constraints = {
    description blank:false, size:1..1000
    satisfaccion nullable: true
    comentario nullable: true
  }

  static hasMany = [answers : Answer]

  int compareTo(q){
    this.id <=> q.id
  }
}