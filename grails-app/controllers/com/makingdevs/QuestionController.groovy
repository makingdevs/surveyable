package com.makingdevs

class QuestionController {

  def index() { }

  def save(){
    def pregunta = new Question(params)
    if(pregunta.save(flush:true)){
      redirect action:"show", id:pregunta.id
    }else{
      render view:"create"
    }
  }

  def show(){
    def question = Question.get(params.id)
    [question:question]
  }

  def list(){
    def listQuestion = Question.list()
    [listQuestion:listQuestion]
  }

  def detail(){
    def question = Question.get(params.id)
    [question:question]
  }

}