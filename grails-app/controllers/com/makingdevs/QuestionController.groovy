package com.makingdevs

class QuestionController {

  def index() { }

  def save(){
    //refactorizar a servicio
    def pregunta
    if(params.from&&params.to){
    pregunta = new Question(description:params.description,
                                questionType:params.questionType,
                                satisfaccion:params.from.toInteger()..params.to.toInteger())
    }else{
    pregunta = new Question(params)
  }
    if(pregunta.save(flush:true)){
      redirect action:"show", id:pregunta.id
    }else{
      render view:"index"
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